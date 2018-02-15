package com.elipcero.classcustomerschool.service;

import com.elipcero.classcustomerschool.domain.ClassCustomerEvent;
import com.elipcero.classcustomerschool.domain.ClassCustomerIdProjection;
import com.elipcero.classcustomerschool.domain.Event;
import com.elipcero.classcustomerschool.message.ClassCustomerSource;
import com.elipcero.classcustomerschool.message.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@EnableBinding(ClassCustomerSource.class)
public class EventProcessor {

    @Value("${school.classcustomer.eventsourcing.processor.limitForReading}")
    private int limitForReading = 100;

    @Value("${school.classcustomer.eventsourcing.processor.lockedMessageMaxTimeInMinutes}")
    private int lockedMessageMaxTimeInMinutes = 10;

    private final MongoOperations mongo;
    private final ClassCustomerSource source;

    private final String collectionName;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public EventProcessor(MongoOperations mongo, ClassCustomerSource source) {
        Assert.notNull(mongo, "MongoOperations cannot be null");
        Assert.notNull(source, "ClassCustomerSource cannot be null");

        this.mongo = mongo;
        this.source = source;
        this.collectionName = mongo.getCollectionName(ClassCustomerEvent.class);
    }

    @Scheduled(fixedDelay = 5000, initialDelay = 5000)
    public void process() {
        try {
            final UUID correlationId = UUID.randomUUID();

            this.markEventsToReadConcurrently(correlationId);


            final List<ClassCustomerEvent> events = this.mongo.find(
                    query(where(Event.CONST_FIELD_PUBLICATION_CORRELATION_ID).is(correlationId.toString())),
                    ClassCustomerEvent.class);

            for (ClassCustomerEvent event : events) {
                SendingMessage(event);
            }
        } catch (Exception ex) {
            this.log.error("General error for sending messages: {}", ex.toString());
        }
    }

    private void SendingMessage(ClassCustomerEvent event) {
        try {
            this.source.output().send(
                    MessageBuilder.withPayload(Converter.convertToClassCustomerMessage(event)).build());

            mongo.updateFirst(
                    query(where(Event.CONST_FIELD_ID).is(event.getId())),
                    new Update()
                            .set(Event.CONST_FIELD_PUBLISHED, true)
                            .set(Event.CONST_FIELD_PUBLICATION_CORRELATION_ID, ""),
                    this.collectionName);
        } catch (Exception ex) {
            this.log.error("Sending event message: {} with error: {}", event.toString(), ex.toString());
        }
    }

    private void markEventsToReadConcurrently(UUID correlationId) {

        final List<ClassCustomerIdProjection> events = this.mongo.find(
                query(where(Event.CONST_FIELD_PUBLICATION_EXPIRATION_DATE).lt(LocalDateTime.now())
                    .and(Event.CONST_FIELD_PUBLISHED).is(false))
                    .limit(limitForReading), ClassCustomerIdProjection.class);

        for (ClassCustomerIdProjection event: events) {
            mongo.updateFirst(
                    query(
                            where(Event.CONST_FIELD_PUBLICATION_EXPIRATION_DATE).lt(LocalDateTime.now())
                            .and(Event.CONST_FIELD_ID).is(event.getId())
                    ),
                    new Update()
                            .set(Event.CONST_FIELD_PUBLICATION_EXPIRATION_DATE,
                                    LocalDateTime.now().plusMinutes(lockedMessageMaxTimeInMinutes))
                            .set(Event.CONST_FIELD_PUBLICATION_CORRELATION_ID, correlationId.toString()),
                    this.collectionName
            );
       }
    }
}

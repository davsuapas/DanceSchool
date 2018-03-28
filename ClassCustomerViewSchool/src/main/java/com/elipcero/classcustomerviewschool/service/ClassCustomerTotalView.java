package com.elipcero.classcustomerviewschool.service;

import com.elipcero.classcustomerviewschool.domain.ClassCustomer;
import com.elipcero.classcustomerviewschool.domain.ClassCustomerDayTotal;
import com.elipcero.classcustomerviewschool.domain.EventTransaction;
import com.elipcero.schoolcore.eventsourcing.EventMessage;
import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@EnableBinding(ClassCustomerConsumer.class)
public class ClassCustomerTotalView {

    private final MongoOperations mongo;

    @Value("${school.classcustomerview.transaction.timeStoredInMinutes}")
    private int timeStoredInMinutes = 60;

    @Autowired
    public ClassCustomerTotalView(MongoOperations mongo) {
        Assert.notNull(mongo, "MongoOperations cannot be null");
        this.mongo = mongo;
    }

    @StreamListener(ClassCustomerConsumer.INPUT)
    public void ConsumeClassCustomerEvent(EventMessage<ClassCustomer> eventMessage) {
        CalculateDayTotalByCustomer(eventMessage);
        RemoveTransactionsProcessedAndExpired(eventMessage);
    }

    private void RemoveTransactionsProcessedAndExpired(EventMessage<ClassCustomer> eventMessage) {
        final ClassCustomer entity = eventMessage.getEntity();
        mongo.updateMulti(
                query(where(ClassCustomerDayTotal.CONST_FIELD_NAME_CLASSCALENDARID).is(entity.getClassCalendarId())),
                new Update().pull(ClassCustomerDayTotal.CONST_FIELD_NAME_EVENTTRANSACTION,
                        query(where(EventTransaction.CONST_FIELD_NAME_EXPIRATIONDATE).lt(LocalDateTime.now()))),
                ClassCustomerDayTotal.class
        );
    }

    private void CalculateDayTotalByCustomer(EventMessage<ClassCustomer> eventMessage) {
        final ClassCustomer entity = eventMessage.getEntity();
        final int inc = eventMessage.getEventType().equals("ClientAssigned") ? 1 : -1;

        // If no exists insert, otherwise it's not do nothing
        if (!mongo.exists(query(where(ClassCustomerDayTotal.CONST_FIELD_NAME_CLASSCALENDARID).is(entity.getClassCalendarId())), ClassCustomerDayTotal.class)) {
            try {
                mongo.insert(ClassCustomerDayTotal.builder().id(entity.getClassCalendarId()).build());
            }
            catch (DuplicateKeyException e) {
                // it's not do nothing
            }
        }

        // If exists the client increment the number of students by class. This is just done,
        // if the event is not repeated. The events processed are store into collection in the same
        // transaction
        mongo.updateFirst(
            query(
                    where(ClassCustomerDayTotal.CONST_FIELD_NAME_CLASSCALENDARID).is(entity.getClassCalendarId())
                    .and(getTransactionName()).ne(eventMessage.getId())
            ),
            new Update()
                    .inc(getDayFieldName(entity.getClassCalendarDayId()), inc)
                    .push(ClassCustomerDayTotal.CONST_FIELD_NAME_EVENTTRANSACTION,
                            EventTransaction.builder()
                                    .eventId(eventMessage.getId())
                                    .expirationDate(LocalDateTime.now().plusMinutes(this.timeStoredInMinutes)).build()),
            ClassCustomerDayTotal.class
        );
    }

    private String getTransactionName() {
        return ClassCustomerDayTotal.CONST_FIELD_NAME_EVENTTRANSACTION + "." + EventTransaction.CONST_FIELD_NAME_ID;
    }

    private String getDayFieldName(int classCalendarDayId) {
        return ClassCustomerDayTotal.CONST_FIELD_NAME_CUSTOMERTOTALDAY + Integer.toString(classCalendarDayId);
    }
}

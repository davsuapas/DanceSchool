package com.elipcero.classcustomerviewschool.service;

import com.elipcero.classcustomerviewschool.domain.ClassCustomer;
import com.elipcero.classcustomerviewschool.domain.ClassCustomerDayTotal;
import com.elipcero.schoolcore.eventsourcing.EventMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@EnableBinding(ClassCustomerConsumer.class)
public class ClassCustomerTotalView {

    private final MongoOperations mongo;
    private final String collectionName;

    @Autowired
    public ClassCustomerTotalView(MongoOperations mongo) {
        Assert.notNull(mongo, "MongoOperations cannot be null");

        this.mongo = mongo;
        this.collectionName = mongo.getCollectionName(ClassCustomerDayTotal.class);
    }

    @StreamListener(ClassCustomerConsumer.INPUT)
    public void ConsumeClassCustomerEvent(EventMessage<ClassCustomer> event) {

        final ClassCustomer entity = event.getEntity();
        final int inc = event.getEventType() == "ClientAssigned" ? 1 : -1;

        mongo.updateFirst(
                query(
                    where("classCalendarId").is(entity.getClassCalendarId())
                ),
                new Update().inc(getDayFieldName(entity.getClassCalendarDayId()), inc),
                this.collectionName
        );
    }

    private String getDayFieldName(int classCalendarDayId) {
        return ClassCustomerDayTotal.CONST_FIELD_NAME_CUSTOMERTOTALDAY + Integer.toString(classCalendarDayId);
    }
}

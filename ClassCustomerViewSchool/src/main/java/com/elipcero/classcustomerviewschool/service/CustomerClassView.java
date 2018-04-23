package com.elipcero.classcustomerviewschool.service;

import com.elipcero.classcustomerviewschool.domain.ClassCustomer;
import com.elipcero.classcustomerviewschool.domain.Classes;
import com.elipcero.classcustomerviewschool.domain.CustomerClass;
import com.elipcero.schoolcore.eventsourcing.EventMessage;
import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.DayOfWeek;
import java.util.Collections;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class CustomerClassView {

    private final MongoOperations mongo;

    @Autowired
    public CustomerClassView(MongoOperations mongo) {
        Assert.notNull(mongo, "MongoOperations cannot be null");
        this.mongo = mongo;
    }

    public void selectOrUnSelected(EventMessage<ClassCustomer> eventMessage) {
        ClassCustomer entity = eventMessage.getEntity();

        if (eventMessage.getEventType().equals(ClassCustomerEvent.CONST_EVENT_CUSTOMER_REGISTERED)) {
            insertIfNotExists(entity);
        }
        else if (eventMessage.getEventType().equals(ClassCustomerEvent.CONST_EVENT_CUSTOMER_UNREGISTERED)) {
            removeIfExists(entity);
        }
    }

    private void removeIfExists(ClassCustomer entity) {
        if (removeClassDayIfExists(entity)) {
            if (removeClassIfEmpty(entity)) {
                removeCustomerIfEmpty(entity);
            }
        }
    }

    private boolean removeClassDayIfExists(ClassCustomer entity) {
        return mongo.updateFirst(
                query(
                        where(CustomerClass.CONST_FIELD_NAME_ID).is(entity.getClientId())
                            .and(getClassesByIdPath()).is(entity.getClassCalendarId())
                ),
                new Update()
                        .pull(getCalendarDay$Path(), DayOfWeek.of(entity.getClassCalendarDayId())),
                CustomerClass.class
        ).getModifiedCount() > 0;
    }

    private boolean removeClassIfEmpty(ClassCustomer entity) {
        return mongo.updateFirst(
                query(
                        where(CustomerClass.CONST_FIELD_NAME_ID).is(entity.getClientId())
                            .and(getClassesByIdPath()).is(entity.getClassCalendarId())
                            .and(getCalendarDayPath()).size(0)
                ),
                new Update()
                        .pull(CustomerClass.CONST_FIELD_NAME_CLASSES,
                            query(
                                where(Classes.CONST_FIELD_NAME_ID).is(entity.getClassCalendarId())
                            )),
                CustomerClass.class
        ).getModifiedCount() > 0;
    }

    private void removeCustomerIfEmpty(ClassCustomer entity) {
        mongo.remove(
                query(
                        where(CustomerClass.CONST_FIELD_NAME_ID).is(entity.getClientId())
                ),
                CustomerClass.class
        );
    }


    private void insertIfNotExists(ClassCustomer entity) {
        if (!insertClassDayIfNotExists(entity)) {
            if (!insertClassIfNotExists(entity)) {
                insertCustomerIfNotExists(entity);
                if (!insertClassIfNotExists(entity)) {
                    insertClassDayIfNotExists(entity);
                }
            }
        }
    }

    private boolean insertClassDayIfNotExists(ClassCustomer entity) {
        return mongo.updateFirst(
                query(
                        where(CustomerClass.CONST_FIELD_NAME_ID).is(entity.getClientId())
                                .and(getClassesByIdPath()).is(entity.getClassCalendarId())
                ),
                new Update()
                        .addToSet(getCalendarDay$Path(), DayOfWeek.of(entity.getClassCalendarDayId())),
                CustomerClass.class
        ).getMatchedCount() > 0;
    }

    private boolean insertClassIfNotExists(ClassCustomer entity) {
        return mongo.updateFirst(
                query(
                        where(CustomerClass.CONST_FIELD_NAME_ID).is(entity.getClientId())
                                .and(getClassesByIdPath()).ne(entity.getClassCalendarId())
                ),
                new Update()
                        .push(CustomerClass.CONST_FIELD_NAME_CLASSES,
                                Classes.builder()
                                        .id(entity.getClassCalendarId())
                                        .name(entity.getClassCalendarName())
                                        .day(Collections.singletonList(
                                            DayOfWeek.of(entity.getClassCalendarDayId())
                                        ))
                                    .build()),
                CustomerClass.class
        ).getModifiedCount() > 0;
    }

    private void insertCustomerIfNotExists(ClassCustomer entity) {
        if (!mongo.exists(query(where(CustomerClass.CONST_FIELD_NAME_ID).is(entity.getClientId())), CustomerClass.class)) {
            try {
                mongo.insert(CustomerClass.builder()
                        .id(entity.getClientId())
                        .clientName(entity.getClientName())
                            .build());
            }
            catch (DuplicateKeyException e) {
                // it's not do nothing
            }
        }
    }

    private String getClassesByIdPath() {
        return CustomerClass.CONST_FIELD_NAME_CLASSES + "." + Classes.CONST_FIELD_NAME_ID;
    }

    private String getCalendarDayPath() {
        return CustomerClass.CONST_FIELD_NAME_CLASSES + "." + Classes.CONST_FIELD_NAME_DAY;
    }

    private String getCalendarDay$Path() {
        return CustomerClass.CONST_FIELD_NAME_CLASSES + ".$." + Classes.CONST_FIELD_NAME_DAY;
    }
}

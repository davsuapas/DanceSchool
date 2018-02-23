package com.elipcero.classcustomerschool.domain;

import com.elipcero.schoolcore.eventsourcing.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Getter
@ToString
@Document(collection="ClassCustomerEvent")
public class ClassCustomerEvent extends Event<ClassCustomer> {

    public ClassCustomerEvent(long eventId, String eventType, ClassCustomer entity) {
        super(eventId, eventType, entity);
    }
}

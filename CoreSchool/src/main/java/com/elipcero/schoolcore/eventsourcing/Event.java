package com.elipcero.schoolcore.eventsourcing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Event<T> {

    public static final String CONST_FIELD_PUBLICATION_CORRELATION_ID = "publicationCorrelationId";
    public static final String CONST_FIELD_PUBLICATION_EXPIRATION_DATE = "publicationExpirationDate";
    public static final String CONST_FIELD_PUBLISHED = "published";
    public static final String CONST_FIELD_ID = "_id";

    public Event(long id, String eventType, T entity) {
        this.id = id;
        this.eventType = eventType;
        this.entity = entity;
        this.publicationExpirationDate = LocalDateTime.now();
        this.creationDate = LocalDateTime.now();
    }

    private boolean published;
    private LocalDateTime publicationExpirationDate;
    private String publicationCorrelationId;

    @Id
    private long id;
    private String eventType;
    private LocalDateTime creationDate;

    private T entity;
}

package com.elipcero.classcustomerschool.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Document
public class Event<T> {

    public Event(long eventId, String eventType, T entity) {
        this.id = eventId;
        this.eventType = eventType;
        this.entity = entity;
        this.publicationExpirationDate = LocalDateTime.now();
        this.creationDate = LocalDateTime.now();
    }

    private boolean published;
    private LocalDateTime publicationExpirationDate;
    private UUID publicationCorrelationId;

    @Id
    private final long id;
    private final String eventType;
    private final LocalDateTime creationDate;

    private final T entity;
}

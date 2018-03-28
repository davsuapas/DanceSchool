package com.elipcero.classcustomerviewschool.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class EventTransaction {
    public static final String CONST_FIELD_NAME_ID = "eventId";
    public static final String CONST_FIELD_NAME_EXPIRATIONDATE = "expirationDate";

    private final long eventId;
    private final LocalDateTime expirationDate;
}

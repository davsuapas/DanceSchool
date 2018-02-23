package com.elipcero.classcustomerschool.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document(collection="Counter")
public class Counter {
    @Id private long id;
    private long count;
}

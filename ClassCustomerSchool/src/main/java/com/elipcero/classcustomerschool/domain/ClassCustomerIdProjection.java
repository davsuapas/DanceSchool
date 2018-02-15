package com.elipcero.classcustomerschool.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@Document(collection="ClassCustomerEvent")
public class ClassCustomerIdProjection {
    @Id
    private final long id;
}

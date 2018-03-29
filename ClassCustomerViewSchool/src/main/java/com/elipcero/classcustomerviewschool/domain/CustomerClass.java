package com.elipcero.classcustomerviewschool.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Builder
@Document(collection = "CustomerClass")
public class CustomerClass {

    public static final String CONST_FIELD_NAME_ID = "_id";
    public static final String CONST_FIELD_NAME_CLIENTNAME = "clientName";
    public static final String CONST_FIELD_NAME_CLASSES = "classes";

    @Id
    private int id;

    private String clientName;
    private List<Classes> classes;
}

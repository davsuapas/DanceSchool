package com.elipcero.classcustomerviewschool.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ClassCustomerConsumer {

    String INPUT = "ClassCustomerEvent";

    @Input(ClassCustomerConsumer.INPUT)
    SubscribableChannel input();
}

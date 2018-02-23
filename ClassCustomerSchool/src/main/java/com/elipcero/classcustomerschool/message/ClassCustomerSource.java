package com.elipcero.classcustomerschool.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ClassCustomerSource {

    @Output("ClassCustomerEvent")
    MessageChannel output();
}

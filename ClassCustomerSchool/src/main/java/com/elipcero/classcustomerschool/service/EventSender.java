package com.elipcero.classcustomerschool.service;

import com.elipcero.classcustomerschool.domain.ClassCustomerEvent;
import com.elipcero.classcustomerschool.message.ClassCustomerSource;
import com.elipcero.classcustomerschool.message.Converter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;

@RequiredArgsConstructor
public class EventSender {

    @NonNull  private final ClassCustomerSource source;

    public boolean sendingMessage(ClassCustomerEvent event) {
        if (this.source.output().send(
                MessageBuilder.withPayload(Converter.convertToClassCustomerMessage(event)).build())) {
            return true;
        }
        return false;
    }
}

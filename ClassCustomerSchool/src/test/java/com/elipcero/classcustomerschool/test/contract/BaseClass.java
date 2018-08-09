package com.elipcero.classcustomerschool.test.contract;

import com.elipcero.classcustomerschool.domain.ClassCustomer;
import com.elipcero.classcustomerschool.domain.ClassCustomerEvent;
import com.elipcero.classcustomerschool.message.ClassCustomerSource;
import com.elipcero.classcustomerschool.service.EventSender;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMessageVerifier
@ActiveProfiles("test")
public class BaseClass {

    @Autowired private ClassCustomerSource source;

    private EventSender eventSender;

    @Before
    public void setup() {
        eventSender = new EventSender(source);
    }

    public void registerEvent()  {
        eventSender.sendingMessage(
                new ClassCustomerEvent(1, "CustomerRegistered",
                        ClassCustomer.builder()
                            .clientId(1)
                            .clientName("David, Suarez, Pascual")
                            .classCalendarId(1)
                            .classCalendarName("Aula 1 - Aerobic")
                            .classCalendarDayId(7)
                        .build())
        );
    }
}
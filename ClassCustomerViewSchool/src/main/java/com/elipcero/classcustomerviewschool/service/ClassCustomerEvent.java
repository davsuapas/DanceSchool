package com.elipcero.classcustomerviewschool.service;

import com.elipcero.classcustomerviewschool.domain.ClassCustomer;
import com.elipcero.schoolcore.eventsourcing.EventMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(ClassCustomerConsumer.class)
@RequiredArgsConstructor
public class ClassCustomerEvent {

    public static final String CONST_EVENT_CUSTOMER_REGISTERED = "CustomerRegistered";
    public static final String CONST_EVENT_CUSTOMER_UNREGISTERED = "CustomerUnregistered";

    @NonNull private ClassCustomerTotalView classCustomerTotalView;
    @NonNull private CustomerClassView customerClassView;

    @StreamListener(ClassCustomerConsumer.INPUT)
    public void ConsumeClassCustomerEvent(EventMessage<ClassCustomer> eventMessage) {
        classCustomerTotalView.calculate(eventMessage);
        customerClassView.selectOrUnSelected(eventMessage);
    }
}

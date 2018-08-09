org.springframework.cloud.contract.spec.Contract.make {

    description 'Register event: Customer registered'

    label 'CustomerRegistered'

    input {
        // the contract will be triggered by a method
        triggeredBy('registerEvent()')
    }
    // output message of the contract
    outputMessage {
        // destination to which the output message will be sent
        sentTo 'ClassCustomerEvent'
        // the body of the output message
        body('''{"id":1,"eventType":"CustomerRegistered","entity":{"clientId":1,"clientName":"David, Suarez, Pascual","classCalendarId":1,"classCalendarName":"Aula 1 - Aerobic","classCalendarDayId":7}}''')
        headers {
            header('contentType', applicationJson())
        }
    }
}
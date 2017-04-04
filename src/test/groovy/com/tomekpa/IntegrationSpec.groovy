package com.tomekpa

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification

@SpringBootTest
class IntegrationSpec extends Specification {

    @Autowired
    ApplicationContext applicationContext

    def "Sanity IntegrationSpec"() {
        expect:
        true
    }

    def "ApplicationContext not null"() {
        expect:
        applicationContext
    }
}
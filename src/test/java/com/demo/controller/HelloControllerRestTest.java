package com.demo.controller;

import com.demo.RestBaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloControllerRestTest extends RestBaseTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturnDefaultMessage() {
        assertThat(this.testRestTemplate.getForObject(url + "/api/greeting", String.class))
                .contains("Hello World!");
    }
}

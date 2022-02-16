package com.demo;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * 此类型测试会启动服务
 */
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = Application.class)
public class RestBaseTest {

    @LocalServerPort
    protected int port;
    protected String url;

    @BeforeEach
    public void setUp() {
        url = String.format("http://localhost:%d", port);
    }

}

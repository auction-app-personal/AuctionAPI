package com.application.auction.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTests {
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        //setup db
    }

    @Test
    public void testAccountCreation() throws Exception {

    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        //clean db
    }
}

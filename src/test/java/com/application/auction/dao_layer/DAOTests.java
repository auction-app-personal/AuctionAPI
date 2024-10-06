package com.application.auction.dao_layer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DAOTests {
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

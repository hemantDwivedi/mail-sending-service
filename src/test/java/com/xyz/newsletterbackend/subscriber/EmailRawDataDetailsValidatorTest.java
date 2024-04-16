package com.xyz.newsletterbackend.subscriber;

import com.xyz.newsletterbackend.utils.EmailDetailsValidator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailRawDataDetailsValidatorTest {
    @InjectMocks
    private EmailDetailsValidator emailDetailsValidator;

    @Test
    void testValidSubscription(){
        boolean result = emailDetailsValidator.isSubscriptionValid("testuser@testing.com");
        assertTrue(result, "Valid subscription should return true");
    }
    @Test
    void testInvalidSubscriptionBlankFields() {
        boolean result = emailDetailsValidator.isSubscriptionValid("");
        assertFalse(result, "Blank fields should return false");
    }
}
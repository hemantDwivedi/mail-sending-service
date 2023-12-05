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
    public void testValidSubscription(){
        boolean result = emailDetailsValidator.isSubscriptionValid("test repository", "testuser@testing.com");
        assertTrue(result, "Valid subscription should return true");
    }
    @Test
    public void testInvalidSubscriptionBlankFields() {
        boolean result = emailDetailsValidator.isSubscriptionValid("", "");
        assertFalse(result, "Blank fields should return false");
    }

    @Test
    public void testInvalidSubscriptionMissingName() {
        boolean result = emailDetailsValidator.isSubscriptionValid("", "test@example.com");
        assertFalse(result, "Missing name should return false");
    }

    @Test
    public void testInvalidSubscriptionMissingEmail() {
        boolean result = emailDetailsValidator.isSubscriptionValid("Test User", "");
        assertFalse(result, "Missing email should return false");
    }
    @Test
    public void testInvalidSubscriptionIncorrectEmail() {
        boolean result = emailDetailsValidator.isSubscriptionValid("Hemant", "justhemant.d@gmail.com");
        assertFalse(result, "Incorrect email should return false");
    }
}
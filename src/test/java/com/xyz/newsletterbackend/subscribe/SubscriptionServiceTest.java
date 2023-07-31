package com.xyz.newsletterbackend.subscribe;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SubscriptionServiceTest {
    @InjectMocks
    private SubscriptionService subscriptionService;

    @Test
    public void testValidSubscription(){
        boolean result = subscriptionService.isSubscriptionValid("test user", "testuser@testing.com");
        assertTrue(result, "Valid subscription should return true");
    }
    @Test
    public void testInvalidSubscriptionBlankFields() {
        boolean result = subscriptionService.isSubscriptionValid("", "");
        assertFalse(result, "Blank fields should return false");
    }

    @Test
    public void testInvalidSubscriptionMissingName() {
        boolean result = subscriptionService.isSubscriptionValid("", "test@example.com");
        assertFalse(result, "Missing name should return false");
    }

    @Test
    public void testInvalidSubscriptionMissingEmail() {
        boolean result = subscriptionService.isSubscriptionValid("Test User", "");
        assertFalse(result, "Missing email should return false");
    }
    @Test
    public void testInvalidSubscriptionIncorrectEmail() {
        boolean result = subscriptionService.isSubscriptionValid("Hemant", "justhemant.d@gmail.com");
        assertFalse(result, "Incorrect email should return false");
    }
}
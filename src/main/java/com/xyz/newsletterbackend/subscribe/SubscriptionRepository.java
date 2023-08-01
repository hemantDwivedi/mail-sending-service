package com.xyz.newsletterbackend.subscribe;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SubscriptionRepository extends MongoRepository<Subscribe, String> {
    Optional<Subscribe> findByEmail(String email);
}

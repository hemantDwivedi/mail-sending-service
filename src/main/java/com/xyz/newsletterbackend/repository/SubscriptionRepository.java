package com.xyz.newsletterbackend.repository;

import com.xyz.newsletterbackend.model.Subscriber;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SubscriptionRepository extends MongoRepository<Subscriber, String> {
    Optional<Subscriber> findByEmail(String email);
}

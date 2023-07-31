package com.xyz.newsletterbackend.subscribe;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubscribeRepository extends MongoRepository<Subscribe, String> {
}

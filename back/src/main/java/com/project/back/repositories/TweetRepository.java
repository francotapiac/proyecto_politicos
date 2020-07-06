package com.project.back.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.project.back.models.Tweet;

public interface TweetRepository extends MongoRepository<Tweet, String> {
}

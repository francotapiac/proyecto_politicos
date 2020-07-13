package com.project.back.repositories;

import com.project.back.models.KeyWords;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KeyWordsRepository extends MongoRepository<KeyWords, String> {
}

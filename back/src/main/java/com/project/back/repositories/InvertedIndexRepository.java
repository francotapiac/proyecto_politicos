package com.project.back.repositories;

import com.project.back.models.InvertedIndex;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvertedIndexRepository extends MongoRepository<InvertedIndex, String> {
}

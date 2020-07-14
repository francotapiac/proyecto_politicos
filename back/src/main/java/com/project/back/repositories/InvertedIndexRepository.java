package com.project.back.repositories;

import com.project.back.models.InvertedIndex;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InvertedIndexRepository extends MongoRepository<InvertedIndex, String> {
    List<InvertedIndex> findByName(String name);
}

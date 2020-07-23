package com.project.back.repositories;

import com.project.back.models.PoliticalParty;
import com.project.back.models.Politician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliticianRepository extends JpaRepository<Politician, Integer> {

}

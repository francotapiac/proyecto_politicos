package com.project.back.repositories;

import com.project.back.models.PoliticalParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliticalPartyRepository extends JpaRepository<PoliticalParty, Integer> {
}

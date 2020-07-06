package com.project.back.repositories;

import com.project.back.models.NationalActuality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalActualityRepository extends JpaRepository<NationalActuality, Integer>{
    NationalActuality findNationalActualityById(Integer id);

}

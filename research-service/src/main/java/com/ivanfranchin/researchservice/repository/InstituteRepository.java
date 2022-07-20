package com.ivanfranchin.researchservice.repository;

import com.ivanfranchin.researchservice.model.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituteRepository extends JpaRepository<Institute, Long> {
}

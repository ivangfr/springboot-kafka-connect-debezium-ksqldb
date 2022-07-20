package com.ivanfranchin.researchservice.repository;

import com.ivanfranchin.researchservice.model.Researcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResearcherRepository extends JpaRepository<Researcher, Long> {
}

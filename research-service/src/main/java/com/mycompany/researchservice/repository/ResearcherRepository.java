package com.mycompany.researchservice.repository;

import com.mycompany.researchservice.model.Researcher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResearcherRepository extends JpaRepository<Researcher, Long> {
}

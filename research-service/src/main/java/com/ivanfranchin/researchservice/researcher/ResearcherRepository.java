package com.ivanfranchin.researchservice.researcher;

import com.ivanfranchin.researchservice.researcher.model.Researcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResearcherRepository extends JpaRepository<Researcher, Long> {
}

package com.mycompany.researchservice.repository;

import com.mycompany.researchservice.model.Institute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituteRepository extends JpaRepository<Institute, Long> {
}

package com.mycompany.researchservice.repository;

import com.mycompany.researchservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}

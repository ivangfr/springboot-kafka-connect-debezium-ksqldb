package com.mycompany.researchservice.repository;

import com.mycompany.researchservice.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}

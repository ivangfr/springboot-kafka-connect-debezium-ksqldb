package com.mycompany.researchservice.service;

import com.mycompany.researchservice.model.Article;
import com.mycompany.researchservice.model.Institute;

import java.util.List;

public interface ArticleService {

    List<Article> getAllArticles();

    Article validateAndGetArticle(Long id);

    Article saveArticle(Article article);

    void deleteArticle(Article article);

}

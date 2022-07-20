package com.ivanfranchin.researchservice.service;

import com.ivanfranchin.researchservice.exception.ArticleDeletionException;
import com.ivanfranchin.researchservice.repository.ArticleRepository;
import com.ivanfranchin.researchservice.exception.ArticleNotFoundException;
import com.ivanfranchin.researchservice.model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article validateAndGetArticle(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
    }

    @Override
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Article article) {
        try {
            articleRepository.delete(article);
        } catch (DataIntegrityViolationException e) {
            throw new ArticleDeletionException(article.getId());
        }
    }
}

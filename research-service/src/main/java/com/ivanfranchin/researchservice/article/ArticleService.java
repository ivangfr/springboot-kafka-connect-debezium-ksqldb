package com.ivanfranchin.researchservice.article;

import com.ivanfranchin.researchservice.article.exception.ArticleDeletionException;
import com.ivanfranchin.researchservice.article.exception.ArticleNotFoundException;
import com.ivanfranchin.researchservice.article.model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article validateAndGetArticle(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
    }

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    public void deleteArticle(Article article) {
        try {
            articleRepository.delete(article);
        } catch (DataIntegrityViolationException e) {
            throw new ArticleDeletionException(article.getId());
        }
    }
}

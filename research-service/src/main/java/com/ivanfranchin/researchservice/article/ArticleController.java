package com.ivanfranchin.researchservice.article;

import com.ivanfranchin.researchservice.article.model.Article;
import com.ivanfranchin.researchservice.article.dto.ArticleResponse;
import com.ivanfranchin.researchservice.article.dto.CreateArticleRequest;
import com.ivanfranchin.researchservice.article.dto.UpdateArticleRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public List<ArticleResponse> getAllArticles() {
        return articleService.getAllArticles()
                .stream()
                .map(ArticleResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    public ArticleResponse getArticle(@PathVariable Long id) {
        Article article = articleService.validateAndGetArticle(id);
        return ArticleResponse.from(article);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ArticleResponse createArticle(@Valid @RequestBody CreateArticleRequest createArticleRequest) {
        Article article = Article.from(createArticleRequest);
        article = articleService.saveArticle(article);
        return ArticleResponse.from(article);
    }

    @PatchMapping("/{id}")
    public ArticleResponse updateArticle(@PathVariable Long id, @Valid @RequestBody UpdateArticleRequest updateArticleRequest) {
        Article article = articleService.validateAndGetArticle(id);
        Article.updateFrom(updateArticleRequest, article);
        article = articleService.saveArticle(article);
        return ArticleResponse.from(article);
    }

    @DeleteMapping("/{id}")
    public ArticleResponse deleteArticle(@PathVariable Long id) {
        Article article = articleService.validateAndGetArticle(id);
        articleService.deleteArticle(article);
        return ArticleResponse.from(article);
    }
}

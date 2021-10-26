package com.mycompany.researchservice.rest;

import com.mycompany.researchservice.mapper.ArticleMapper;
import com.mycompany.researchservice.model.Article;
import com.mycompany.researchservice.rest.dto.ArticleResponse;
import com.mycompany.researchservice.rest.dto.CreateArticleRequest;
import com.mycompany.researchservice.rest.dto.UpdateArticleRequest;
import com.mycompany.researchservice.service.ArticleService;
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

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleMapper articleMapper;

    @GetMapping
    public List<ArticleResponse> getAllArticles() {
        return articleService.getAllArticles()
                .stream()
                .map(articleMapper::toArticleResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ArticleResponse getArticle(@PathVariable Long id) {
        Article article = articleService.validateAndGetArticle(id);
        return articleMapper.toArticleResponse(article);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ArticleResponse createArticle(@Valid @RequestBody CreateArticleRequest createArticleRequest) {
        Article article = articleMapper.toArticle(createArticleRequest);
        article = articleService.saveArticle(article);
        return articleMapper.toArticleResponse(article);
    }

    @PatchMapping("/{id}")
    public ArticleResponse updateArticle(@PathVariable Long id, @Valid @RequestBody UpdateArticleRequest updateArticleRequest) {
        Article article = articleService.validateAndGetArticle(id);
        articleMapper.updateArticleFromRequest(updateArticleRequest, article);
        article = articleService.saveArticle(article);
        return articleMapper.toArticleResponse(article);
    }

    @DeleteMapping("/{id}")
    public ArticleResponse deleteArticle(@PathVariable Long id) {
        Article article = articleService.validateAndGetArticle(id);
        articleService.deleteArticle(article);
        return articleMapper.toArticleResponse(article);
    }
}

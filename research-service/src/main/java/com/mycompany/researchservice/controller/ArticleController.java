package com.mycompany.researchservice.controller;

import com.mycompany.researchservice.dto.CreateArticleDto;
import com.mycompany.researchservice.dto.ArticleDto;
import com.mycompany.researchservice.dto.UpdateArticleDto;
import com.mycompany.researchservice.dto.UpdateArticleDto;
import com.mycompany.researchservice.model.Article;
import com.mycompany.researchservice.service.ArticleService;
import com.mycompany.researchservice.service.ArticleService;
import ma.glasnost.orika.MapperFacade;
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

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final MapperFacade mapperFacade;

    public ArticleController(ArticleService articleService, MapperFacade mapperFacade) {
        this.articleService = articleService;
        this.mapperFacade = mapperFacade;
    }

    @GetMapping
    public List<ArticleDto> getAllArticles() {
        return articleService.getAllArticles()
                .stream()
                .map(article -> mapperFacade.map(article, ArticleDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ArticleDto getArticle(@PathVariable Long id) {
        Article article = articleService.validateAndGetArticle(id);
        return mapperFacade.map(article, ArticleDto.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ArticleDto createArticle(@Valid @RequestBody CreateArticleDto createArticleDto) {
        Article article = mapperFacade.map(createArticleDto, Article.class);
        article = articleService.saveArticle(article);
        return mapperFacade.map(article, ArticleDto.class);
    }

    @PatchMapping("/{id}")
    public ArticleDto updateArticle(@PathVariable Long id, @Valid @RequestBody UpdateArticleDto updateArticleDto) {
        Article article = articleService.validateAndGetArticle(id);
        mapperFacade.map(updateArticleDto, article);
        article = articleService.saveArticle(article);
        return mapperFacade.map(article, ArticleDto.class);
    }

    @DeleteMapping("/{id}")
    public ArticleDto deleteArticle(@PathVariable Long id) {
        Article article = articleService.validateAndGetArticle(id);
        articleService.deleteArticle(article);
        return mapperFacade.map(article, ArticleDto.class);
    }

}

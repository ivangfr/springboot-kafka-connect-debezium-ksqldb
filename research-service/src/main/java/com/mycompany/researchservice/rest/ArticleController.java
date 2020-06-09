package com.mycompany.researchservice.rest;

import com.mycompany.researchservice.mapper.ArticleMapper;
import com.mycompany.researchservice.model.Article;
import com.mycompany.researchservice.rest.dto.ArticleDto;
import com.mycompany.researchservice.rest.dto.CreateArticleDto;
import com.mycompany.researchservice.rest.dto.UpdateArticleDto;
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
    public List<ArticleDto> getAllArticles() {
        return articleService.getAllArticles()
                .stream()
                .map(articleMapper::toArticleDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ArticleDto getArticle(@PathVariable Long id) {
        Article article = articleService.validateAndGetArticle(id);
        return articleMapper.toArticleDto(article);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ArticleDto createArticle(@Valid @RequestBody CreateArticleDto createArticleDto) {
        Article article = articleMapper.toArticle(createArticleDto);
        article = articleService.saveArticle(article);
        return articleMapper.toArticleDto(article);
    }

    @PatchMapping("/{id}")
    public ArticleDto updateArticle(@PathVariable Long id, @Valid @RequestBody UpdateArticleDto updateArticleDto) {
        Article article = articleService.validateAndGetArticle(id);
        articleMapper.updateArticleFromDto(updateArticleDto, article);
        article = articleService.saveArticle(article);
        return articleMapper.toArticleDto(article);
    }

    @DeleteMapping("/{id}")
    public ArticleDto deleteArticle(@PathVariable Long id) {
        Article article = articleService.validateAndGetArticle(id);
        articleService.deleteArticle(article);
        return articleMapper.toArticleDto(article);
    }

}

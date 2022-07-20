package com.ivanfranchin.researchservice.mapper;

import com.ivanfranchin.researchservice.rest.dto.ArticleResponse;
import com.ivanfranchin.researchservice.model.Article;
import com.ivanfranchin.researchservice.rest.dto.CreateArticleRequest;
import com.ivanfranchin.researchservice.rest.dto.UpdateArticleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ArticleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Article toArticle(CreateArticleRequest createArticleRequest);

    ArticleResponse toArticleResponse(Article article);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateArticleFromRequest(UpdateArticleRequest updateArticleRequest, @MappingTarget Article article);
}

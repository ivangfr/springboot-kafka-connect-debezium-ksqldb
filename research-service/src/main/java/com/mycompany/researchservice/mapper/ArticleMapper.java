package com.mycompany.researchservice.mapper;

import com.mycompany.researchservice.model.Article;
import com.mycompany.researchservice.rest.dto.ArticleResponse;
import com.mycompany.researchservice.rest.dto.CreateArticleRequest;
import com.mycompany.researchservice.rest.dto.UpdateArticleRequest;
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

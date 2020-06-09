package com.mycompany.researchservice.mapper;

import com.mycompany.researchservice.model.Article;
import com.mycompany.researchservice.rest.dto.ArticleDto;
import com.mycompany.researchservice.rest.dto.CreateArticleDto;
import com.mycompany.researchservice.rest.dto.UpdateArticleDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ArticleMapper {

    Article toArticle(CreateArticleDto createArticleDto);

    ArticleDto toArticleDto(Article article);

    void updateArticleFromDto(UpdateArticleDto updateArticleDto, @MappingTarget Article article);

}

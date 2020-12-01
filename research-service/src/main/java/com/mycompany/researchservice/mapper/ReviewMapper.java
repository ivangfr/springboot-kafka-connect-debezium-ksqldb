package com.mycompany.researchservice.mapper;

import com.mycompany.researchservice.model.Review;
import com.mycompany.researchservice.rest.dto.CreateReviewDto;
import com.mycompany.researchservice.rest.dto.ReviewDto;
import com.mycompany.researchservice.rest.dto.UpdateReviewDto;
import com.mycompany.researchservice.service.ArticleService;
import com.mycompany.researchservice.service.ResearcherService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {ResearcherService.class, ArticleService.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ReviewMapper {

    @Mapping(source = "article.id", target = "articleId")
    @Mapping(source = "researcher.id", target = "researcherId")
    ReviewDto toReviewDto(Review review);

    void updateReviewFromDto(UpdateReviewDto updateReviewDto, @MappingTarget Review review);

    @Mapping(target = "researcher", source = "researcherId")
    @Mapping(target = "article", source = "articleId")
    Review toReview(CreateReviewDto createReviewDto);

}

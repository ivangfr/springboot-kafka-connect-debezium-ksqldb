package com.ivanfranchin.researchservice.mapper;

import com.ivanfranchin.researchservice.rest.dto.CreateReviewRequest;
import com.ivanfranchin.researchservice.rest.dto.UpdateReviewRequest;
import com.ivanfranchin.researchservice.service.ResearcherService;
import com.ivanfranchin.researchservice.model.Review;
import com.ivanfranchin.researchservice.rest.dto.ReviewResponse;
import com.ivanfranchin.researchservice.service.ArticleService;
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
    ReviewResponse toReviewResponse(Review review);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "researcher", ignore = true)
    @Mapping(target = "article", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateReviewFromRequest(UpdateReviewRequest updateReviewRequest, @MappingTarget Review review);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "researcher", source = "researcherId")
    @Mapping(target = "article", source = "articleId")
    Review toReview(CreateReviewRequest createReviewRequest);
}

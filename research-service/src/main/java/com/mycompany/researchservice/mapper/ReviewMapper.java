package com.mycompany.researchservice.mapper;

import com.mycompany.researchservice.model.Review;
import com.mycompany.researchservice.rest.dto.CreateReviewRequest;
import com.mycompany.researchservice.rest.dto.ReviewResponse;
import com.mycompany.researchservice.rest.dto.UpdateReviewRequest;
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
    ReviewResponse toReviewResponse(Review review);

    void updateReviewFromRequest(UpdateReviewRequest updateReviewRequest, @MappingTarget Review review);

    @Mapping(target = "researcher", source = "researcherId")
    @Mapping(target = "article", source = "articleId")
    Review toReview(CreateReviewRequest createReviewRequest);
}

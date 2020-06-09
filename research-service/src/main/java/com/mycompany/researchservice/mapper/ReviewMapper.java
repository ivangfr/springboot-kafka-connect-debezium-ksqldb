package com.mycompany.researchservice.mapper;

import com.mycompany.researchservice.model.Article;
import com.mycompany.researchservice.model.Researcher;
import com.mycompany.researchservice.model.Review;
import com.mycompany.researchservice.rest.dto.CreateReviewDto;
import com.mycompany.researchservice.rest.dto.ReviewDto;
import com.mycompany.researchservice.rest.dto.UpdateReviewDto;
import com.mycompany.researchservice.service.ArticleService;
import com.mycompany.researchservice.service.ResearcherService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {ResearcherService.class, ArticleService.class}
)
public abstract class ReviewMapper {

    @Autowired
    private ResearcherService researcherService;

    @Autowired
    private ArticleService articleService;

    @Mapping(source = "article.id", target = "articleId")
    @Mapping(source = "researcher.id", target = "researcherId")
    public abstract ReviewDto toReviewDto(Review review);

    public abstract void updateReviewFromDto(UpdateReviewDto updateReviewDto, @MappingTarget Review review);

    public Review toReview(CreateReviewDto createReviewDto) {
        Review review = new Review();
        review.setComment(createReviewDto.getComment());

        Researcher researcher = researcherService.validateAndGetResearcher(createReviewDto.getResearcherId());
        review.setResearcher(researcher);

        Article article = articleService.validateAndGetArticle(createReviewDto.getArticleId());
        review.setArticle(article);

        return review;
    }

}

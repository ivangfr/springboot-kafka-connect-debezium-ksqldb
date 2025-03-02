package com.ivanfranchin.researchservice.review;

import com.ivanfranchin.researchservice.article.ArticleService;
import com.ivanfranchin.researchservice.article.model.Article;
import com.ivanfranchin.researchservice.researcher.ResearcherService;
import com.ivanfranchin.researchservice.researcher.model.Researcher;
import com.ivanfranchin.researchservice.review.dto.CreateReviewRequest;
import com.ivanfranchin.researchservice.review.exception.ReviewDeletionException;
import com.ivanfranchin.researchservice.review.exception.ReviewNotFoundException;
import com.ivanfranchin.researchservice.review.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ResearcherService researcherService;
    private final ArticleService articleService;

    public Review validateAndGetReview(Long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
    }

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public void deleteReview(Review review) {
        try {
            reviewRepository.delete(review);
        } catch (DataIntegrityViolationException e) {
            throw new ReviewDeletionException(review.getId());
        }
    }

    public Review createReviewFrom(CreateReviewRequest createReviewRequest) {
        Researcher researcher = researcherService.validateAndGetResearcher(createReviewRequest.researcherId());
        Article article = articleService.validateAndGetArticle(createReviewRequest.articleId());

        Review review = new Review();
        review.setResearcher(researcher);
        review.setArticle(article);
        review.setComment(createReviewRequest.comment());
        return review;
    }
}

package com.mycompany.researchservice.controller;

import com.mycompany.researchservice.dto.CreateReviewDto;
import com.mycompany.researchservice.dto.ReviewDto;
import com.mycompany.researchservice.dto.UpdateReviewDto;
import com.mycompany.researchservice.model.Review;
import com.mycompany.researchservice.service.ReviewService;
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

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final MapperFacade mapperFacade;

    public ReviewController(ReviewService reviewService, MapperFacade mapperFacade) {
        this.reviewService = reviewService;
        this.mapperFacade = mapperFacade;
    }

    @GetMapping("/{id}")
    public ReviewDto getReview(@PathVariable Long id) {
        Review review = reviewService.validateAndGetReview(id);
        return mapperFacade.map(review, ReviewDto.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ReviewDto createReview(@Valid @RequestBody CreateReviewDto createReviewDto) {
        Review review = mapperFacade.map(createReviewDto, Review.class);
        review = reviewService.saveReview(review);
        return mapperFacade.map(review, ReviewDto.class);
    }

    @PatchMapping("/{id}")
    public ReviewDto updateReview(@PathVariable Long id, @Valid @RequestBody UpdateReviewDto updateReviewDto) {
        Review review = reviewService.validateAndGetReview(id);
        mapperFacade.map(updateReviewDto, review);
        review = reviewService.saveReview(review);
        return mapperFacade.map(review, ReviewDto.class);
    }

    @DeleteMapping("/{id}")
    public ReviewDto deleteReview(@PathVariable Long id) {
        Review review = reviewService.validateAndGetReview(id);
        reviewService.saveReview(review);
        return mapperFacade.map(review, ReviewDto.class);
    }

}

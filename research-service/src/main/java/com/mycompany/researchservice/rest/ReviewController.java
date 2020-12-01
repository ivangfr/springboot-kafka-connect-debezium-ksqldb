package com.mycompany.researchservice.rest;

import com.mycompany.researchservice.mapper.ReviewMapper;
import com.mycompany.researchservice.model.Review;
import com.mycompany.researchservice.rest.dto.CreateReviewDto;
import com.mycompany.researchservice.rest.dto.ReviewDto;
import com.mycompany.researchservice.rest.dto.UpdateReviewDto;
import com.mycompany.researchservice.service.ReviewService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @GetMapping("/{id}")
    public ReviewDto getReview(@PathVariable Long id) {
        Review review = reviewService.validateAndGetReview(id);
        return reviewMapper.toReviewDto(review);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ReviewDto createReview(@Valid @RequestBody CreateReviewDto createReviewDto) {
        Review review = reviewMapper.toReview(createReviewDto);
        review = reviewService.saveReview(review);
        return reviewMapper.toReviewDto(review);
    }

    @PatchMapping("/{id}")
    public ReviewDto updateReview(@PathVariable Long id, @Valid @RequestBody UpdateReviewDto updateReviewDto) {
        Review review = reviewService.validateAndGetReview(id);
        reviewMapper.updateReviewFromDto(updateReviewDto, review);
        review = reviewService.saveReview(review);
        return reviewMapper.toReviewDto(review);
    }

    @DeleteMapping("/{id}")
    public ReviewDto deleteReview(@PathVariable Long id) {
        Review review = reviewService.validateAndGetReview(id);
        reviewService.deleteReview(review);
        return reviewMapper.toReviewDto(review);
    }

}

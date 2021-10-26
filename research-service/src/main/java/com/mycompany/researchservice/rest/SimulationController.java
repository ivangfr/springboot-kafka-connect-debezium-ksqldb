package com.mycompany.researchservice.rest;

import com.mycompany.researchservice.model.Article;
import com.mycompany.researchservice.model.Researcher;
import com.mycompany.researchservice.model.Review;
import com.mycompany.researchservice.rest.dto.RandomReviewsRequest;
import com.mycompany.researchservice.service.ArticleService;
import com.mycompany.researchservice.service.ResearcherService;
import com.mycompany.researchservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/simulation")
public class SimulationController {

    @Value("${simulation.reviews.total}")
    private Integer total;

    @Value("${simulation.reviews.sleep}")
    private Integer sleep;

    private final ArticleService articleService;
    private final ResearcherService researcherService;
    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public List<Long> createRandomOrders(@RequestBody RandomReviewsRequest randomReviewsRequest) throws InterruptedException {
        total = randomReviewsRequest.getTotal() == null ? total : randomReviewsRequest.getTotal();
        sleep = randomReviewsRequest.getSleep() == null ? sleep : randomReviewsRequest.getSleep();

        log.info("## Running review simulation - total: {}, sleep: {}", total, sleep);

        List<Long> reviewIds = new ArrayList<>();
        List<Article> articles = articleService.getAllArticles();
        List<Researcher> researchers = researcherService.getAllResearchers();

        for (int i = 0; i < total; i++) {
            Article article = articles.get(random.nextInt(articles.size()));
            Researcher researcher = researchers.get(random.nextInt(researchers.size()));

            Review review = new Review();
            review.setArticle(article);
            review.setResearcher(researcher);
            review.setComment(generateComment());
            review = reviewService.saveReview(review);
            log.info("Review created: {}", review);

            reviewIds.add(review.getId());

            Thread.sleep(sleep);
        }

        log.info("## Review simulation finished successfully!");

        return reviewIds;
    }

    private final Random random = new SecureRandom();

    private String generateComment() {
        return String.format("Ln %s: %s", random.nextInt(150) + 1, phases.get(random.nextInt(phases.size())));
    }

    private static final List<String> phases = Arrays.asList(
            "Excellent!", "Very good!", "I didn't like it.", "You should improve it.", "A coma is missing here.",
            "There are many typos!", "There are a lot of mistakes", "I didn't like the Verdana font",
            "What do you mean here?", "Can you rephrase it?", "Why are you using passive?", "'there is' not 'there are'");
}

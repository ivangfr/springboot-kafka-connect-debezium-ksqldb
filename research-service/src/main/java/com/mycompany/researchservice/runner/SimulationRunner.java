package com.mycompany.researchservice.runner;

import com.mycompany.researchservice.model.Article;
import com.mycompany.researchservice.model.Researcher;
import com.mycompany.researchservice.model.Review;
import com.mycompany.researchservice.service.ArticleService;
import com.mycompany.researchservice.service.ResearcherService;
import com.mycompany.researchservice.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
@Profile("simulation")
@Component
@Order(2)
public class SimulationRunner implements CommandLineRunner {

    @Value("${simulation.reviews.total}")
    private Integer total;

    @Value("${simulation.reviews.sleep}")
    private Integer sleep;

    private final Random random = new Random();

    private final ArticleService articleService;
    private final ResearcherService researcherService;
    private final ReviewService reviewService;

    public SimulationRunner(ArticleService articleService, ResearcherService researcherService, ReviewService reviewService) {
        this.articleService = articleService;
        this.researcherService = researcherService;
        this.reviewService = reviewService;
    }

    @Override
    public void run(String... args) throws InterruptedException {

        log.info("Running review simulation ...");

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

            Thread.sleep(sleep);
        }

        log.info("Review simulation finished!");
    }

    private String generateComment() {
        return String.format("Ln %s: %s", random.nextInt(150) + 1, phases.get(random.nextInt(phases.size())));
    }

    private static final List<String> phases = Arrays.asList(
            "Excellent!", "Very good!", "I didn't like it.", "You should improve it.", "A coma is missing here.",
            "There are many typos!", "There are a lot of mistakes", "I didn't like the Verdana font",
            "What do you mean here?", "Can you rephrase it?", "Why are you using passive?", "'there is' not 'there are'");

}

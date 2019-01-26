package com.mycompany.researchservice.runner;

import com.mycompany.researchservice.model.Article;
import com.mycompany.researchservice.model.Institute;
import com.mycompany.researchservice.model.Researcher;
import com.mycompany.researchservice.model.Review;
import com.mycompany.researchservice.service.ArticleService;
import com.mycompany.researchservice.service.InstituteService;
import com.mycompany.researchservice.service.ResearcherService;
import com.mycompany.researchservice.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
@Profile("simulation")
@Component
public class SimulationRunner implements CommandLineRunner {

    private final InstituteService instituteService;
    private final ArticleService articleService;
    private final ResearcherService researcherService;
    private final ReviewService reviewService;

    @Value("${reviews.total}")
    private Integer reviewsTotal;

    @Value("${reviews.delay-interval}")
    private Integer reviewsDelayInterval;

    public SimulationRunner(InstituteService instituteService, ArticleService articleService, ResearcherService researcherService, ReviewService reviewService) {
        this.instituteService = instituteService;
        this.articleService = articleService;
        this.researcherService = researcherService;
        this.reviewService = reviewService;
    }

    @Override
    public void run(String... args) {

        List<Article> articles = articleService.getAllArticles();
        if (articles.isEmpty()) {
            articleTitles.forEach(articleTitle -> {
                Article article = new Article();
                article.setTitle(articleTitle);
                article = articleService.saveArticle(article);
                articles.add(article);

                log.info("Article created: {}", article);
            });
        }

        List<Institute> institutes = instituteService.getAllInstitutes();
        if (institutes.isEmpty()) {
            instituteNames.forEach(instituteName -> {
                Institute institute = new Institute();
                institute.setName(instituteName);
                institute = instituteService.saveInstitute(institute);
                institutes.add(institute);

                log.info("Institute created: {}", institute);
            });
        }

        List<Researcher> researchers = researcherService.getAllResearchers();
        if (researchers.isEmpty()) {
            researcherNames.forEach(researcherName -> {
                String[] firstLastName = researcherName.split(" ");
                Researcher researcher = new Researcher();
                researcher.setFirstName(firstLastName[0]);
                researcher.setLastName(firstLastName[1]);
                researcher.setInstitute(institutes.get(random.nextInt(institutes.size())));
                researcher = researcherService.saveResearchers(researcher);
                researchers.add(researcher);

                log.info("Researcher created: {}", researcher);
            });
        }

        for (int i = 0; i < reviewsTotal; i++) {
            Article article = articles.get(random.nextInt(articles.size()));
            Researcher researcher = researchers.get(random.nextInt(researchers.size()));

            Review review = new Review();
            review.setArticle(article);
            review.setResearcher(researcher);
            review.setComment(comments.get(random.nextInt(comments.size())));
            review = reviewService.saveReview(review);

            log.info("Review created: {}", review);

            try {
                Thread.sleep(reviewsDelayInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private final Random random = new Random();

    private static final List<String> articleTitles = Arrays.asList(
            "Advantages of using KSQL over Kafka Streams", "Advantages of using Spring over Play",
            "Learn C++ in 10 minutes", "Learn Python in 5 minutes", "Why you should use Debezium",
            "How to use KStreams and KTable correctly", "Learn Kafka for Dummies", "Why Java is so popular",
            "10 tips to develop your programming skills", "Tutorial about Hashicorp Consul",
            "Tutorial about Hashicorp Vault", "Tutorial about Hashicorp Packer", "Mesos/Marathon/Chronos How to");

    private static final List<String> instituteNames = Arrays.asList(
            "UCSF", "MIT", "USP", "UFSCar", "INESC TEC", "UNICAMP", "UFRJ");

    private static final List<String> comments = Arrays.asList(
            "Excellent article!", "Very good!", "I didn't like the conclusion.",
            "I'd suggest to improve the introduction.", "I didn't like it at all!", "The worst article so far!",
            "It explains very well the problem!", "There are many typos!", "The images are too small!",
            "There are a lot of mistakes", "I didn't like the font of the letter");

    private static final List<String> researcherNames = Arrays.asList(
            "Ivan Franchin", "John Gates", "Mark Bacon", "Alex Stone", "Susan Spice", "Peter Lopes", "Mikael Lopes",
            "Renato Souza", "Paul Schneider", "Tobias Bohn", "John Star", "Rick Sander", "Nakito Hashi", "Kyo Lo");

}

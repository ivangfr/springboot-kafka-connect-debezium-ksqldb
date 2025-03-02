package com.ivanfranchin.researchservice.runner;

import com.ivanfranchin.researchservice.researcher.ResearcherService;
import com.ivanfranchin.researchservice.article.model.Article;
import com.ivanfranchin.researchservice.institute.model.Institute;
import com.ivanfranchin.researchservice.researcher.model.Researcher;
import com.ivanfranchin.researchservice.article.ArticleService;
import com.ivanfranchin.researchservice.institute.InstituteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoadSamples implements CommandLineRunner {

    @Value("${load-samples.articles.enabled}")
    private boolean articleSamples;

    @Value("${load-samples.institutes.enabled}")
    private boolean instituteSamples;

    @Value("${load-samples.researchers.enabled}")
    private boolean researcherSamples;

    private final InstituteService instituteService;
    private final ArticleService articleService;
    private final ResearcherService researcherService;

    @Override
    public void run(String... args) {

        if (articleSamples || instituteSamples || researcherSamples) {

            log.info("## Start loading samples of articles, institutes and researchers ...");

            if (articleSamples && articleService.getAllArticles().isEmpty()) {
                articleTitles.forEach(articleTitle -> {
                    Article article = new Article();
                    article.setTitle(articleTitle);
                    article = articleService.saveArticle(article);

                    log.info("Article created: {}", article);
                });
            }

            if (instituteSamples) {
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

                if (researcherSamples && researcherService.getAllResearchers().isEmpty()) {
                    researcherNames.forEach(researcherName -> {
                        String[] firstLastName = researcherName.split(" ");
                        Researcher researcher = new Researcher();
                        researcher.setFirstName(firstLastName[0]);
                        researcher.setLastName(firstLastName[1]);
                        researcher.setInstitute(institutes.get(random.nextInt(institutes.size())));
                        researcher = researcherService.saveResearchers(researcher);

                        log.info("Researcher created: {}", researcher);
                    });
                }
            }

            log.info("## Finished successfully loading samples of articles, institutes and researchers!");
        }
    }

    private final Random random = new SecureRandom();

    private static final List<String> articleTitles = Arrays.asList(
            "Learn C++ in 10 days", "Learn Python in 5 days", "Why you should use Debezium",
            "How to use KStreams and KTable", "Learn Kafka for Dummies", "Why Java is so popular",
            "10 tips to improve your coding", "Tutorial about Consul", "Tutorial about Vault",
            "Tutorial about Packer", "Mesos in a Nutshell", "Kubernetes in a Nutshell");

    private static final List<String> instituteNames = Arrays.asList(
            "UCSF", "MIT", "USP", "UFSCar", "INESC TEC", "UNICAMP", "UFRJ");

    private static final List<String> researcherNames = Arrays.asList(
            "John Gates", "Mark Bacon", "Alex Stone", "Susan Spice", "Peter Lopes", "Mikael Lopes", "Renato Souza",
            "Paul Schneider", "Tobias Bohn", "John Star", "Rick Sander", "Nakito Hashi", "Kyo Lo", "David Cube");
}

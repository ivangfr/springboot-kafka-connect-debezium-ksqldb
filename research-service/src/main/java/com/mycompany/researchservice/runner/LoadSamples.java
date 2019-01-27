package com.mycompany.researchservice.runner;

import com.mycompany.researchservice.model.Article;
import com.mycompany.researchservice.model.Institute;
import com.mycompany.researchservice.model.Researcher;
import com.mycompany.researchservice.service.ArticleService;
import com.mycompany.researchservice.service.InstituteService;
import com.mycompany.researchservice.service.ResearcherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
@Order(1)
public class LoadSamples implements CommandLineRunner {

    private final InstituteService instituteService;
    private final ArticleService articleService;
    private final ResearcherService researcherService;

    public LoadSamples(InstituteService instituteService, ArticleService articleService, ResearcherService researcherService) {
        this.instituteService = instituteService;
        this.articleService = articleService;
        this.researcherService = researcherService;
    }

    private final Random random = new Random();

    @Override
    public void run(String... args) {

        log.info("Loading articles, institutes and researchers samples ...");

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

        log.info("Loading samples finished!");
    }

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

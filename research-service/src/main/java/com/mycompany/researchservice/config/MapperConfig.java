package com.mycompany.researchservice.config;

import com.mycompany.researchservice.rest.dto.CreateResearcherDto;
import com.mycompany.researchservice.rest.dto.CreateReviewDto;
import com.mycompany.researchservice.rest.dto.ResearcherDto;
import com.mycompany.researchservice.rest.dto.ReviewDto;
import com.mycompany.researchservice.rest.dto.UpdateArticleDto;
import com.mycompany.researchservice.rest.dto.UpdateInstituteDto;
import com.mycompany.researchservice.rest.dto.UpdateResearcherDto;
import com.mycompany.researchservice.model.Article;
import com.mycompany.researchservice.model.Institute;
import com.mycompany.researchservice.model.Researcher;
import com.mycompany.researchservice.model.Review;
import com.mycompany.researchservice.service.ArticleService;
import com.mycompany.researchservice.service.InstituteService;
import com.mycompany.researchservice.service.ResearcherService;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    private final InstituteService instituteService;
    private final ResearcherService researcherService;
    private final ArticleService articleService;

    public MapperConfig(InstituteService instituteService, ResearcherService researcherService, ArticleService articleService) {
        this.instituteService = instituteService;
        this.researcherService = researcherService;
        this.articleService = articleService;
    }

    @Bean
    MapperFactory mapperFactory() {
        DefaultMapperFactory defaultMapperFactory = new DefaultMapperFactory.Builder().useAutoMapping(true).build();

        // --
        // Institute
        defaultMapperFactory.classMap(UpdateInstituteDto.class, Institute.class).mapNulls(false).byDefault().register();

        // --
        // Article
        defaultMapperFactory.classMap(UpdateArticleDto.class, Article.class).mapNulls(false).byDefault().register();

        // --
        // Researcher
        defaultMapperFactory.classMap(CreateResearcherDto.class, Researcher.class)
                .byDefault()
                .customize(new CustomMapper<CreateResearcherDto, Researcher>() {
                    @Override
                    public void mapAtoB(CreateResearcherDto createResearcherDto, Researcher researcher, MappingContext context) {
                        super.mapAtoB(createResearcherDto, researcher, context);
                        Institute institute = instituteService.validateAndGetInstitute(createResearcherDto.getInstituteId());
                        researcher.setInstitute(institute);
                    }
                }).register();

        defaultMapperFactory.classMap(UpdateResearcherDto.class, Researcher.class)
                .mapNulls(false)
                .byDefault()
                .customize(new CustomMapper<UpdateResearcherDto, Researcher>() {
                    @Override
                    public void mapAtoB(UpdateResearcherDto updateResearcherDto, Researcher researcher, MappingContext context) {
                        super.mapAtoB(updateResearcherDto, researcher, context);

                        Long dtoInstituteId = updateResearcherDto.getInstituteId();
                        if (dtoInstituteId != null) {
                            Institute institute = instituteService.validateAndGetInstitute(dtoInstituteId);
                            researcher.setInstitute(institute);
                        }
                    }
                }).register();

        defaultMapperFactory.classMap(Researcher.class, ResearcherDto.class)
                .field("institute.id", "instituteId")
                .byDefault()
                .register();

        // --
        // Review
        defaultMapperFactory.classMap(CreateReviewDto.class, Review.class)
                .byDefault()
                .customize(new CustomMapper<CreateReviewDto, Review>() {
                    @Override
                    public void mapAtoB(CreateReviewDto createReviewDto, Review review, MappingContext context) {
                        super.mapAtoB(createReviewDto, review, context);
                        Researcher researcher = researcherService.validateAndGetResearcher(createReviewDto.getResearcherId());
                        review.setResearcher(researcher);

                        Article article = articleService.validateAndGetArticle(createReviewDto.getArticleId());
                        review.setArticle(article);
                    }
                }).register();

        defaultMapperFactory.classMap(Review.class, ReviewDto.class)
                .field("article.id", "articleId")
                .field("researcher.id", "researcherId")
                .byDefault()
                .register();

        return defaultMapperFactory;
    }

    @Bean
    MapperFacade mapperFacade() {
        return mapperFactory().getMapperFacade();
    }

}

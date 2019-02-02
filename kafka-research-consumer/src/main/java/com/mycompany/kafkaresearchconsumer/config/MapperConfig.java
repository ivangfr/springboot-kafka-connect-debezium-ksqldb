package com.mycompany.kafkaresearchconsumer.config;

import com.mycompany.kafkaresearchconsumer.model.Review;
import com.mycompany.research.avro.ReviewMessage;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    MapperFactory mapperFactory() {
        DefaultMapperFactory defaultMapperFactory = new DefaultMapperFactory.Builder()
                .useAutoMapping(true)
                .build();

        defaultMapperFactory.classMap(ReviewMessage.class, Review.class)
                .byDefault()
                .mapNulls(false)
                .customize(new CustomMapper<ReviewMessage, Review>() {
                    @Override
                    public void mapAtoB(ReviewMessage reviewMessage, Review review, MappingContext context) {
                        super.mapAtoB(reviewMessage, review, context);
                        review.setReviewId(reviewMessage.getREVIEWID());
                        review.setArticleId(reviewMessage.getARTICLEID());
                        review.setArticleTitle(reviewMessage.getARTICLETITLE().toString());
                        review.setReviewerId(reviewMessage.getREVIEWERID());
                        review.setReviewerFirstName(reviewMessage.getREVIEWERFIRSTNAME().toString());
                        review.setReviewerLastName(reviewMessage.getREVIEWERLASTNAME().toString());
                        review.setInstituteId(reviewMessage.getINSTITUTEID());
                        review.setInstituteName(reviewMessage.getINSTITUTENAME().toString());
                        review.setComment(reviewMessage.getCOMMENT().toString());
                        review.setCreatedAt(Long.valueOf(reviewMessage.getCREATEDAT().toString()));
                    }
                }).register();

        return defaultMapperFactory;
    }

    @Bean
    MapperFacade mapperFacade() {
        return mapperFactory().getMapperFacade();
    }

}

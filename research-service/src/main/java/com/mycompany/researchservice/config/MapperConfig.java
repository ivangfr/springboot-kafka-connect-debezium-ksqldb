package com.mycompany.researchservice.config;

import com.mycompany.researchservice.dto.CreateResearcherDto;
import com.mycompany.researchservice.dto.UpdateInstituteDto;
import com.mycompany.researchservice.dto.UpdateResearcherDto;
import com.mycompany.researchservice.model.Institute;
import com.mycompany.researchservice.model.Researcher;
import com.mycompany.researchservice.service.InstituteService;
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

    public MapperConfig(InstituteService instituteService) {
        this.instituteService = instituteService;
    }

    @Bean
    MapperFactory mapperFactory() {
        DefaultMapperFactory defaultMapperFactory = new DefaultMapperFactory.Builder().useAutoMapping(true).build();

        // --
        // Institute
        defaultMapperFactory.classMap(UpdateInstituteDto.class, Institute.class).mapNulls(false).byDefault().register();

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

        return defaultMapperFactory;
    }

    @Bean
    MapperFacade mapperFacade() {
        return mapperFactory().getMapperFacade();
    }

}

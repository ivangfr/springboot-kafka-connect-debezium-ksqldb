package com.mycompany.researchservice.mapper;

import com.mycompany.researchservice.model.Researcher;
import com.mycompany.researchservice.rest.dto.CreateResearcherRequest;
import com.mycompany.researchservice.rest.dto.ResearcherResponse;
import com.mycompany.researchservice.rest.dto.UpdateResearcherRequest;
import com.mycompany.researchservice.service.InstituteService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = InstituteService.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ResearcherMapper {

    @Mapping(source = "institute.id", target = "instituteId")
    ResearcherResponse toResearcherResponse(Researcher researcher);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "institute", source = "instituteId")
    Researcher toResearcher(CreateResearcherRequest createResearcherRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "institute", source = "instituteId")
    void updateResearcherFromRequest(UpdateResearcherRequest updateResearcherRequest, @MappingTarget Researcher researcher);
}

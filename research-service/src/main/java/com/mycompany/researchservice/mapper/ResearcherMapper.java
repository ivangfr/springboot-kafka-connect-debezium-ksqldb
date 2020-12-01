package com.mycompany.researchservice.mapper;

import com.mycompany.researchservice.model.Researcher;
import com.mycompany.researchservice.rest.dto.CreateResearcherDto;
import com.mycompany.researchservice.rest.dto.ResearcherDto;
import com.mycompany.researchservice.rest.dto.UpdateResearcherDto;
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
    ResearcherDto toResearcherDto(Researcher researcher);

    @Mapping(target = "institute", source = "instituteId")
    Researcher toResearcher(CreateResearcherDto createResearcherDto);

    @Mapping(target = "institute", source = "instituteId")
    void updateResearcherFromDto(UpdateResearcherDto updateResearcherDto, @MappingTarget Researcher researcher);

}

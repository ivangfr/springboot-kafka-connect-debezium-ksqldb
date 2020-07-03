package com.mycompany.researchservice.mapper;

import com.mycompany.researchservice.model.Researcher;
import com.mycompany.researchservice.rest.dto.CreateResearcherDto;
import com.mycompany.researchservice.rest.dto.ResearcherDto;
import com.mycompany.researchservice.rest.dto.UpdateResearcherDto;
import com.mycompany.researchservice.service.InstituteService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = InstituteService.class
)
public abstract class ResearcherMapper {

    @Autowired
    protected InstituteService instituteService;

    @Mapping(source = "institute.id", target = "instituteId")
    public abstract ResearcherDto toResearcherDto(Researcher researcher);

    @Mapping(
            target = "institute",
            expression = "java( instituteService.validateAndGetInstitute(createResearcherDto.getInstituteId()) )"
    )
    public abstract Researcher toResearcher(CreateResearcherDto createResearcherDto);

    @Mapping(
            target = "institute",
            expression = "java( updateResearcherDto.getInstituteId() != null ? instituteService.validateAndGetInstitute(updateResearcherDto.getInstituteId()) : researcher.getInstitute() )"
    )
    public abstract void updateResearcherFromDto(UpdateResearcherDto updateResearcherDto, @MappingTarget Researcher researcher);

}

package com.mycompany.researchservice.mapper;

import com.mycompany.researchservice.model.Institute;
import com.mycompany.researchservice.model.Researcher;
import com.mycompany.researchservice.rest.dto.CreateResearcherDto;
import com.mycompany.researchservice.rest.dto.ResearcherDto;
import com.mycompany.researchservice.rest.dto.UpdateResearcherDto;
import com.mycompany.researchservice.service.InstituteService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = InstituteService.class
)
public abstract class ResearcherMapper {

    @Autowired
    private InstituteService instituteService;

    @Mapping(source = "institute.id", target = "instituteId")
    public abstract ResearcherDto toResearcherDto(Researcher researcher);

    public Researcher toResearcher(CreateResearcherDto createResearcherDto) {
        Researcher researcher = new Researcher();
        researcher.setFirstName(createResearcherDto.getFirstName());
        researcher.setLastName(createResearcherDto.getLastName());
        Institute institute = instituteService.validateAndGetInstitute(createResearcherDto.getInstituteId());
        researcher.setInstitute(institute);
        return researcher;
    }

    public void updateResearcherFromDto(UpdateResearcherDto updateResearcherDto, Researcher researcher) {
        String firstName = updateResearcherDto.getFirstName();
        if (firstName != null) {
            researcher.setFirstName(firstName);
        }

        String lastName = updateResearcherDto.getLastName();
        if (lastName != null) {
            researcher.setLastName(lastName);
        }

        Long dtoInstituteId = updateResearcherDto.getInstituteId();
        if (dtoInstituteId != null) {
            Institute institute = instituteService.validateAndGetInstitute(dtoInstituteId);
            researcher.setInstitute(institute);
        }
    }

}

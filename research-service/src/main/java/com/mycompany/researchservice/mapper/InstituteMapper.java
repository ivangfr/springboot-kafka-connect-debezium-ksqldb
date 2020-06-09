package com.mycompany.researchservice.mapper;

import com.mycompany.researchservice.model.Institute;
import com.mycompany.researchservice.rest.dto.CreateInstituteDto;
import com.mycompany.researchservice.rest.dto.InstituteDto;
import com.mycompany.researchservice.rest.dto.UpdateInstituteDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface InstituteMapper {

    Institute toInstitute(CreateInstituteDto createInstituteDto);

    InstituteDto toInstituteDto(Institute institute);

    void updateInstituteFromDto(UpdateInstituteDto updateInstituteDto, @MappingTarget Institute institute);

}

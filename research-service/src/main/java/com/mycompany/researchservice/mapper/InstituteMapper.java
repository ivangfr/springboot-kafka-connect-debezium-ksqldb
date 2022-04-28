package com.mycompany.researchservice.mapper;

import com.mycompany.researchservice.model.Institute;
import com.mycompany.researchservice.rest.dto.CreateInstituteRequest;
import com.mycompany.researchservice.rest.dto.InstituteResponse;
import com.mycompany.researchservice.rest.dto.UpdateInstituteRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface InstituteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Institute toInstitute(CreateInstituteRequest createInstituteRequest);

    InstituteResponse toInstituteResponse(Institute institute);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateInstituteFromRequest(UpdateInstituteRequest updateInstituteRequest, @MappingTarget Institute institute);
}

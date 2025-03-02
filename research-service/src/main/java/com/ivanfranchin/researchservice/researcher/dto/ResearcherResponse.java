package com.ivanfranchin.researchservice.researcher.dto;

import com.ivanfranchin.researchservice.researcher.model.Researcher;

import java.time.LocalDateTime;

public record ResearcherResponse(Long id,
                                 String firstName,
                                 String lastName,
                                 Long instituteId,
                                 LocalDateTime createdAt) {

    public static ResearcherResponse from(Researcher researcher) {
        return new ResearcherResponse(
                researcher.getId(),
                researcher.getFirstName(),
                researcher.getLastName(),
                researcher.getInstitute().getId(),
                researcher.getCreatedAt());
    }
}

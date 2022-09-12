package com.ivanfranchin.researchservice.rest.dto;

import java.time.LocalDateTime;

public record ResearcherResponse(Long id, String firstName, String lastName, Long instituteId,
                                 LocalDateTime createdAt) {
}

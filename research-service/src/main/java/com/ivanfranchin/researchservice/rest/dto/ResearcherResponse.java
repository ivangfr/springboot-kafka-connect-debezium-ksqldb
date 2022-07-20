package com.ivanfranchin.researchservice.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResearcherResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private Long instituteId;
    private LocalDateTime createdAt;
}

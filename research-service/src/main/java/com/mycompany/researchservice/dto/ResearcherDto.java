package com.mycompany.researchservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResearcherDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Long instituteId;
    private LocalDateTime createdAt;

}

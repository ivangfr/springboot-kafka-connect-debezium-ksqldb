package com.ivanfranchin.researchservice.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InstituteResponse {

    private Long id;
    private String name;
    private LocalDateTime createdAt;
}

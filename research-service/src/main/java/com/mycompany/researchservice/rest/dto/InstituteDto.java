package com.mycompany.researchservice.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InstituteDto {

    private Long id;
    private String name;
    private LocalDateTime createdAt;

}

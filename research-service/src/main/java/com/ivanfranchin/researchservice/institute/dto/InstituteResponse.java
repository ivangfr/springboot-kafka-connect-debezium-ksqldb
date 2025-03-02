package com.ivanfranchin.researchservice.institute.dto;

import com.ivanfranchin.researchservice.institute.model.Institute;

import java.time.LocalDateTime;

public record InstituteResponse(Long id, String name, LocalDateTime createdAt) {

    public static InstituteResponse from(Institute institute) {
        return new InstituteResponse(institute.getId(), institute.getName(), institute.getCreatedAt());
    }
}

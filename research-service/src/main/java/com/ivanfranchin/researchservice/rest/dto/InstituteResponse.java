package com.ivanfranchin.researchservice.rest.dto;

import java.time.LocalDateTime;

public record InstituteResponse(Long id, String name, LocalDateTime createdAt) {
}

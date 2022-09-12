package com.ivanfranchin.researchservice.rest.dto;

public record ReviewResponse(Long id, Long researcherId, Long articleId, String comment) {
}

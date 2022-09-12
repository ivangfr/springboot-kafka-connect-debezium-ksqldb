package com.ivanfranchin.researchservice.rest.dto;

import java.time.LocalDateTime;

public record ArticleResponse(Long id, String title, LocalDateTime createdAt) {
}

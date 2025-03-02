package com.ivanfranchin.researchservice.institute.model;

import com.ivanfranchin.researchservice.institute.dto.CreateInstituteRequest;
import com.ivanfranchin.researchservice.institute.dto.UpdateInstituteRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "institutes")
public class Institute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onPrePersist() {
        createdAt = updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public static Institute from(CreateInstituteRequest createInstituteRequest) {
        Institute institute = new Institute();
        institute.setName(createInstituteRequest.name());
        return institute;
    }

    public static void updateFrom(UpdateInstituteRequest updateInstituteRequest, Institute institute) {
        if (updateInstituteRequest.name() != null) {
            institute.setName(updateInstituteRequest.name());
        }
    }
}

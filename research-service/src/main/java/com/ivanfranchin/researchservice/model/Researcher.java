package com.ivanfranchin.researchservice.model;

import com.ivanfranchin.researchservice.rest.dto.CreateResearcherRequest;
import com.ivanfranchin.researchservice.rest.dto.UpdateResearcherRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "researchers")
public class Researcher {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institute_id", nullable = false, foreignKey = @ForeignKey(name = "FK_INSTITUTE"))
    private Institute institute;

    @PrePersist
    public void onPrePersist() {
        createdAt = updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public static Researcher from(CreateResearcherRequest createResearcherRequest) {
        Researcher researcher = new Researcher();
        researcher.setFirstName(createResearcherRequest.firstName());
        researcher.setLastName(createResearcherRequest.lastName());
        return researcher;
    }

    public static void updateFrom(UpdateResearcherRequest updateResearcherRequest, Researcher researcher) {
        if (updateResearcherRequest.firstName() != null) {
            researcher.setFirstName(updateResearcherRequest.firstName());
        }
        if (updateResearcherRequest.lastName() != null) {
            researcher.setLastName(updateResearcherRequest.lastName());
        }
    }
}

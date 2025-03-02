package com.ivanfranchin.researchservice.researcher;

import com.ivanfranchin.researchservice.researcher.dto.CreateResearcherRequest;
import com.ivanfranchin.researchservice.researcher.dto.ResearcherResponse;
import com.ivanfranchin.researchservice.researcher.dto.UpdateResearcherRequest;
import com.ivanfranchin.researchservice.researcher.model.Researcher;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/researchers")
public class ResearcherController {

    private final ResearcherService researcherService;

    @GetMapping
    public List<ResearcherResponse> getAllResearchers() {
        return researcherService.getAllResearchers()
                .stream()
                .map(ResearcherResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    public ResearcherResponse getResearcher(@PathVariable Long id) {
        Researcher researcher = researcherService.validateAndGetResearcher(id);
        return ResearcherResponse.from(researcher);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResearcherResponse createResearcher(@Valid @RequestBody CreateResearcherRequest createResearcherRequest) {
        Researcher researcher = researcherService.createResearcherFrom(createResearcherRequest);
        researcher = researcherService.saveResearchers(researcher);
        return ResearcherResponse.from(researcher);
    }

    @PatchMapping("/{id}")
    public ResearcherResponse updateResearcher(@PathVariable Long id, @Valid @RequestBody UpdateResearcherRequest updateResearcherRequest) {
        Researcher researcher = researcherService.validateAndGetResearcher(id);
        researcherService.updateResearcherFrom(updateResearcherRequest, researcher);
        researcher = researcherService.saveResearchers(researcher);
        return ResearcherResponse.from(researcher);
    }

    @DeleteMapping("/{id}")
    public ResearcherResponse deleteResearcher(@PathVariable Long id) {
        Researcher researcher = researcherService.validateAndGetResearcher(id);
        researcherService.deleteResearcher(researcher);
        return ResearcherResponse.from(researcher);
    }
}

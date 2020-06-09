package com.mycompany.researchservice.rest;

import com.mycompany.researchservice.mapper.ResearcherMapper;
import com.mycompany.researchservice.model.Researcher;
import com.mycompany.researchservice.rest.dto.CreateResearcherDto;
import com.mycompany.researchservice.rest.dto.ResearcherDto;
import com.mycompany.researchservice.rest.dto.UpdateResearcherDto;
import com.mycompany.researchservice.service.ResearcherService;
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

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/researchers")
public class ResearcherController {

    private final ResearcherService researcherService;
    private final ResearcherMapper researcherMapper;

    @GetMapping
    public List<ResearcherDto> getAllResearchers() {
        return researcherService.getAllResearchers()
                .stream()
                .map(researcherMapper::toResearcherDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResearcherDto getResearcher(@PathVariable Long id) {
        Researcher researcher = researcherService.validateAndGetResearcher(id);
        return researcherMapper.toResearcherDto(researcher);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResearcherDto createResearcher(@Valid @RequestBody CreateResearcherDto createResearcherDto) {
        Researcher researcher = researcherMapper.toResearcher(createResearcherDto);
        researcher = researcherService.saveResearchers(researcher);
        return researcherMapper.toResearcherDto(researcher);
    }

    @PatchMapping("/{id}")
    public ResearcherDto updateResearcher(@PathVariable Long id, @Valid @RequestBody UpdateResearcherDto updateResearcherDto) {
        Researcher researcher = researcherService.validateAndGetResearcher(id);
        researcherMapper.updateResearcherFromDto(updateResearcherDto, researcher);
        researcher = researcherService.saveResearchers(researcher);
        return researcherMapper.toResearcherDto(researcher);
    }

    @DeleteMapping("/{id}")
    public ResearcherDto deleteResearcher(@PathVariable Long id) {
        Researcher researcher = researcherService.validateAndGetResearcher(id);
        researcherService.deleteResearcher(researcher);
        return researcherMapper.toResearcherDto(researcher);
    }

}

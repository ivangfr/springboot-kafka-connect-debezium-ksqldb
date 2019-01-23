package com.mycompany.researchservice.controller;

import com.mycompany.researchservice.dto.CreateResearcherDto;
import com.mycompany.researchservice.dto.ResearcherDto;
import com.mycompany.researchservice.dto.UpdateResearcherDto;
import com.mycompany.researchservice.model.Researcher;
import com.mycompany.researchservice.service.ResearcherService;
import ma.glasnost.orika.MapperFacade;
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

@RestController
@RequestMapping("/api/researchers")
public class ResearcherController {

    private final ResearcherService researcherService;
    private final MapperFacade mapperFacade;

    public ResearcherController(ResearcherService researcherService, MapperFacade mapperFacade) {
        this.researcherService = researcherService;
        this.mapperFacade = mapperFacade;
    }

    @GetMapping
    public List<ResearcherDto> getAllResearchers() {
        return researcherService.getAllResearchers()
                .stream()
                .map(researcher -> mapperFacade.map(researcher, ResearcherDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResearcherDto getResearcher(@PathVariable Long id) {
        Researcher researcher = researcherService.validateAndGetResearcher(id);
        return mapperFacade.map(researcher, ResearcherDto.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResearcherDto createResearcher(@Valid @RequestBody CreateResearcherDto createResearcherDto) {
        Researcher researcher = mapperFacade.map(createResearcherDto, Researcher.class);
        researcher = researcherService.saveResearchers(researcher);
        return mapperFacade.map(researcher, ResearcherDto.class);
    }

    @PatchMapping("/{id}")
    public ResearcherDto updateResearcher(@PathVariable Long id, @Valid @RequestBody UpdateResearcherDto updateResearcherDto) {
        Researcher researcher = researcherService.validateAndGetResearcher(id);
        mapperFacade.map(updateResearcherDto, researcher);
        researcher = researcherService.saveResearchers(researcher);
        return mapperFacade.map(researcher, ResearcherDto.class);
    }

    @DeleteMapping("/{id}")
    public ResearcherDto deleteResearcher(@PathVariable Long id) {
        Researcher researcher = researcherService.validateAndGetResearcher(id);
        researcherService.deleteResearcher(researcher);
        return mapperFacade.map(researcher, ResearcherDto.class);
    }

}

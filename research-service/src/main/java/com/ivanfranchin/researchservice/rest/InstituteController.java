package com.ivanfranchin.researchservice.rest;

import com.ivanfranchin.researchservice.mapper.InstituteMapper;
import com.ivanfranchin.researchservice.model.Institute;
import com.ivanfranchin.researchservice.rest.dto.CreateInstituteRequest;
import com.ivanfranchin.researchservice.rest.dto.InstituteResponse;
import com.ivanfranchin.researchservice.rest.dto.UpdateInstituteRequest;
import com.ivanfranchin.researchservice.service.InstituteService;
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
@RequestMapping("/api/institutes")
public class InstituteController {

    private final InstituteService instituteService;
    private final InstituteMapper instituteMapper;

    @GetMapping
    public List<InstituteResponse> getAllInstitutes() {
        return instituteService.getAllInstitutes()
                .stream()
                .map(instituteMapper::toInstituteResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public InstituteResponse getInstitute(@PathVariable Long id) {
        Institute institute = instituteService.validateAndGetInstitute(id);
        return instituteMapper.toInstituteResponse(institute);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public InstituteResponse createInstitute(@Valid @RequestBody CreateInstituteRequest createInstituteRequest) {
        Institute institute = instituteMapper.toInstitute(createInstituteRequest);
        institute = instituteService.saveInstitute(institute);
        return instituteMapper.toInstituteResponse(institute);
    }

    @PatchMapping("/{id}")
    public InstituteResponse updateResearcher(@PathVariable Long id, @Valid @RequestBody UpdateInstituteRequest updateInstituteRequest) {
        Institute institute = instituteService.validateAndGetInstitute(id);
        instituteMapper.updateInstituteFromRequest(updateInstituteRequest, institute);
        institute = instituteService.saveInstitute(institute);
        return instituteMapper.toInstituteResponse(institute);
    }

    @DeleteMapping("/{id}")
    public InstituteResponse deleteResearcher(@PathVariable Long id) {
        Institute institute = instituteService.validateAndGetInstitute(id);
        instituteService.deleteInstitute(institute);
        return instituteMapper.toInstituteResponse(institute);
    }
}

package com.mycompany.researchservice.rest;

import com.mycompany.researchservice.rest.dto.CreateInstituteDto;
import com.mycompany.researchservice.rest.dto.InstituteDto;
import com.mycompany.researchservice.rest.dto.UpdateInstituteDto;
import com.mycompany.researchservice.model.Institute;
import com.mycompany.researchservice.service.InstituteService;
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
@RequestMapping("/api/institutes")
public class InstituteController {

    private final InstituteService instituteService;
    private final MapperFacade mapperFacade;

    public InstituteController(InstituteService instituteService, MapperFacade mapperFacade) {
        this.instituteService = instituteService;
        this.mapperFacade = mapperFacade;
    }

    @GetMapping
    public List<InstituteDto> getAllInstitutes() {
        return instituteService.getAllInstitutes()
                .stream()
                .map(institute -> mapperFacade.map(institute, InstituteDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public InstituteDto getInstitute(@PathVariable Long id) {
        Institute institute = instituteService.validateAndGetInstitute(id);
        return mapperFacade.map(institute, InstituteDto.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public InstituteDto createInstitute(@Valid @RequestBody CreateInstituteDto createInstituteDto) {
        Institute institute = mapperFacade.map(createInstituteDto, Institute.class);
        institute = instituteService.saveInstitute(institute);
        return mapperFacade.map(institute, InstituteDto.class);
    }

    @PatchMapping("/{id}")
    public InstituteDto updateResearcher(@PathVariable Long id, @Valid @RequestBody UpdateInstituteDto updateInstituteDto) {
        Institute institute = instituteService.validateAndGetInstitute(id);
        mapperFacade.map(updateInstituteDto, institute);
        institute = instituteService.saveInstitute(institute);
        return mapperFacade.map(institute, InstituteDto.class);
    }

    @DeleteMapping("/{id}")
    public InstituteDto deleteResearcher(@PathVariable Long id) {
        Institute institute = instituteService.validateAndGetInstitute(id);
        instituteService.deleteInstitute(institute);
        return mapperFacade.map(institute, InstituteDto.class);
    }

}

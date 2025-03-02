package com.ivanfranchin.researchservice.researcher;

import com.ivanfranchin.researchservice.institute.InstituteService;
import com.ivanfranchin.researchservice.institute.model.Institute;
import com.ivanfranchin.researchservice.researcher.dto.CreateResearcherRequest;
import com.ivanfranchin.researchservice.researcher.dto.UpdateResearcherRequest;
import com.ivanfranchin.researchservice.researcher.exception.ResearcherDeletionException;
import com.ivanfranchin.researchservice.researcher.exception.ResearcherNotFoundException;
import com.ivanfranchin.researchservice.researcher.model.Researcher;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ResearcherService {

    private final ResearcherRepository researcherRepository;
    private final InstituteService instituteService;

    public List<Researcher> getAllResearchers() {
        return researcherRepository.findAll();
    }

    public Researcher validateAndGetResearcher(Long id) {
        return researcherRepository.findById(id).orElseThrow(() -> new ResearcherNotFoundException(id));
    }

    public Researcher saveResearchers(Researcher researcher) {
        return researcherRepository.save(researcher);
    }

    public void deleteResearcher(Researcher researcher) {
        try {
            researcherRepository.delete(researcher);
        } catch (DataIntegrityViolationException e) {
            throw new ResearcherDeletionException(researcher.getId());
        }
    }

    public Researcher createResearcherFrom(CreateResearcherRequest createResearcherRequest) {
        Institute institute = instituteService.validateAndGetInstitute(createResearcherRequest.instituteId());

        Researcher researcher = new Researcher();
        researcher.setFirstName(createResearcherRequest.firstName());
        researcher.setLastName(createResearcherRequest.lastName());
        researcher.setInstitute(institute);
        return researcher;
    }

    public void updateResearcherFrom(UpdateResearcherRequest updateResearcherRequest, Researcher researcher) {
        if (updateResearcherRequest.firstName() != null) {
            researcher.setFirstName(updateResearcherRequest.firstName());
        }
        if (updateResearcherRequest.lastName() != null) {
            researcher.setLastName(updateResearcherRequest.lastName());
        }
        if (updateResearcherRequest.instituteId() != null) {
            Institute institute = instituteService.validateAndGetInstitute(updateResearcherRequest.instituteId());
            researcher.setInstitute(institute);
        }
    }
}

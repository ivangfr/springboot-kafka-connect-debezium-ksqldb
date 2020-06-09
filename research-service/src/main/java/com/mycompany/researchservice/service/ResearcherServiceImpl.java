package com.mycompany.researchservice.service;

import com.mycompany.researchservice.exception.InstituteDeletionException;
import com.mycompany.researchservice.exception.ResearcherNotFoundException;
import com.mycompany.researchservice.model.Researcher;
import com.mycompany.researchservice.repository.ResearcherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ResearcherServiceImpl implements ResearcherService {

    private final ResearcherRepository researcherRepository;

    @Override
    public List<Researcher> getAllResearchers() {
        return researcherRepository.findAll();
    }

    @Override
    public Researcher validateAndGetResearcher(Long id) {
        return researcherRepository.findById(id)
                .orElseThrow(() -> new ResearcherNotFoundException(String.format("Researcher with id %s not found", id)));
    }

    @Override
    public Researcher saveResearchers(Researcher researcher) {
        return researcherRepository.save(researcher);
    }

    @Override
    public void deleteResearcher(Researcher researcher) {
        try {
            researcherRepository.delete(researcher);
        } catch (DataIntegrityViolationException e) {
            throw new InstituteDeletionException(String.format("Researcher with id %s cannot be deleted", researcher.getId()));
        }
    }
}

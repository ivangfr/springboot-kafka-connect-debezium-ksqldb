package com.mycompany.researchservice.service;

import com.mycompany.researchservice.exception.ResearcherNotFoundException;
import com.mycompany.researchservice.model.Researcher;
import com.mycompany.researchservice.repository.ResearcherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResearcherServiceImpl implements ResearcherService {

    private final ResearcherRepository researcherRepository;

    public ResearcherServiceImpl(ResearcherRepository researcherRepository) {
        this.researcherRepository = researcherRepository;
    }

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
        researcherRepository.delete(researcher);
    }
}

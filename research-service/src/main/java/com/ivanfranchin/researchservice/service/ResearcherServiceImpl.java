package com.ivanfranchin.researchservice.service;

import com.ivanfranchin.researchservice.exception.ResearcherNotFoundException;
import com.ivanfranchin.researchservice.repository.ResearcherRepository;
import com.ivanfranchin.researchservice.exception.ResearcherDeletionException;
import com.ivanfranchin.researchservice.model.Researcher;
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
        return researcherRepository.findById(id).orElseThrow(() -> new ResearcherNotFoundException(id));
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
            throw new ResearcherDeletionException(researcher.getId());
        }
    }
}

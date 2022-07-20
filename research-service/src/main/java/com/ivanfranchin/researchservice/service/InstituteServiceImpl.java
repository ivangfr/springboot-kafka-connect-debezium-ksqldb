package com.ivanfranchin.researchservice.service;

import com.ivanfranchin.researchservice.exception.InstituteNotFoundException;
import com.ivanfranchin.researchservice.repository.InstituteRepository;
import com.ivanfranchin.researchservice.exception.InstituteDeletionException;
import com.ivanfranchin.researchservice.model.Institute;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InstituteServiceImpl implements InstituteService {

    private final InstituteRepository instituteRepository;

    @Override
    public List<Institute> getAllInstitutes() {
        return instituteRepository.findAll();
    }

    @Override
    public Institute validateAndGetInstitute(Long id) {
        return instituteRepository.findById(id).orElseThrow(() -> new InstituteNotFoundException(id));
    }

    @Override
    public Institute saveInstitute(Institute institute) {
        return instituteRepository.save(institute);
    }

    @Override
    public void deleteInstitute(Institute institute) {
        try {
            instituteRepository.delete(institute);
        } catch (DataIntegrityViolationException e) {
            throw new InstituteDeletionException(institute.getId());
        }
    }
}

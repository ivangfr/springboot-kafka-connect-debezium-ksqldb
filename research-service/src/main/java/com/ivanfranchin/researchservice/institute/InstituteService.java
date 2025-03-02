package com.ivanfranchin.researchservice.institute;

import com.ivanfranchin.researchservice.institute.exception.InstituteDeletionException;
import com.ivanfranchin.researchservice.institute.exception.InstituteNotFoundException;
import com.ivanfranchin.researchservice.institute.model.Institute;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InstituteService {

    private final InstituteRepository instituteRepository;

    public List<Institute> getAllInstitutes() {
        return instituteRepository.findAll();
    }

    public Institute validateAndGetInstitute(Long id) {
        return instituteRepository.findById(id).orElseThrow(() -> new InstituteNotFoundException(id));
    }

    public Institute saveInstitute(Institute institute) {
        return instituteRepository.save(institute);
    }

    public void deleteInstitute(Institute institute) {
        try {
            instituteRepository.delete(institute);
        } catch (DataIntegrityViolationException e) {
            throw new InstituteDeletionException(institute.getId());
        }
    }
}

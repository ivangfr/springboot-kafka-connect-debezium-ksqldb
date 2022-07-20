package com.ivanfranchin.researchservice.service;

import com.ivanfranchin.researchservice.model.Institute;

import java.util.List;

public interface InstituteService {

    List<Institute> getAllInstitutes();

    Institute validateAndGetInstitute(Long id);

    Institute saveInstitute(Institute institute);

    void deleteInstitute(Institute institute);
}

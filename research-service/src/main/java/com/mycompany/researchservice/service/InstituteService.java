package com.mycompany.researchservice.service;

import com.mycompany.researchservice.model.Institute;

import java.util.List;

public interface InstituteService {

    List<Institute> getAllInstitutes();

    Institute validateAndGetInstitute(Long id);

    Institute saveInstitute(Institute institute);

    void deleteInstitute(Institute institute);
}

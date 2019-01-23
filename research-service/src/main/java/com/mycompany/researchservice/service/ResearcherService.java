package com.mycompany.researchservice.service;

import com.mycompany.researchservice.model.Researcher;

import java.util.List;

public interface ResearcherService {

    List<Researcher> getAllResearchers();

    Researcher validateAndGetResearcher(Long id);

    Researcher saveResearchers(Researcher researcher);

    void deleteResearcher(Researcher researcher);

}

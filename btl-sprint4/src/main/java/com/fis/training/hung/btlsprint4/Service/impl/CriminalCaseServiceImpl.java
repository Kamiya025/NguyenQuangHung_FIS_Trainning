package com.fis.training.hung.btlsprint4.Service.impl;

import com.fis.training.hung.btlsprint4.Service.CriminalCaseService;
import com.fis.training.hung.btlsprint4.exception.CriminalCaseException;
import com.fis.training.hung.btlsprint4.exception.DetectiveException;
import com.fis.training.hung.btlsprint4.model.CriminalCase;
import com.fis.training.hung.btlsprint4.model.Detective;
import com.fis.training.hung.btlsprint4.model.Evidence;
import com.fis.training.hung.btlsprint4.repository.CriminalCaseRepository;
import com.fis.training.hung.btlsprint4.repository.DetectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CriminalCaseServiceImpl implements CriminalCaseService {

    CriminalCaseRepository criminalCaseRepository;
    DetectiveRepository detectiveRepository;
    @Autowired
    public CriminalCaseServiceImpl(CriminalCaseRepository criminalCaseRepository, DetectiveRepository detectiveRepository) {
        this.criminalCaseRepository = criminalCaseRepository;
        this.detectiveRepository = detectiveRepository;
    }

    @Override
    public CriminalCase findByCriminalCaseId(Long criminalCaseId) {
        return criminalCaseRepository.findById(criminalCaseId)
                                        .orElseThrow(() -> new CriminalCaseException("Criminal case not found"));
    }

    @Override
    public CriminalCase createCriminalCase(CriminalCase criminalCase)
    {
        if(criminalCase.getLeadInvestigator()==null)
            throw new CriminalCaseException("Create criminal case fail! Lead Investigator null");
        Detective detective =detectiveRepository.findById(criminalCase.getLeadInvestigator().getId())
                .orElseThrow(() -> new CriminalCaseException("Create criminal case fail! Detective not exist"));
        criminalCase.setLeadInvestigator(detective);
        if(criminalCase.getId()==null)return criminalCaseRepository.save(criminalCase);
        Optional<CriminalCase> optionalCriminalCase = criminalCaseRepository.findById(criminalCase.getId());
        if(optionalCriminalCase.isEmpty())
            return criminalCaseRepository.save(criminalCase);
        else throw new CriminalCaseException("Create criminal case fail! Criminal case already exist");
    }

    @Override
    public CriminalCase updateCriminalCase(CriminalCase criminalCase) {
        if(criminalCase.getLeadInvestigator()==null)
            throw new CriminalCaseException("Update criminal case fail! Lead Investigator null");
        Detective detective =detectiveRepository.findById(criminalCase.getLeadInvestigator().getId())
                .orElseThrow(() -> new CriminalCaseException("Update criminal case fail! Detective not exist"));

        criminalCaseRepository.findById(criminalCase.getId())
                .orElseThrow(() -> new CriminalCaseException("Update criminal case failCriminal case not found"));
        return criminalCaseRepository.save(criminalCase);
    }




    @Override
    public void removeCriminalCase(Long criminalCaseId) {
        criminalCaseRepository.findById(criminalCaseId)
                .orElseThrow(() -> new CriminalCaseException("Remove criminal case failCriminal case not found"));
        criminalCaseRepository.deleteById(criminalCaseId);
    }

    @Override
    public List<CriminalCase> findAll() {
        return criminalCaseRepository.findAll();
    }
}

package com.fis.training.hung.btlsprint4.Service.impl;

import com.fis.training.hung.btlsprint4.Service.DetectiveService;
import com.fis.training.hung.btlsprint4.exception.CriminalCaseException;
import com.fis.training.hung.btlsprint4.exception.DetectiveException;
import com.fis.training.hung.btlsprint4.model.CriminalCase;
import com.fis.training.hung.btlsprint4.model.Detective;
import com.fis.training.hung.btlsprint4.repository.CriminalCaseRepository;
import com.fis.training.hung.btlsprint4.repository.DetectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetectiveServiceImpl implements DetectiveService {

    DetectiveRepository detectiveRepository;
    CriminalCaseRepository criminalCaseRepository;

    @Autowired
    public DetectiveServiceImpl(DetectiveRepository detectiveRepository, CriminalCaseRepository criminalCaseRepository) {
        this.detectiveRepository = detectiveRepository;
        this.criminalCaseRepository = criminalCaseRepository;
    }

    @Override
    public Detective findByDetectiveId(Long detectiveId) {
        return detectiveRepository.findById(detectiveId).orElseThrow(()->new DetectiveException("detective not found"));
    }

    @Override
    public Detective createDetective(Detective detective)
    {
        if(detective.getId()==null) return detectiveRepository.save(detective);

        Optional<Detective> optionalDetective = detectiveRepository.findById(detective.getId());
        if(optionalDetective.isEmpty())
            return detectiveRepository.save(detective);
        else throw new DetectiveException("Create detective fail! Detective already exist");
    }

    @Override
    public Detective addCriminalCase(Long detectiveId, Long criminalCaseId) {
        Detective detective = detectiveRepository.findById(detectiveId)
                .orElseThrow(()->new DetectiveException("Add criminal case of detective fail!detective not found"));
        CriminalCase criminalCase = criminalCaseRepository.findById(criminalCaseId)
                .orElseThrow(()->new DetectiveException("Add criminal case of detective fail!lead Investigator not null"));
        detective.getCriminalCases().add(criminalCase);
        criminalCase.getAssigned().add(detective);
        return detectiveRepository.save(detective);
    }

    @Override
    public Detective removeCriminalCase(Long detectiveId,  Long criminalCaseId) {
        Detective detective = detectiveRepository.findById(detectiveId)
                .orElseThrow(()->new DetectiveException("remove criminal case of detective fail!detective not found"));
        CriminalCase criminalCase = criminalCaseRepository.findById(criminalCaseId)
                .orElseThrow(()->new DetectiveException("remove criminal case of detective fail!lead Investigator not null"));
        detective.getCriminalCases().remove(criminalCase);
        criminalCase.getAssigned().remove(detective);
        return detectiveRepository.save(detective);
    }

    @Override
    public Detective updateDetective(Detective detective) {
        detectiveRepository.findById(detective.getId()).orElseThrow(()->new DetectiveException("detective not found"));
        return detectiveRepository.save(detective);
    }

    @Override
    public void removeDetective(Long detectiveId) {
        detectiveRepository.findById(detectiveId).orElseThrow(()->new DetectiveException("detective not found"));
        detectiveRepository.deleteById(detectiveId);
    }

    @Override
    public List<Detective> findAll() {
        return detectiveRepository.findAll();
    }
}

package com.fis.training.hung.btlsprint4.Service.impl;

import com.fis.training.hung.btlsprint4.Service.EvidenceService;
import com.fis.training.hung.btlsprint4.exception.CriminalCaseException;
import com.fis.training.hung.btlsprint4.exception.EvidenceException;
import com.fis.training.hung.btlsprint4.model.Evidence;
import com.fis.training.hung.btlsprint4.repository.CriminalCaseRepository;
import com.fis.training.hung.btlsprint4.repository.EvidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvidenceServiceImpl implements EvidenceService {

    EvidenceRepository evidenceRepository;
    CriminalCaseRepository criminalCaseRepository;

    @Autowired
    public EvidenceServiceImpl(EvidenceRepository evidenceRepository, CriminalCaseRepository criminalCaseRepository) {
        this.evidenceRepository = evidenceRepository;
        this.criminalCaseRepository = criminalCaseRepository;
    }

    @Override
    public Evidence findByEvidenceId(Long evidenceId) {
        return evidenceRepository.findById(evidenceId).orElseThrow(() -> new EvidenceException("Evidence not found"));
    }

    @Override
    public Evidence createEvidence(Evidence evidence) {
        if(evidence.getId()==null) return evidenceRepository.save(evidence);
        Optional<Evidence> optionalEvidence = evidenceRepository.findById(evidence.getId());
        if(optionalEvidence.isEmpty()){
            criminalCaseRepository.findById(evidence.getCriminalCase().getId())
                    .orElseThrow(() -> new EvidenceException("Create evidence fail! criminal case not exist"));
            return evidenceRepository.save(evidence);}
        else throw new EvidenceException("Create evidence fail! It already exist");
    }

    @Override
    public Evidence updateEvidence(Evidence evidence) {
        criminalCaseRepository.findById(evidence.getCriminalCase().getId())
                .orElseThrow(() -> new EvidenceException("Create evidence fail! criminal case not exist"));
        evidenceRepository.findById(evidence.getId()).orElseThrow(() -> new EvidenceException("Evidence not found"));
        return evidenceRepository.save(evidence);
    }

    @Override
    public void removeEvidence(Long evidenceId) {
        evidenceRepository.findById(evidenceId).orElseThrow(() -> new EvidenceException("Evidence not found"));
        evidenceRepository.deleteById(evidenceId);
    }

    @Override
    public List<Evidence> findAll() {
        return evidenceRepository.findAll();
    }
}

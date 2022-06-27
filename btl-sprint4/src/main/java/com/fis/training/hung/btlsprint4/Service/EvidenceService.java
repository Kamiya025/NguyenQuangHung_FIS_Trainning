package com.fis.training.hung.btlsprint4.Service;

import com.fis.training.hung.btlsprint4.model.Evidence;

import java.util.List;

public interface EvidenceService{
    Evidence findByEvidenceId(Long evidenceId);
    Evidence createEvidence(Evidence evidence);
    Evidence updateEvidence(Evidence evidence);
    void removeEvidence(Long evidenceId);
    List<Evidence> findAll();
}

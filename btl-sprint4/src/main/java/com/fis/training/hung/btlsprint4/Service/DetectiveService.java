package com.fis.training.hung.btlsprint4.Service;

import com.fis.training.hung.btlsprint4.model.CriminalCase;
import com.fis.training.hung.btlsprint4.model.Detective;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DetectiveService{
    Detective findByDetectiveId(Long detectiveId);
    Detective createDetective(Detective detective);
    Detective addCriminalCase(Long detectiveId, Long criminalCaseId);
    Detective removeCriminalCase(Long detectiveId, Long criminalCaseId);
    Detective updateDetective(Detective detective);
    void removeDetective(Long detectiveId);
    List<Detective> findAll();
}

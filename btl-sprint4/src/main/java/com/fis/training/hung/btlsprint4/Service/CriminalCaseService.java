package com.fis.training.hung.btlsprint4.Service;

import com.fis.training.hung.btlsprint4.model.CriminalCase;
import com.fis.training.hung.btlsprint4.model.Detective;
import com.fis.training.hung.btlsprint4.model.Evidence;

import java.util.List;

public interface CriminalCaseService {
    CriminalCase findByCriminalCaseId(Long criminalCaseId);
    CriminalCase createCriminalCase(CriminalCase criminalCase);
    CriminalCase updateCriminalCase(CriminalCase criminalCase);
    void removeCriminalCase(Long criminalCaseId);
    List<CriminalCase> findAll();
}

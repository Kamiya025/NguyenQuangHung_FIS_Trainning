package com.hung.sprint3.btl.btl_sprint3.service;

import com.hung.sprint3.btl.btl_sprint3.model.CriminalCase;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ICriminalCaseService {
    CriminalCase findById(long criminalId);
    CriminalCase createCase(CriminalCase criminalCase);
    CriminalCase updateCase(CriminalCase criminalCase);
    void deleteCase(long id);
    List<CriminalCase> findAll();
}

package com.hung.sprint3.btl.btl_sprint3.service.impl;

import com.hung.sprint3.btl.btl_sprint3.dao.ICriminalCaseDAO;
import com.hung.sprint3.btl.btl_sprint3.model.CriminalCase;
import com.hung.sprint3.btl.btl_sprint3.service.ICriminalCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriminalCaseServiceImpl implements ICriminalCaseService {

    @Autowired
    ICriminalCaseDAO criminalCaseDAO;

    @Override
    public CriminalCase findById(long criminalId) {
        return criminalCaseDAO.findById(criminalId);
    }

    @Override
    public CriminalCase createCase(CriminalCase criminalCase) {
        if(criminalCase!=null)
            if(findById(criminalCase.getId())!=null)
                throw new RuntimeException("Create fail because criminal case exited");
        return criminalCaseDAO.save(criminalCase);
    }

    @Override
    public CriminalCase updateCase(CriminalCase criminalCase) {
        if(findById(criminalCase.getId())==null)
            throw new RuntimeException("Update fail because criminal case not exit");
        return criminalCaseDAO.save(criminalCase);
    }

    @Override
    public void deleteCase(long id) {
        CriminalCase criminalCase = findById(id);
        if(criminalCase==null)
            throw new RuntimeException("Delete fail because criminal case not exit");
        criminalCaseDAO.delete(criminalCase);
    }

    @Override
    public List<CriminalCase> findAll() {
        return criminalCaseDAO.findAll();
    }
}

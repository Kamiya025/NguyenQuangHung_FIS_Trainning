package com.hung.sprint3.btl.btl_sprint3.dao.impl;

import com.hung.sprint3.btl.btl_sprint3.dao.IDetectiveDAO;
import com.hung.sprint3.btl.btl_sprint3.model.Detective;
import com.hung.sprint3.btl.btl_sprint3.service.IDetectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetectiveServiceImpl implements IDetectiveService {
    @Autowired
    IDetectiveDAO detectiveDAO;
    @Override
    public Detective findById(long detectiveId) {
        return detectiveDAO.getById(detectiveId);
    }

    @Override
    public Detective createDetective(Detective detective) {
        return null;
    }

    @Override
    public Detective updateDetective(Detective detective) {
        return null;
    }

    @Override
    public void deleteDetective(long id) {

    }

    @Override
    public List<Detective> findAll() {
        return null;
    }
}

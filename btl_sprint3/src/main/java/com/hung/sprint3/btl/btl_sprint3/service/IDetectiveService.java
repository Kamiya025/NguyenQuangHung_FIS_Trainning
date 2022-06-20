package com.hung.sprint3.btl.btl_sprint3.service;

import com.hung.sprint3.btl.btl_sprint3.model.CriminalCase;
import com.hung.sprint3.btl.btl_sprint3.model.Detective;

import java.util.List;


public interface IDetectiveService {
    Detective findById(long detectiveId);
    Detective createDetective(Detective detective);
    Detective updateDetective(Detective detective);
    void deleteDetective(long id);
    List<Detective> findAll();
}

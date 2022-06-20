package com.hung.sprint3.btl.btl_sprint3.dao;

import com.hung.sprint3.btl.btl_sprint3.model.Detective;

import java.util.List;
import java.util.Set;

public interface IDetectiveDAO {
    Detective getById(long id);
    Detective save(Detective detective);
    boolean remove(long id);
    List<Detective> getAll();
}

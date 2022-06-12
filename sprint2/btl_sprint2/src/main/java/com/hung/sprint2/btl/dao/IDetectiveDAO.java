package com.hung.sprint2.btl.dao;



import com.hung.sprint2.btl.model.Detective;

import java.util.List;
import java.util.function.Predicate;

public interface IDetectiveDAO{

    Detective getById(long id);
//    Detective getByUsename(String username);
    boolean add(Detective detective);
//    void addAll(List<Detective> detective);
    boolean update(Detective detective);
    boolean remove(long id);
    List<Detective> getAll();
//    List<Detective> soft(Predicate<Detective> detectives);
}

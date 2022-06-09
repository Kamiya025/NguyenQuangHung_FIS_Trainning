package com.hung.sprint2.btl.dao;

import fis.trainning.quang_hung.btl.model.Detective;

import java.util.List;
import java.util.function.Predicate;

public interface IDetectiveDAO{

    Detective getById(long id);
    Detective getByUsename(String username);
    void add(Detective detective);
    Detective update(Detective detective);
    Detective remove(long id);
    List<Detective> getAll();
//    List<Detective> soft(Predicate<Detective> detectives);
}

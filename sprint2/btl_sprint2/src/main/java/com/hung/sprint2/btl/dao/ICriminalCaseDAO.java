package com.hung.sprint2.btl.dao;



import com.hung.sprint2.btl.model.CriminalCase;

import java.util.List;

public interface ICriminalCaseDAO{

    CriminalCase getById(long id);
    boolean add(CriminalCase criminalCase);
//    void addAll(List<CriminalCase> criminalCases);
    boolean update(CriminalCase criminalCase);
    boolean remove(long id);
    List<CriminalCase> getAll();

}

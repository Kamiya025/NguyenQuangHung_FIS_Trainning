package fis.trainning.quang_hung.btl.dao;

import fis.trainning.quang_hung.btl.model.CriminalCase;
import fis.trainning.quang_hung.btl.model.CriminalCase;

import java.util.List;

public interface ICriminalCaseDAO{

    CriminalCase getById(long id);
    void add(CriminalCase criminalCase);
    CriminalCase update(CriminalCase criminalCase);
    CriminalCase remove(long id);
    List<CriminalCase> getAll();

}

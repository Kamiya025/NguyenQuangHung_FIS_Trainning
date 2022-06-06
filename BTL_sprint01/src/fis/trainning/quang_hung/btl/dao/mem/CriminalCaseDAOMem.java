package fis.trainning.quang_hung.btl.dao.mem;

import fis.trainning.quang_hung.btl.dao.ICriminalCaseDAO;
import fis.trainning.quang_hung.btl.model.CriminalCase;

import java.util.ArrayList;
import java.util.List;

public class CriminalCaseDAOMem implements ICriminalCaseDAO {

    List<CriminalCase> criminalCaseList = new ArrayList<>();

    private CriminalCaseDAOMem() {}

    private static CriminalCaseDAOMem instance = new CriminalCaseDAOMem();

    public static CriminalCaseDAOMem getInstance(){
        return instance;
    }

    @Override
    public CriminalCase getById(long id) {
        return criminalCaseList.stream().filter(criminalCase -> criminalCase.getId() == id).findFirst().get();
    }

    @Override
    public void add(CriminalCase criminalCase) {
        criminalCaseList.add(criminalCase);
    }

    @Override
    public CriminalCase update(CriminalCase criminalCase) {
        int index = criminalCaseList.indexOf(getById(criminalCase.getId()));
        if(index<0) return null;
        criminalCaseList.set(index,criminalCase);
        return criminalCase;
    }

    @Override
    public CriminalCase remove(long id) {
        CriminalCase criminalCase = getById(id);
        if(criminalCase!=null) criminalCaseList.remove(criminalCase);
        return criminalCase;
    }

    @Override
    public List<CriminalCase> getAll() {
        return criminalCaseList;
    }
}

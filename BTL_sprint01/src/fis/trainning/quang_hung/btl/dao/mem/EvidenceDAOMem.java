package fis.trainning.quang_hung.btl.dao.mem;

import fis.trainning.quang_hung.btl.dao.IEvidenceDAO;
import fis.trainning.quang_hung.btl.model.Detective;
import fis.trainning.quang_hung.btl.model.Evidence;
import fis.trainning.quang_hung.btl.util.Rank;

import java.lang.annotation.Native;
import java.util.ArrayList;
import java.util.List;

public class EvidenceDAOMem implements IEvidenceDAO {

    List<Evidence> evidences= new ArrayList<>();

    private EvidenceDAOMem() {}

    private static EvidenceDAOMem instance = new EvidenceDAOMem();

    public static EvidenceDAOMem getInstance(){
        return instance;
    }

    @Override
    public Evidence getById(long id) {

            Evidence evidence = evidences.stream()
                    .filter(d -> d.getId()==id).findFirst().get();
            return evidence;
    }

    @Override
    public void add( Evidence evidence) {
        evidences.add(evidence);
    }



    @Override
    public Evidence update(Evidence evidence) {
        evidences.set(evidences.indexOf(getById(evidence.getId())),evidence);
        return evidence;
    }

    @Override
    public Evidence remove(long id) {

        return null;
    }

    @Override
    public List<Evidence> getAll() {
        return evidences;
    }
}

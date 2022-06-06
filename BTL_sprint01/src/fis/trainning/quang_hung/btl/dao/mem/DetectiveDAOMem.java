package fis.trainning.quang_hung.btl.dao.mem;

import fis.trainning.quang_hung.btl.dao.IDetectiveDAO;
import fis.trainning.quang_hung.btl.model.Detective;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class DetectiveDAOMem implements IDetectiveDAO {

    List<Detective> detectiveList= new ArrayList<>();

    private DetectiveDAOMem() {}

    private static DetectiveDAOMem instance = new DetectiveDAOMem();

    public static DetectiveDAOMem getInstance(){
        return instance;
    }

    @Override
    public Detective getById(long id) {
        Detective detective = detectiveList.stream()
                                            .filter(d -> d.getId()==id).findFirst().get();
        return detective;
    }

    @Override
    public Detective getByUsename(String username) {
        Detective detective = detectiveList.stream()
                .filter(d -> d.getUsername().equals(username)).findFirst().get();
        return detective;
    }

    @Override
    public void add(Detective detective) {
        detectiveList.add(detective);
    }

    @Override
    public Detective update(Detective detective) {
        detectiveList.set(detectiveList.indexOf(this.getById(detective.getId())), detective);
        return detective;
    }

    @Override
    public Detective remove(long id) {
        Detective detective = getById(id);
        detectiveList.remove(detective);
        return detective;
    }

    @Override
    public List<Detective> getAll() {
        return detectiveList;
    }


}

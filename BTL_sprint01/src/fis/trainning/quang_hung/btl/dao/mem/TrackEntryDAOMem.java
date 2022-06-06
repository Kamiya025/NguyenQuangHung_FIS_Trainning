package fis.trainning.quang_hung.btl.dao.mem;

import fis.trainning.quang_hung.btl.dao.IDetectiveDAO;
import fis.trainning.quang_hung.btl.dao.ITrackEntryDAO;
import fis.trainning.quang_hung.btl.model.TrackEntry;
import fis.trainning.quang_hung.btl.model.Detective;
import fis.trainning.quang_hung.btl.model.TrackEntry;

import java.util.ArrayList;
import java.util.List;

public class TrackEntryDAOMem implements ITrackEntryDAO {

    List<TrackEntry> trackEntryList = new ArrayList<>();

    private TrackEntryDAOMem() {
    }

    private static TrackEntryDAOMem instance = new TrackEntryDAOMem();

    public static TrackEntryDAOMem getInstance(){
        return instance;
    }


    @Override
    public TrackEntry getById(long id) {
        return trackEntryList.stream().filter(trackEntry -> trackEntry.getId()==id).findFirst().get();
    }

    @Override
    public void add(TrackEntry trackEntry) {
        trackEntryList.add(trackEntry);
    }

    @Override
    public TrackEntry update(TrackEntry trackEntry) {
        int index = trackEntryList.indexOf(getById(trackEntry.getId()));
        if(index<0) return null;
        trackEntryList.set(index,trackEntry);
        return trackEntry;
    }

    @Override
    public TrackEntry remove(long id) {
        TrackEntry trackEntry = getById(id);
        if(trackEntry !=null) trackEntryList.remove(trackEntry);
        return trackEntry;
    }

    @Override
    public List<TrackEntry> getAll() {
        return trackEntryList;
    }
}

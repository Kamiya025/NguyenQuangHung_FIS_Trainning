package fis.trainning.quang_hung.btl.dao;

import fis.trainning.quang_hung.btl.model.TrackEntry;

import java.util.List;

public interface ITrackEntryDAO {

    TrackEntry getById(long id);
    void add(TrackEntry trackEntry);
    TrackEntry update(TrackEntry trackEntry);
    TrackEntry remove(long id);
    List<TrackEntry> getAll();
}

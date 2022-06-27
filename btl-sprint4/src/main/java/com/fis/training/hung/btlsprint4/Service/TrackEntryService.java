package com.fis.training.hung.btlsprint4.Service;

import com.fis.training.hung.btlsprint4.dto.TrackEntryDTO;
import com.fis.training.hung.btlsprint4.model.TrackEntry;

import java.util.List;

public interface TrackEntryService {
    TrackEntry findByTrackEntryId(Long trackEntryId);
    TrackEntry createTrackEntry(TrackEntry trackEntry);
    TrackEntry updateTrackEntry(TrackEntry trackEntry);
    void removeTrackEntry(Long trackEntryId);
    List<TrackEntry> findAll();
}

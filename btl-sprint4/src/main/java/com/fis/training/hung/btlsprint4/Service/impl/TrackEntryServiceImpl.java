package com.fis.training.hung.btlsprint4.Service.impl;

import com.fis.training.hung.btlsprint4.Service.TrackEntryService;
import com.fis.training.hung.btlsprint4.dto.TrackEntryDTO;
import com.fis.training.hung.btlsprint4.exception.TrackEntryException;
import com.fis.training.hung.btlsprint4.model.Detective;
import com.fis.training.hung.btlsprint4.model.Evidence;
import com.fis.training.hung.btlsprint4.model.TrackEntry;
import com.fis.training.hung.btlsprint4.repository.DetectiveRepository;
import com.fis.training.hung.btlsprint4.repository.EvidenceRepository;
import com.fis.training.hung.btlsprint4.repository.TrackEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackEntryServiceImpl implements TrackEntryService {

    private final TrackEntryRepository trackEntryRepository;
    private final DetectiveRepository detectiveRepository;
    private final EvidenceRepository evidenceRepository;

    @Autowired
    private TrackEntryServiceImpl(TrackEntryRepository trackEntryRepository,
                                  DetectiveRepository detectiveRepository,
                                  EvidenceRepository evidenceRepository) {
        this.trackEntryRepository = trackEntryRepository;
        this.detectiveRepository = detectiveRepository;
        this.evidenceRepository = evidenceRepository;
    }

    @Override
    public TrackEntry findByTrackEntryId(Long trackEntryId) {
        return trackEntryRepository.findById(trackEntryId).orElseThrow(() -> new TrackEntryException("Track entry not found"));
    }

    @Override
    public TrackEntry createTrackEntry(TrackEntry trackEntry) {
        if(trackEntry.getId()!=null)
        {Optional<TrackEntry> optionalTrackEntry = trackEntryRepository.findById(trackEntry.getId());
        if (!optionalTrackEntry.isEmpty())
            throw new TrackEntryException("Create track entry fail! Track entry already exist");
        }
        Detective detective = detectiveRepository.findById(trackEntry.getDetective().getId())
                .orElseThrow(() -> new TrackEntryException("Create track entry fail! Detective not found"));
        Evidence evidence = evidenceRepository.findById(trackEntry.getEvidence().getId())
                .orElseThrow(() -> new TrackEntryException("Create track entry fail! Evidence not found"));
        trackEntry.setDetective(detective);
        trackEntry.setEvidence(evidence);
        return trackEntryRepository.save(trackEntry);
    }

    @Override
    public TrackEntry updateTrackEntry(TrackEntry trackEntry) {
        trackEntryRepository.findById(trackEntry.getId())
                .orElseThrow(() -> new TrackEntryException("Track entry not found"));
        Detective detective = detectiveRepository.findById(trackEntry.getDetective().getId())
                .orElseThrow(() -> new TrackEntryException("Update Track entry fail! Detective not found"));
        Evidence evidence = evidenceRepository.findById(trackEntry.getEvidence().getId())
                .orElseThrow(() -> new TrackEntryException("Update Track entry fail! Evidence not found"));
        trackEntry.setDetective(detective);
        trackEntry.setEvidence(evidence);

        return trackEntryRepository.save(trackEntry);
    }

    @Override
    public void removeTrackEntry(Long trackEntryId) {
        trackEntryRepository.findById(trackEntryId).orElseThrow(() -> new TrackEntryException("remove fail! Track entry not found"));
        trackEntryRepository.deleteById(trackEntryId);
    }

    @Override
    public List<TrackEntry> findAll() {
        return trackEntryRepository.findAll();
    }
}

package com.fis.training.hung.btlsprint4.repository;

import com.fis.training.hung.btlsprint4.model.TrackEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackEntryRepository extends JpaRepository<TrackEntry,Long> {
}

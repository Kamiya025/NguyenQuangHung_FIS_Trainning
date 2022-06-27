package com.fis.training.hung.btlsprint4.repository;

import com.fis.training.hung.btlsprint4.model.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvidenceRepository extends JpaRepository<Evidence,Long> {
}

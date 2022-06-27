package com.fis.training.hung.btlsprint4.repository;

import com.fis.training.hung.btlsprint4.model.CriminalCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriminalCaseRepository extends JpaRepository<CriminalCase,Long> {
}

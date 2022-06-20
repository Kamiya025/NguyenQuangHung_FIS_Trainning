package com.hung.sprint3.btl.btl_sprint3.dao;

import com.hung.sprint3.btl.btl_sprint3.model.CriminalCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICriminalCaseDAO extends JpaRepository<CriminalCase, Long> {
    CriminalCase findById(long id);
}

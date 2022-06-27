package com.fis.training.hung.btlsprint4.repository;

import com.fis.training.hung.btlsprint4.model.Detective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetectiveRepository extends JpaRepository<Detective,Long> {
}

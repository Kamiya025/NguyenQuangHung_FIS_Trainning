package com.hung.sprint3.btl.btl_sprint3.service.impl;

import com.hung.sprint3.btl.btl_sprint3.dao.ICriminalCaseDAO;
import com.hung.sprint3.btl.btl_sprint3.model.CriminalCase;
import com.hung.sprint3.btl.btl_sprint3.model.Detective;
import com.hung.sprint3.btl.btl_sprint3.model.util.CaseStatus;
import com.hung.sprint3.btl.btl_sprint3.model.util.CaseType;
import com.hung.sprint3.btl.btl_sprint3.model.util.EmploymentStatus;
import com.hung.sprint3.btl.btl_sprint3.model.util.Rank;
import com.hung.sprint3.btl.btl_sprint3.service.ICriminalCaseService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CriminalCaseServiceImplTest {

    @Autowired
    ICriminalCaseService criminalCaseService;

    @Test
    @Order(1)
    void createCase() {
        Detective detective = new Detective(1,1,LocalDateTime.now(),LocalDateTime.now(),
                "test1","test","1","1234",
                LocalDateTime.now(),"12", Rank.CHIEF_INSPECTOR,true,
                EmploymentStatus.ACTIVE,new HashSet<>());

        CriminalCase caseCreate = new CriminalCase( 1, LocalDateTime.now(), LocalDateTime.now(), "12",
                CaseType.FELONY, "test", "detail test", CaseStatus.INCOURT,
                "notes test", detective, new HashSet<>());
        CriminalCase criminalCase = criminalCaseService.createCase(caseCreate);
        System.out.println("id: "+criminalCase.getId());
        assertNotNull(criminalCase);

    }
    @Test
    @Order(2)
    void findById() {
        criminalCaseService.findById(1);
    }



    @Test
    @Order(3)
    void updateCase() {
        CriminalCase criminalCase = criminalCaseService.findById(1);
        criminalCase.setVersion(2);
        criminalCaseService.updateCase(criminalCase);
        assertEquals(2, criminalCase.getVersion());
    }
}
package com.fis.training.hung.btlsprint4.repository;

import com.fis.training.hung.btlsprint4.model.CriminalCase;

import com.fis.training.hung.btlsprint4.model.Detective;
import com.fis.training.hung.btlsprint4.util.CaseStatus;
import com.fis.training.hung.btlsprint4.util.CaseType;
import com.fis.training.hung.btlsprint4.util.EmploymentStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CriminalCaseRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    CriminalCaseRepository criminalCaseRepository;


    @Test
    public void should_find_no_criminalCases_if_repository_is_empty() {
        Iterable criminalCases = criminalCaseRepository.findAll();
        assertThat(criminalCases).isEmpty();
    }

    @Test
    public void should_store_a_criminalCase() {
        Detective detective1 = Detective.builder()
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        entityManager.persist(detective1);

        CriminalCase criminalCase = CriminalCase.builder()
                .number("123")
                .notes("this is a note")
                .detailedDescription("this is a detail")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail")
                .build();

        CriminalCase criminalCaseStore = criminalCaseRepository.save(criminalCase);
        assertThat(criminalCaseStore.getId()).isNotZero();
        assertThat(criminalCaseStore).hasFieldOrPropertyWithValue("number", "123");
        assertThat(criminalCaseStore).hasFieldOrPropertyWithValue("shortDescription", "this is a short detail");

    }

    @Test
    public void should_find_all_criminalCases() {
        Detective detective1 = Detective.builder()
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        entityManager.persist(detective1);

        CriminalCase criminalCase1 = CriminalCase.builder()
                .number("123")
                .notes("this is a note")
                .detailedDescription("this is a detail")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail")
                .build();
        entityManager.persist(criminalCase1);
        CriminalCase criminalCase2 = CriminalCase.builder()
                .number("124")
                .notes("this is a note 2")
                .detailedDescription("this is a detail 2")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail 2")
                .build();
        entityManager.persist(criminalCase2);
        CriminalCase criminalCase3 = CriminalCase.builder()
                .number("125")
                .notes("this is a note 3")
                .detailedDescription("this is a detail 3")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail 3")
                .build();
        entityManager.persist(criminalCase3);
        Iterable criminalCases = criminalCaseRepository.findAll();
        assertThat(criminalCases).hasSize(3).contains(criminalCase1, criminalCase2, criminalCase3);
    }

    @Test
    public void should_find_criminalCase_by_id() {
        Detective detective1 = Detective.builder()
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        entityManager.persist(detective1);

        CriminalCase criminalCase1 = CriminalCase.builder()
                .number("123")
                .notes("this is a note")
                .detailedDescription("this is a detail")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail")
                .build();
        entityManager.persist(criminalCase1);
        CriminalCase criminalCase2 = CriminalCase.builder()
                .number("124")
                .notes("this is a note 2")
                .detailedDescription("this is a detail 2")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail 2")
                .build();
        entityManager.persist(criminalCase2);
        CriminalCase criminalCase3 = CriminalCase.builder()
                .number("125")
                .notes("this is a note 3")
                .detailedDescription("this is a detail 3")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail 3")
                .build();
        entityManager.persist(criminalCase3);
        CriminalCase foundCriminalCase = criminalCaseRepository.findById(criminalCase1.getId()).get();
        assertThat(foundCriminalCase).isEqualTo(criminalCase1);
    }

    @Test
    public void should_update_criminalCase_by_id() {
        Detective detective1 = Detective.builder()
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        entityManager.persist(detective1);

        CriminalCase criminalCase1 = CriminalCase.builder()
                .number("123")
                .notes("this is a note")
                .detailedDescription("this is a detail")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail")
                .build();
        entityManager.persist(criminalCase1);
        CriminalCase criminalCase2 = CriminalCase.builder()
                .number("124")
                .notes("this is a note 2")
                .detailedDescription("this is a detail 2")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail 2")
                .build();
        entityManager.persist(criminalCase2);
        CriminalCase criminalCase3 = CriminalCase.builder()
                .number("125")
                .notes("this is a note 3")
                .detailedDescription("this is a detail 3")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail 3")
                .build();
        entityManager.persist(criminalCase3);

        CriminalCase criminalCase = criminalCaseRepository.findById(criminalCase3.getId()).get();
        criminalCase.setNotes("Note was update");

        criminalCaseRepository.save(criminalCase);
        CriminalCase checkCriminalCase = criminalCaseRepository.findById(criminalCase3.getId()).get();

        assertThat(checkCriminalCase.getId()).isEqualTo(criminalCase3.getId());
        assertThat(checkCriminalCase.getNotes()).isEqualTo(criminalCase.getNotes());

    }

    @Test
    public void should_delete_criminalcase_by_id() {
        Detective detective1 = Detective.builder()
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        entityManager.persist(detective1);

        CriminalCase criminalCase1 = CriminalCase.builder()
                .number("123")
                .notes("this is a note")
                .leadInvestigator(detective1)
                .detailedDescription("this is a detail")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .shortDescription("this is a short detail")
                .build();
        entityManager.persist(criminalCase1);
        CriminalCase criminalCase2 = CriminalCase.builder()
                .number("124")
                .notes("this is a note 2")
                .detailedDescription("this is a detail 2")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail 2")
                .build();
        entityManager.persist(criminalCase2);
        CriminalCase criminalCase3 = CriminalCase.builder()
                .number("125")
                .notes("this is a note 3")
                .detailedDescription("this is a detail 3")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail 3")
                .build();
        entityManager.persist(criminalCase3);
        criminalCaseRepository.deleteById(criminalCase1.getId());
        Iterable criminalCases = criminalCaseRepository.findAll();
        assertThat(criminalCases).hasSize(2).contains(criminalCase2, criminalCase3);
    }
}
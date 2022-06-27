package com.fis.training.hung.btlsprint4.repository;

import com.fis.training.hung.btlsprint4.model.CriminalCase;
import com.fis.training.hung.btlsprint4.model.Detective;
import com.fis.training.hung.btlsprint4.model.Evidence;
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
class EvidenceRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    EvidenceRepository evidenceRepository;


    @Test
    public void should_find_no_Evidences_if_repository_is_empty() {
        Iterable Evidences = evidenceRepository.findAll();
        assertThat(Evidences).isEmpty();
    }

    @Test
    public void should_store_a_Evidence() {
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
                .type(CaseType.FELONY).leadInvestigator(detective1)
                .shortDescription("this is a short detail")
                .build();
        entityManager.persist(criminalCase1);

        Evidence evidence = Evidence.builder()
                .criminalCase(criminalCase1)
                .itemName("AAA")
                .number("7823")
                .archived(true)
                .notes("this is note")
                .build();

        Evidence evidenceStore = evidenceRepository.save(evidence);
        assertThat(evidenceStore.getId()).isNotZero();
        assertThat(evidenceStore).hasFieldOrPropertyWithValue("itemName", "AAA");
        assertThat(evidenceStore.getCriminalCase()).isEqualTo(criminalCase1);
        assertThat(evidenceStore).hasFieldOrPropertyWithValue("number", "7823");

    }
    @Test
    public void should_find_all_evidences() {

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
                .type(CaseType.FELONY).leadInvestigator(detective1)
                .shortDescription("this is a short detail")
                .build();
        entityManager.persist(criminalCase1);
        Evidence evidence1 = Evidence.builder()
                .criminalCase(criminalCase1)
                .itemName("AAA")
                .number("7823")
                .archived(true)
                .notes("this is note")
                .build();
        entityManager.persist(evidence1);

        Evidence evidence2 = Evidence.builder()
                .criminalCase(criminalCase1)
                .itemName("AAB")
                .number("7824")
                .archived(true)
                .notes("this is note 2")
                .build();
        entityManager.persist(evidence2);

        Evidence evidence3 = Evidence.builder()
                .criminalCase(criminalCase1)
                .itemName("AAC")
                .number("7825")
                .archived(true)
                .notes("this is note 3")
                .build();
        entityManager.persist(evidence3);

        Iterable evidences = evidenceRepository.findAll();
        assertThat(evidences).hasSize(3).contains(evidence1, evidence2, evidence3);
    }
    @Test
    public void should_find_Evidence_by_id() {
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
                .type(CaseType.FELONY).leadInvestigator(detective1)
                .shortDescription("this is a short detail")
                .build();
        entityManager.persist(criminalCase1);
        Evidence evidence1 = Evidence.builder()
                .criminalCase(criminalCase1)
                .itemName("AAA")
                .number("7823")
                .archived(true)
                .notes("this is note")
                .build();
        entityManager.persist(evidence1);
        Evidence evidence2 = Evidence.builder()
                .criminalCase(criminalCase1)
                .itemName("AAB")
                .number("7824")
                .archived(true)
                .notes("this is note")
                .build();
        entityManager.persist(evidence2);
        Evidence evidence3 = Evidence.builder()
                .criminalCase(criminalCase1)
                .itemName("AAA")
                .number("7825")
                .archived(true)
                .notes("this is note")
                .build();
        entityManager.persist(evidence3);
        Evidence foundEvidence = evidenceRepository.findById(evidence1.getId()).get();
        assertThat(foundEvidence).isEqualTo(evidence1);
    }
    @Test
    public void should_update_evidence_by_id() {
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
                .type(CaseType.FELONY).leadInvestigator(detective1)
                .shortDescription("this is a short detail")
                .build();
        entityManager.persist(criminalCase1);
        CriminalCase criminalCase2 = CriminalCase.builder()
                .number("124")
                .notes("this is a note 2")
                .detailedDescription("this is a detail 2")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY).leadInvestigator(detective1)
                .shortDescription("this is a short detail 2")
                .build();
        entityManager.persist(criminalCase2);

        Evidence evidence1 = Evidence.builder()
                .criminalCase(criminalCase1)
                .itemName("AAA")
                .number("7823")
                .archived(true)
                .notes("this is note")
                .build();
        entityManager.persist(evidence1);
        Evidence evidence2 = Evidence.builder()
                .criminalCase(criminalCase1)
                .itemName("AAB")
                .number("7824")
                .archived(true)
                .notes("this is note")
                .build();
        entityManager.persist(evidence2);
        Evidence evidence3 = Evidence.builder()
                .criminalCase(criminalCase1)
                .itemName("AAA")
                .number("7825")
                .archived(true)
                .notes("this is note")
                .build();
        entityManager.persist(evidence3);


        Evidence evidence = evidenceRepository.findById(evidence3.getId()).get();
        evidence.setNotes("Note was update");
        evidence.setCriminalCase(criminalCase2);

        evidenceRepository.save(evidence);
        Evidence checkEvidence = evidenceRepository.findById(evidence3.getId()).get();

        assertThat(checkEvidence.getId()).isEqualTo(evidence3.getId());
        assertThat(checkEvidence.getNotes()).isEqualTo(evidence.getNotes());
        assertThat(checkEvidence.getCriminalCase()).isEqualTo(criminalCase2);

    }
    @Test
    public void should_delete_Evidence_by_id() {
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
                .type(CaseType.FELONY).leadInvestigator(detective1)
                .shortDescription("this is a short detail")
                .build();
        entityManager.persist(criminalCase1);


        Evidence evidence1 = Evidence.builder()
                .criminalCase(criminalCase1)
                .itemName("AAA")
                .number("7823")
                .archived(true)
                .notes("this is note")
                .build();
        entityManager.persist(evidence1);
        Evidence evidence2 = Evidence.builder()
                .criminalCase(criminalCase1)
                .itemName("AAB")
                .number("7824")
                .archived(true)
                .notes("this is note")
                .build();
        entityManager.persist(evidence2);
        Evidence evidence3 = Evidence.builder()
                .criminalCase(criminalCase1)
                .itemName("AAA")
                .number("7825")
                .archived(true)
                .notes("this is note")
                .build();
        entityManager.persist(evidence3);
        evidenceRepository.deleteById(evidence1.getId());
        Iterable Evidences = evidenceRepository.findAll();
        assertThat(Evidences).hasSize(2).contains(evidence2, evidence3);
    }

}
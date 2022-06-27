package com.fis.training.hung.btlsprint4.repository;


import com.fis.training.hung.btlsprint4.model.CriminalCase;
import com.fis.training.hung.btlsprint4.model.Detective;
import com.fis.training.hung.btlsprint4.model.Evidence;
import com.fis.training.hung.btlsprint4.model.Storage;

import com.fis.training.hung.btlsprint4.util.CaseStatus;
import com.fis.training.hung.btlsprint4.util.CaseType;
import com.fis.training.hung.btlsprint4.util.EmploymentStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StorageRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    StorageRepository storageRepository;


    @Test
    public void should_find_no_storages_if_repository_is_empty() {
        Iterable Storages = storageRepository.findAll();
        assertThat(Storages).isEmpty();
    }

    @Test
    public void should_store_a_storage() {
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
        Set<Evidence> evidenceSet =new HashSet<>();
        evidenceSet.add(evidence1);
        evidenceSet.add(evidence2);
        evidenceSet.add(evidence3);
        Storage storage = Storage.builder()
                .name("storage 1")
                .location("location 1")
                .evidenceSet(evidenceSet)
                .build();

        Storage storageStore = storageRepository.save(storage);
        assertThat(storageStore.getId()).isNotZero();
        assertThat(storageStore.getEvidenceSet()).hasSize(3).contains(evidence1,evidence2,evidence3);
    }

    @Test
    public void should_find_all_storages() {
        Storage storage1 = Storage.builder()
                .name("storage 1")
                .location("location 1")
                .build();
        entityManager.persist(storage1);
        Storage storage2 = Storage.builder()
                .name("storage 2")
                .location("location 2")
                .build();
        entityManager.persist(storage2);
        Storage storage3 = Storage.builder()
                .name("storage 3")
                .location("location 3")
                .build();
        entityManager.persist(storage3);

        Iterable storages = storageRepository.findAll();
        assertThat(storages).hasSize(3).contains(storage1, storage2, storage3);
    }

    @Test
    public void should_find_storage_by_id() {
        Storage storage1 = Storage.builder()
                .name("storage 1")
                .location("location 1")
                .build();
        entityManager.persist(storage1);
        Storage storage2 = Storage.builder()
                .name("storage 2")
                .location("location 2")
                .build();
        entityManager.persist(storage2);
        Storage storage3 = Storage.builder()
                .name("storage 3")
                .location("location 3")
                .build();
        entityManager.persist(storage3);
        Storage foundStorage = storageRepository.findById(storage1.getId()).get();
        assertThat(foundStorage).isEqualTo(storage1);
    }

    @Test
    public void should_update_storage_by_id() {
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
        Set<Evidence> evidenceSet =new HashSet<>();
        evidenceSet.add(evidence1);
        Storage storage1 = Storage.builder()
                .name("storage 1")
                .location("location 1")
                .evidenceSet(evidenceSet)
                .build();
        entityManager.persist(storage1);

        Storage storage = storageRepository.findById(storage1.getId()).get();
        storage.setName("storage 1 was update");
        storage.getEvidenceSet().add(evidence2);
        storageRepository.save(storage);
        Storage checkStorage = storageRepository.findById(storage1.getId()).get();

        assertThat(checkStorage.getId()).isEqualTo(storage.getId());
        assertThat(checkStorage.getName()).isEqualTo(storage.getName());
        assertThat(checkStorage.getEvidenceSet()).isEqualTo(storage.getEvidenceSet());

    }

    @Test
    public void should_delete_Storage_by_id() {
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
        Set<Evidence> evidenceSet =new HashSet<>();
        evidenceSet.add(evidence1);
        Storage storage1 = Storage.builder()
                .name("storage 1")
                .location("location 1")
                .evidenceSet(evidenceSet)
                .build();
        entityManager.persist(storage1);

        storageRepository.deleteById(storage1.getId());
        Iterable Storages = storageRepository.findAll();
        assertThat(Storages).hasSize(0);
    }
}
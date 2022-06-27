package com.fis.training.hung.btlsprint4.repository;

import com.fis.training.hung.btlsprint4.model.CriminalCase;
import com.fis.training.hung.btlsprint4.model.Detective;
import com.fis.training.hung.btlsprint4.model.Evidence;
import com.fis.training.hung.btlsprint4.model.TrackEntry;
import com.fis.training.hung.btlsprint4.util.CaseStatus;
import com.fis.training.hung.btlsprint4.util.CaseType;
import com.fis.training.hung.btlsprint4.util.EmploymentStatus;
import com.fis.training.hung.btlsprint4.util.TrackAction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TrackEntryRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    TrackEntryRepository trackEntryRepository;


    @Test
    public void should_find_no_trackEntries_if_repository_is_empty() {
        Iterable trackEntries = trackEntryRepository.findAll();
        assertThat(trackEntries).isEmpty();
    }

    @Test
    public void should_store_a_trackEntry() {
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

        Evidence evidence1 = Evidence.builder()
                .criminalCase(criminalCase1)
                .itemName("AAA")
                .number("7823")
                .archived(true)
                .notes("this is note")
                .build();
        entityManager.persist(evidence1);

        TrackEntry trackEntry = TrackEntry.builder()
                .detective(detective1)
                .evidence(evidence1)
                .date(LocalDateTime.now())
                .trackAction(TrackAction.SUBMITTED)
                .reason("Create")
                .build();
        TrackEntry trackEntryStore = trackEntryRepository.save(trackEntry);
        assertThat(trackEntryStore.getId()).isNotZero();
        assertThat(trackEntryStore.getTrackAction()).isEqualTo(TrackAction.SUBMITTED);
        assertThat(trackEntryStore).hasFieldOrPropertyWithValue("reason", "Create");
        assertThat(trackEntryStore.getDetective()).isEqualTo(detective1);

    }
    @Test
    public void should_find_all_trackEntrys() {
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
                .shortDescription("this is a short detail")
                .leadInvestigator(detective1)
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
        TrackEntry trackEntry1 = TrackEntry.builder()
                .detective(detective1)
                .evidence(evidence1)
                .date(LocalDateTime.now())
                .trackAction(TrackAction.SUBMITTED)
                .reason("Create")
                .build();
        entityManager.persist(trackEntry1);
        TrackEntry trackEntry2 = TrackEntry.builder()
                .detective(detective1).evidence(evidence1)
                .date(LocalDateTime.now())
                .trackAction(TrackAction.SUBMITTED)
                .reason("update 1")
                .build();
        entityManager.persist(trackEntry2);

        TrackEntry trackEntry3 = TrackEntry.builder()
                .detective(detective1).evidence(evidence1)
                .date(LocalDateTime.now())
                .trackAction(TrackAction.SUBMITTED)
                .reason("update 2")
                .build();
        entityManager.persist(trackEntry3);
        Iterable trackEntries = trackEntryRepository.findAll();
        assertThat(trackEntries).hasSize(3).contains(trackEntry1, trackEntry2, trackEntry3);
    }
    @Test
    public void should_find_trackentry_by_id() {
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

        Evidence evidence1 = Evidence.builder()
                .criminalCase(criminalCase1)
                .itemName("AAA")
                .number("7823")
                .archived(true)
                .notes("this is note")
                .build();
        entityManager.persist(evidence1);

        TrackEntry trackEntry1 = TrackEntry.builder()
                .detective(detective1).evidence(evidence1)
                .date(LocalDateTime.now())
                .trackAction(TrackAction.SUBMITTED)
                .reason("Create")
                .build();
        entityManager.persist(trackEntry1);
        TrackEntry trackEntry2 = TrackEntry.builder()
                .detective(detective1).evidence(evidence1)
                .date(LocalDateTime.now())
                .trackAction(TrackAction.SUBMITTED)
                .reason("update 1")
                .build();
        entityManager.persist(trackEntry2);

        TrackEntry trackEntry3 = TrackEntry.builder()
                .detective(detective1).evidence(evidence1)
                .date(LocalDateTime.now())
                .trackAction(TrackAction.SUBMITTED)
                .reason("update 2")
                .build();
        entityManager.persist(trackEntry3);

       TrackEntry foundTrackEntry = trackEntryRepository.findById(trackEntry2.getId()).get();
        assertThat(foundTrackEntry).isEqualTo(trackEntry2);
    }
    @Test
    public void should_update_trackEntry_by_id() {
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

        Evidence evidence1 = Evidence.builder()
                .criminalCase(criminalCase1)
                .itemName("AAA")
                .number("7823")
                .archived(true)
                .notes("this is note")
                .build();
        entityManager.persist(evidence1);

        TrackEntry trackEntry1 = TrackEntry.builder()
                .detective(detective1).evidence(evidence1)
                .date(LocalDateTime.now())
                .trackAction(TrackAction.SUBMITTED)
                .reason("Create")
                .build();
        entityManager.persist(trackEntry1);
        TrackEntry trackEntry2 = TrackEntry.builder()
                .detective(detective1).evidence(evidence1)
                .date(LocalDateTime.now())
                .trackAction(TrackAction.SUBMITTED)
                .reason("update 1")
                .build();
        entityManager.persist(trackEntry2);

        TrackEntry trackEntry3 = TrackEntry.builder()
                .detective(detective1).evidence(evidence1)
                .date(LocalDateTime.now())
                .trackAction(TrackAction.SUBMITTED)
                .reason("update 2")
                .build();
        entityManager.persist(trackEntry3);

        TrackEntry trackEntry = trackEntryRepository.findById(trackEntry3.getId()).get();
        trackEntry.setReason("reason was update");
        trackEntry.setTrackAction(TrackAction.RETURNED);
        trackEntryRepository.save(trackEntry);
        TrackEntry checkTrackEntry = trackEntryRepository.findById(trackEntry.getId()).get();

        assertThat(checkTrackEntry.getId()).isEqualTo(trackEntry.getId());
        assertThat(checkTrackEntry.getTrackAction()).isEqualTo(trackEntry.getTrackAction());

    }
    @Test
    public void should_delete_detective_by_id() {
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
                .shortDescription("this is a short detail")
                .leadInvestigator(detective1)
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

        TrackEntry trackEntry1 = TrackEntry.builder()
                .detective(detective1).evidence(evidence1)
                .date(LocalDateTime.now())
                .trackAction(TrackAction.SUBMITTED)
                .reason("Create")
                .build();
        entityManager.persist(trackEntry1);
        TrackEntry trackEntry2 = TrackEntry.builder()
                .detective(detective1).evidence(evidence1)
                .date(LocalDateTime.now())
                .trackAction(TrackAction.SUBMITTED)
                .reason("update 1")
                .build();
        entityManager.persist(trackEntry2);

        TrackEntry trackEntry3 = TrackEntry.builder()
                .detective(detective1).evidence(evidence1)
                .date(LocalDateTime.now())
                .trackAction(TrackAction.SUBMITTED)
                .reason("update 2")
                .build();
        entityManager.persist(trackEntry3);

        trackEntryRepository.deleteById(trackEntry1.getId());
        Iterable trackEntries = trackEntryRepository.findAll();
        assertThat(trackEntries).hasSize(2).contains(trackEntry2, trackEntry3);
    }
}
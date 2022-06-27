package com.fis.training.hung.btlsprint4.repository;

import com.fis.training.hung.btlsprint4.model.Detective;
import com.fis.training.hung.btlsprint4.util.EmploymentStatus;
import com.fis.training.hung.btlsprint4.util.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DetectiveRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    DetectiveRepository detectiveRepository;


    @Test
    public void should_find_no_detectives_if_repository_is_empty() {
        Iterable detectives = detectiveRepository.findAll();
        assertThat(detectives).isEmpty();
    }

    @Test
    public void should_store_a_detective() {
        Detective detective = Detective.builder()
                            .username("Detective1")
                            .firstName("first")
                            .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();

        Detective detectiveStore = detectiveRepository.save(detective);
        assertThat(detectiveStore.getId()).isNotZero();
        assertThat(detectiveStore).hasFieldOrPropertyWithValue("username", "Detective1");
        assertThat(detectiveStore).hasFieldOrPropertyWithValue("firstName", "first");
        assertThat(detectiveStore).hasFieldOrPropertyWithValue("lastName", "last");
        assertThat(detectiveStore).hasFieldOrPropertyWithValue("badgeNumber", "2");
        assertThat(detectiveStore).hasFieldOrPropertyWithValue("armed", true);
    }
    @Test
    public void should_find_all_detectives() {
        Detective detective1 = Detective.builder()
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        entityManager.persist(detective1);
        Detective detective2 = Detective.builder()
                .username("Detective2")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        entityManager.persist(detective2);
        Detective detective3 = Detective.builder()
                .username("Detective3")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        entityManager.persist(detective3);
        Iterable detectives = detectiveRepository.findAll();
        assertThat(detectives).hasSize(3).contains(detective1, detective2, detective3);
    }
    @Test
    public void should_find_detective_by_id() {
        Detective detective1 = Detective.builder()
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        entityManager.persist(detective1);
        Detective detective2 = Detective.builder()
                .username("Detective2")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        entityManager.persist(detective2);
        Detective detective3 = Detective.builder()
                .username("Detective3")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        entityManager.persist(detective3);
        Detective foundDetective = detectiveRepository.findById(detective2.getId()).get();
        assertThat(foundDetective).isEqualTo(detective2);
    }
    @Test
    public void should_update_detective_by_id() {
        Detective detective1 = Detective.builder()
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        entityManager.persist(detective1);
        Detective detective2 = Detective.builder()
                .username("Detective2")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        entityManager.persist(detective2);
        Detective detective3 = Detective.builder()
                .username("Detective3")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        entityManager.persist(detective3);

        Detective detective = detectiveRepository.findById(detective2.getId()).get();
        detective.setFirstName("Hung");
        detective.setLastName("Nguyen");

        detectiveRepository.save(detective);
        Detective checkDetective = detectiveRepository.findById(detective2.getId()).get();

        assertThat(checkDetective.getId()).isEqualTo(detective.getId());
        assertThat(checkDetective.getFirstName()).isEqualTo(detective.getFirstName());
        assertThat(checkDetective.getLastName()).isEqualTo(detective.getLastName());

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
        Detective detective2 = Detective.builder()
                .username("Detective2")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        entityManager.persist(detective2);
        Detective detective3 = Detective.builder()
                .username("Detective3")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        entityManager.persist(detective3);
        detectiveRepository.deleteById(detective2.getId());
        Iterable detectives = detectiveRepository.findAll();
        assertThat(detectives).hasSize(2).contains(detective1, detective3);
    }
}
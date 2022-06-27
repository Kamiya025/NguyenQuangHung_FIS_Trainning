package com.fis.training.hung.btlsprint4.Service.impl;

import com.fis.training.hung.btlsprint4.exception.CriminalCaseException;
import com.fis.training.hung.btlsprint4.exception.DetectiveException;
import com.fis.training.hung.btlsprint4.model.CriminalCase;
import com.fis.training.hung.btlsprint4.model.Detective;
import com.fis.training.hung.btlsprint4.repository.CriminalCaseRepository;
import com.fis.training.hung.btlsprint4.repository.DetectiveRepository;
import com.fis.training.hung.btlsprint4.util.CaseStatus;
import com.fis.training.hung.btlsprint4.util.CaseType;
import com.fis.training.hung.btlsprint4.util.EmploymentStatus;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CriminalCaseServiceImplTest {
    @Mock
    private DetectiveRepository detectiveRepository;
    @Mock
    private CriminalCaseRepository criminalCaseRepository;
    @InjectMocks
    private CriminalCaseServiceImpl criminalCaseService;

    @Test
    void whenfindById_shouldReturnCriminalCase() {
        Detective detective1 = Detective.builder()
                .id(1L)
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();

        CriminalCase criminalCase1 = CriminalCase.builder()
                .number("123")
                .notes("this is a note")
                .detailedDescription("this is a detail")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail")
                .build();
        when(criminalCaseRepository.findById(1L)).thenReturn(Optional.of(criminalCase1));
        CriminalCase criminalCase = criminalCaseService.findByCriminalCaseId(1L);
        assertThat(criminalCase).isEqualTo(criminalCase1);
        verify(criminalCaseRepository).findById(1L);
    }

    @Test
    void whenfindById_shouldRteurnException() {
        when(criminalCaseRepository.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> criminalCaseService.findByCriminalCaseId(1L))
                .isInstanceOf(CriminalCaseException.class);
        verify(criminalCaseRepository).findById(1L);
    }

    @Test
    void whenCreate_shouldReturnCriminalCase() {
        Detective detective1 = Detective.builder()
                .id(1L)
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();

        CriminalCase criminalCase1 = CriminalCase.builder()
                .number("123")
                .notes("this is a note")
                .detailedDescription("this is a detail")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail")
                .build();
        CriminalCase criminalCaseCreate = CriminalCase.builder()
                .id(1L)
                .number("123")
                .notes("this is a note")
                .detailedDescription("this is a detail")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail")
                .build();

        when(criminalCaseRepository.save(criminalCase1)).thenReturn(criminalCaseCreate);
        when(detectiveRepository.findById(1l)).thenReturn(Optional.of(detective1));
        CriminalCase criminalCase = criminalCaseService.createCriminalCase(criminalCase1);
        assertThat(criminalCase).isEqualTo(criminalCaseCreate);
        verify(criminalCaseRepository, times(1)).save(criminalCase1);
    }

    @Test
    void whenCreateIfDetectiveNull_shouldReturnExeption() {
        Detective detective1 = Detective.builder()
                .id(1L)
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();

        CriminalCase criminalCase1 = CriminalCase.builder()
                .id(1L)
                .number("123")
                .notes("this is a note")
                .detailedDescription("this is a detail")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail")
                .build();

        when(detectiveRepository.findById(1l)).thenReturn(Optional.of(detective1));
        when(criminalCaseRepository.findById(1L)).thenReturn(Optional.of(criminalCase1));
        assertThatThrownBy(() -> criminalCaseService.createCriminalCase(criminalCase1))
                .isInstanceOf(CriminalCaseException.class);
        verify(detectiveRepository, times(1)).findById(1L);
        verify(criminalCaseRepository, times(1)).findById(1L);
        verify(criminalCaseRepository, times(0)).save(criminalCase1);
    }

    @Test
    void whenCreateIfDetectiveNotExist_shouldReturnExeption() {
        Detective detective1 = Detective.builder().id(1L).build();
        CriminalCase criminalCase1 = CriminalCase.builder()
                .number("123")
                .notes("this is a note")
                .detailedDescription("this is a detail")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail")
                .build();

        when(detectiveRepository.findById(1l)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> criminalCaseService.createCriminalCase(criminalCase1))
                .isInstanceOf(CriminalCaseException.class);
        verify(detectiveRepository, times(1)).findById(1L);
        verify(criminalCaseRepository, times(0)).save(criminalCase1);
    }

    @Test
    void whenCreateIfCaseExit_shouldReturnExeption() {
        Detective detective1 = Detective.builder()
                .id(1L)
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();

        CriminalCase criminalCase1 = CriminalCase.builder()
                .id(1L)
                .number("123")
                .notes("this is a note")
                .detailedDescription("this is a detail")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail")
                .build();

        when(detectiveRepository.findById(1l)).thenReturn(Optional.of(detective1));
        when(criminalCaseRepository.findById(1L)).thenReturn(Optional.of(criminalCase1));
        assertThatThrownBy(() -> criminalCaseService.createCriminalCase(criminalCase1))
                .isInstanceOf(CriminalCaseException.class);
        verify(detectiveRepository, times(1)).findById(1L);
        verify(criminalCaseRepository, times(1)).findById(1l);
        verify(criminalCaseRepository, times(0)).save(criminalCase1);
    }

    //
    @Test
    void whenUpdate_shouldReturnCriminalCase() {
        Detective detective1 = Detective.builder()
                .id(1L)
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();

        CriminalCase criminalCase1 = CriminalCase.builder()
                .id(1L)
                .number("123")
                .notes("this is a note")
                .detailedDescription("this is a detail")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail")
                .build();

        when(criminalCaseRepository.save(criminalCase1)).thenReturn(criminalCase1);
        when(detectiveRepository.findById(1l)).thenReturn(Optional.of(detective1));
        when(criminalCaseRepository.findById(1L)).thenReturn(Optional.of(criminalCase1));
        CriminalCase criminalCase = criminalCaseService.updateCriminalCase(criminalCase1);
        assertThat(criminalCase).isEqualTo(criminalCase1);
        verify(criminalCaseRepository, times(1)).save(criminalCase1);
        verify(criminalCaseRepository, times(1)).findById(1L);
        verify(detectiveRepository, times(1)).findById(1L);
    }

    @Test
    void whenUpdateIfDetectiveNull_shouldReturnExeption() {
        Detective detective1 = Detective.builder()
                .id(1L)
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();

        CriminalCase criminalCase1 = CriminalCase.builder()
                .id(1L)
                .number("123")
                .notes("this is a note")
                .detailedDescription("this is a detail")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .shortDescription("this is a short detail")
                .build();

        when(detectiveRepository.findById(1l)).thenReturn(Optional.of(detective1));
        when(criminalCaseRepository.findById(1L)).thenReturn(Optional.of(criminalCase1));
        assertThatThrownBy(() -> criminalCaseService.updateCriminalCase(criminalCase1))
                .isInstanceOf(CriminalCaseException.class);
        verify(detectiveRepository, times(0)).findById(1L);
        verify(criminalCaseRepository, times(0)).findById(1L);
        verify(criminalCaseRepository, times(0)).save(criminalCase1);
    }

    @Test
    void whenUpdateIfDetectiveNotExist_shouldReturnExeption() {
        Detective detective1 = Detective.builder().id(1L).build();
        CriminalCase criminalCase1 = CriminalCase.builder()
                .number("123")
                .notes("this is a note")
                .detailedDescription("this is a detail")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail")
                .build();

        when(detectiveRepository.findById(1l)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> criminalCaseService.updateCriminalCase(criminalCase1))
                .isInstanceOf(CriminalCaseException.class);
        verify(detectiveRepository, times(1)).findById(1L);
        verify(criminalCaseRepository, times(0)).findById(1L);
        verify(criminalCaseRepository, times(0)).save(criminalCase1);
    }

    @Test
    void whenUpdateIfCaseNotExit_shouldReturnExeption() {
        Detective detective1 = Detective.builder()
                .id(1L)
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();

        CriminalCase criminalCase1 = CriminalCase.builder()
                .id(1L)
                .number("123")
                .notes("this is a note")
                .detailedDescription("this is a detail")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail")
                .build();

        when(detectiveRepository.findById(1l)).thenReturn(Optional.of(detective1));
        when(criminalCaseRepository.findById(1L)).thenReturn(Optional.of(criminalCase1));
        assertThatThrownBy(() -> criminalCaseService.createCriminalCase(criminalCase1))
                .isInstanceOf(CriminalCaseException.class);
        verify(detectiveRepository, times(1)).findById(1L);
        verify(criminalCaseRepository, times(1)).findById(1L);
        verify(criminalCaseRepository, times(0)).save(criminalCase1);
    }

    @Test
    void whenRemoveIfCaseNotExist_shouldReturnException() {


        when(criminalCaseRepository.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> criminalCaseService.removeCriminalCase(1L))
                .isInstanceOf(CriminalCaseException.class);
        verify(criminalCaseRepository,times(1)).findById(1L);
        verify(criminalCaseRepository,times(0)).deleteById(1L);
    }

    @Test
    void whenRemoveCriminalCase_shouldReturnDetective() {
        Detective detective1 = Detective.builder()
                .id(1L)
                .username("Detective1")
                .firstName("first1")
                .lastName("last")
                .password("123").badgeNumber("2")
                .criminalCases(new HashSet<>())
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();

        CriminalCase criminalCase1 = CriminalCase.builder()
                .id(1L)
                .number("123")
                .notes("this is a note")
                .detailedDescription("this is a detail")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail")
                .build();
        when(criminalCaseRepository.findById(1L)).thenReturn(Optional.of(criminalCase1));
        criminalCaseService.removeCriminalCase(1L);
        verify(criminalCaseRepository,times(1)).findById(1L);
        verify(criminalCaseRepository,times(1)).deleteById(1L);
    }

    @Test
    void whenFindAll_shouldReturnList() {
        List<CriminalCase>criminalCases = new ArrayList<>();
        Detective detective1 = Detective.builder()
                .id(1L)
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();

        CriminalCase criminalCase1 = CriminalCase.builder()
                .id(1L)
                .number("123")
                .notes("this is a note")
                .detailedDescription("this is a detail")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail")
                .build();
        CriminalCase criminalCase2 = CriminalCase.builder()
                .id(2L)
                .number("123")
                .notes("this is a note")
                .detailedDescription("this is a detail")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail")
                .build();
        CriminalCase criminalCase3 = CriminalCase.builder()
                .id(3L)
                .number("123")
                .notes("this is a note")
                .detailedDescription("this is a detail")
                .status(CaseStatus.INCOURT)
                .type(CaseType.FELONY)
                .leadInvestigator(detective1)
                .shortDescription("this is a short detail")
                .build();
        criminalCases.add(criminalCase1);
        criminalCases.add(criminalCase2);
        criminalCases.add(criminalCase3);
        when(criminalCaseRepository.findAll()).thenReturn(criminalCases);

        List<CriminalCase> actualCases = criminalCaseService.findAll();

        assertThat(actualCases.size()).isEqualTo(criminalCases.size());
        verify(criminalCaseRepository).findAll();
    }
}
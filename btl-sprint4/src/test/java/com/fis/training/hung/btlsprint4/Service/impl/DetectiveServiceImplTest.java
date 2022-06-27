package com.fis.training.hung.btlsprint4.Service.impl;//package com.fis.training.hung.btlsprint4.Service.impl;

import com.fis.training.hung.btlsprint4.exception.DetectiveException;
import com.fis.training.hung.btlsprint4.model.CriminalCase;
import com.fis.training.hung.btlsprint4.model.Detective;
import com.fis.training.hung.btlsprint4.repository.CriminalCaseRepository;
import com.fis.training.hung.btlsprint4.repository.DetectiveRepository;
import com.fis.training.hung.btlsprint4.util.EmploymentStatus;
import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class DetectiveServiceImplTest {
    @Mock
    private DetectiveRepository detectiveRepository;
    @Mock
    private CriminalCaseRepository criminalCaseRepository;
    @InjectMocks
    private DetectiveServiceImpl detectiveService;

    @Test
    void whenfindById_shouldReturnDetective() {
        Optional<Detective> optionalDetective = Optional.of(Detective.builder()
                .id(1L)
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build());
        when(detectiveRepository.findById(1L)).thenReturn(optionalDetective);
        Detective detective = detectiveService.findByDetectiveId(1L);
        assertThat(detective).isEqualTo(optionalDetective.get());
        verify(detectiveRepository).findById(1L);
    }

    @Test
    void whenFindById_shouldReturnExeception() {
        Optional<Detective> optionalDetective = Optional.empty();
        when(detectiveRepository.findById(1L)).thenReturn(optionalDetective);

        assertThatThrownBy(() -> detectiveService.findByDetectiveId(1L))
                .isInstanceOf(DetectiveException.class);

        verify(detectiveRepository).findById(1L);
    }


    @Test
    void whenCreateDetective_shouldReturnDetective() {
        Detective detective = Detective.builder()
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        Detective detectiveCreate = Detective.builder()
                .id(1L)
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        when(detectiveRepository.save(detective)).thenReturn(detectiveCreate);
        Detective detectivePost = detectiveService.createDetective(detective);
        assertThat(detectivePost).isEqualTo(detectiveCreate);
        verify(detectiveRepository, times(0)).findById(1l);
        verify(detectiveRepository).save(detective);
    }

    @Test
    void whenCreateDetectiveIfIdNotExit_shouldReturnDetective() {
        Detective detective = Detective.builder()
                .id(2L)
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        Detective detectiveCreate = Detective.builder()
                .id(1L)
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        when(detectiveRepository.save(detective)).thenReturn(detectiveCreate);
        when(detectiveRepository.findById(2L)).thenReturn(Optional.empty());
        Detective detectivePost = detectiveService.createDetective(detective);
        assertThat(detectivePost).isEqualTo(detectiveCreate);
        verify(detectiveRepository, times(1)).findById(2l);
        verify(detectiveRepository).save(detective);
    }

    @Test
    void whenCreateDetective_shouldReturnException() {
        Detective detective = Detective.builder()
                .id(1L)
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        Detective detectiveFind = Detective.builder()
                .id(1L)
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        when(detectiveRepository.findById(1l)).thenReturn(Optional.of(detectiveFind));

        assertThatThrownBy(() -> detectiveService.createDetective(detective))
                .isInstanceOf(DetectiveException.class);
        verify(detectiveRepository, times(1)).findById(1L);
        verify(detectiveRepository, times(0)).save(detective);
    }

    @Test
    void whenUpdate_shouldReturnDetective() {
        Detective detective = Detective.builder()
                .id(1L)
                .username("Detective001")
                .firstName("first1")
                .lastName("last1")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        Detective detectiveFind = Detective.builder()
                .id(1L)
                .username("Detective1")
                .firstName("first1")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        when(detectiveRepository.save(detective)).thenReturn(detective);
        when(detectiveRepository.findById(1L)).thenReturn(Optional.of(detectiveFind));
        Detective detectivePost = detectiveService.updateDetective(detective);
        assertThat(detectivePost).isEqualTo(detective);
        verify(detectiveRepository, times(1)).findById(1l);
        verify(detectiveRepository, times(1)).save(detective);
    }

    @Test
    void whenUpdate_shouldReturnException() {
        Detective detective = Detective.builder()
                .id(1L)
                .username("Detective001")
                .firstName("first1")
                .lastName("last1")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();

        when(detectiveRepository.save(detective)).thenReturn(detective);
        when(detectiveRepository.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> detectiveService.updateDetective(detective))
                .isInstanceOf(DetectiveException.class);
        verify(detectiveRepository, times(1)).findById(1l);
        verify(detectiveRepository, times(0)).save(detective);
    }


    @Test
    void whenRemoveDetective_shouldDone() {
        List<Detective> mockDetectives = new ArrayList<>();
        Detective detective1 = Detective.builder()
                .id(1L)
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        mockDetectives.add(detective1);
        when(detectiveRepository.findById(1L)).thenReturn(Optional.of(detective1));
        detectiveService.removeDetective(1L);
        verify(detectiveRepository, times(1)).deleteById(1L);
        verify(detectiveRepository, times(1)).findById(1l);
    }



    @Test
    void whenRemoveDetective_shouldReturnException() {
        when(detectiveRepository.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> detectiveService.removeDetective(1l))
                .isInstanceOf(DetectiveException.class);
        verify(detectiveRepository, times(0)).deleteById(1L);
        verify(detectiveRepository, times(1)).findById(1l);
    }

    @Test
    void whenAddCriminalCaseIfDetectiveNotExist_shouldReturnException() {


        when(detectiveRepository.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> detectiveService.addCriminalCase(1l,1L))
                .isInstanceOf(DetectiveException.class);
        verify(detectiveRepository, times(1)).findById(1L);
    }

    @Test
    void whenAddCriminalCaseIfCaseNotExist_shouldReturnException() {
        Detective detective = Detective.builder()
                .id(1L)
                .username("Detective1")
                .firstName("first1")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();

        when(detectiveRepository.findById(1L)).thenReturn(Optional.of(detective));
        when(criminalCaseRepository.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> detectiveService.addCriminalCase(1L,1l))
                .isInstanceOf(DetectiveException.class);
        verify(detectiveRepository, times(1)).findById(1L);
        verify(criminalCaseRepository,times(1)).findById(1L);
        verify(detectiveRepository,times(0)).save(detective);
    }

    @Test
    void whenAddCriminalCase_shouldReturnDetective() {
        Detective detective = Detective.builder()
                .id(1L)
                .username("Detective1")
                .firstName("first1")
                .lastName("last")
                .password("123").badgeNumber("2")
                .criminalCases(new HashSet<>())
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        CriminalCase criminalCase = CriminalCase.builder().id(1L).assigned(new HashSet<>()).build();

        when(detectiveRepository.findById(1L)).thenReturn(Optional.of(detective));
        when(criminalCaseRepository.findById(1L)).thenReturn(Optional.of(criminalCase));
        detectiveService.addCriminalCase(1L,1L);
        verify(detectiveRepository, times(1)).findById(1L);
        verify(criminalCaseRepository,times(1)).findById(1L);
        verify(detectiveRepository,times(1)).save(detective);
    }

    @Test
    void whenRemoveCriminalCaseIfDetectiveNotExist_shouldReturnException() {


        when(detectiveRepository.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> detectiveService.removeCriminalCase(1l,1L))
                .isInstanceOf(DetectiveException.class);
        verify(detectiveRepository, times(1)).findById(1L);
    }

    @Test
    void whenRemoveCriminalCaseIfCaseNotExist_shouldReturnException() {
        Detective detective = Detective.builder()
                .id(1L)
                .username("Detective1")
                .firstName("first1")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();

        when(detectiveRepository.findById(1L)).thenReturn(Optional.of(detective));
        when(criminalCaseRepository.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> detectiveService.removeCriminalCase(1L,1l))
                .isInstanceOf(DetectiveException.class);
        verify(detectiveRepository, times(1)).findById(1L);
        verify(criminalCaseRepository,times(1)).findById(1L);
        verify(detectiveRepository,times(0)).save(detective);
    }

    @Test
    void whenRemoveCriminalCase_shouldReturnDetective() {
        Detective detective = Detective.builder()
                .id(1L)
                .username("Detective1")
                .firstName("first1")
                .lastName("last")
                .password("123").badgeNumber("2")
                .criminalCases(new HashSet<>())
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        CriminalCase criminalCase = CriminalCase.builder().id(1L).assigned(new HashSet<>()).build();

        when(detectiveRepository.findById(1L)).thenReturn(Optional.of(detective));
        when(criminalCaseRepository.findById(1L)).thenReturn(Optional.of(criminalCase));
        detectiveService.addCriminalCase(1L,1L);
        verify(detectiveRepository, times(1)).findById(1L);
        verify(criminalCaseRepository,times(1)).findById(1L);
        verify(detectiveRepository,times(1)).save(detective);
    }



    @Test
    void whenFindAll_shouldReturnList() {
        List<Detective> mockDetectives = new ArrayList<>();
        Detective detective1 = Detective.builder()
                .username("Detective1")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        mockDetectives.add(detective1);
        Detective detective2 = Detective.builder()
                .username("Detective2")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        mockDetectives.add(detective2);
        Detective detective3 = Detective.builder()
                .username("Detective3")
                .firstName("first")
                .lastName("last")
                .password("123").badgeNumber("2")
                .hiringDate(LocalDateTime.now()).status(EmploymentStatus.ACTIVE).armed(true).build();
        mockDetectives.add(detective3);

        when(detectiveRepository.findAll()).thenReturn(mockDetectives);

        List<Detective> actualDetectives = detectiveService.findAll();

        assertThat(actualDetectives.size()).isEqualTo(mockDetectives.size());
        verify(detectiveRepository).findAll();


    }
}
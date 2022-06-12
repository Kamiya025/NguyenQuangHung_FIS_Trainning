package com.hung.sprint2.btl.dao.jdbc;

import com.hung.sprint2.btl.dao.ICriminalCaseDAO;
import com.hung.sprint2.btl.dao.IDetectiveDAO;
import com.hung.sprint2.btl.model.CriminalCase;
import com.hung.sprint2.btl.model.Detective;
import com.hung.sprint2.btl.model.Evidence;
import com.hung.sprint2.btl.model.util.CaseStatus;
import com.hung.sprint2.btl.model.util.CaseType;
import com.hung.sprint2.btl.model.util.EmploymentStatus;
import com.hung.sprint2.btl.model.util.Rank;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class DetectiveDAOJdbcTest {

    @BeforeEach
    public void cleanAndInsertDB()
    {
        String query="delete from detective;";
        try(
                Connection conn = DatabaseUtility.getConnection();
                PreparedStatement statement = conn.prepareStatement(query)){
            statement.executeUpdate();
            query =  "INSERT INTO `detective`(id,version,username,firstname,lastname,password,badge_number,rank,armed,status) " +
                    "VALUES (1, 1,'as','a','a','123','1',1,true,'1')";
            statement.executeUpdate(query);
        }
        catch (Exception e)
        {
            log.error(e.toString());
        };
    }
    @Nested
    @DisplayName("Tests get detective by id")
    class GetById {
        @Test
        public void testGetByIdExit() {
            IDetectiveDAO detectiveDAO = new DetectiveDAOJdbc();
            assertNotNull(detectiveDAO.getById(1));
        }

        @Test
        public void testGetByIdNotExit() {
            IDetectiveDAO detectiveDAO = new DetectiveDAOJdbc();
            assertNull(detectiveDAO.getById(0));
        }
    }


    @Nested
    @DisplayName("Tests add detective")
    class AddDetective {
        @Test
        public void testAddIdNotExit() {
            Detective detective = new Detective(2,1,LocalDateTime.now(),LocalDateTime.now(),
                                                "test1","test","1","1234",
                                                LocalDateTime.now(),"12", Rank.CHIEF_INSPECTOR,true,
                                                EmploymentStatus.ACTIVE,new HashSet<>(),new HashSet<>());
            IDetectiveDAO detectiveDAO = new DetectiveDAOJdbc();
            assertTrue(detectiveDAO.add(detective));
        }

        @Test
        public void testAddIdExit() {
            Detective detective = new Detective(1,1,LocalDateTime.now(),LocalDateTime.now(),
                    "test1","test","1","1234",
                    LocalDateTime.now(),"12", Rank.CHIEF_INSPECTOR,true,
                    EmploymentStatus.ACTIVE,new HashSet<>(),new HashSet<>());
            IDetectiveDAO detectiveDAO = new DetectiveDAOJdbc();
            assertFalse(detectiveDAO.add(detective));
        }
    }

    @Nested
    @DisplayName("Tests update detective")
    class UpdateDetective {
        @Test
        public void testUpdateIdExit() {
            Detective detective = new Detective(1, 2, LocalDateTime.now(), LocalDateTime.now(),
                    "test1", "test", "1", "1234",
                    LocalDateTime.now(), "12", Rank.CHIEF_INSPECTOR, true,
                    EmploymentStatus.ACTIVE, new HashSet<>(), new HashSet<>());
            IDetectiveDAO detectiveDAO = new DetectiveDAOJdbc();
            assertTrue(detectiveDAO.update(detective));
        }

        @Test
        public void testUpdateIdNotExit() {
            Detective detective = new Detective(3, 1, LocalDateTime.now(), LocalDateTime.now(),
                    "test1", "test", "1", "1234",
                    LocalDateTime.now(), "12", Rank.CHIEF_INSPECTOR, true,
                    EmploymentStatus.ACTIVE, new HashSet<>(), new HashSet<>());
            IDetectiveDAO detectiveDAO = new DetectiveDAOJdbc();
            assertFalse(detectiveDAO.update(detective));
        }
    }
    @Nested
    @DisplayName("Tests remove detective")
    class RemoveDetective {
        @Test
        public void testRemoveIdExit() {
            IDetectiveDAO detectiveDAO = new DetectiveDAOJdbc();
            assertTrue(detectiveDAO.remove(1));
        }
        @Test
        public void testRemoveIdNotExit() {
            IDetectiveDAO detectiveDAO = new DetectiveDAOJdbc();
            assertFalse(detectiveDAO.remove(2));
        }
    }

    @Test
    void getAll() {
        IDetectiveDAO detectiveDAO = new DetectiveDAOJdbc();
        assertEquals(1,detectiveDAO.getAll().size());
    }
}
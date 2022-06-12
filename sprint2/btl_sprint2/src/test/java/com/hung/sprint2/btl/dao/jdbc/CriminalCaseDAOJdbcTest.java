package com.hung.sprint2.btl.dao.jdbc;

import com.hung.sprint2.btl.dao.ICriminalCaseDAO;

import com.hung.sprint2.btl.model.CriminalCase;
import com.hung.sprint2.btl.model.Detective;
import com.hung.sprint2.btl.model.Evidence;
import com.hung.sprint2.btl.model.util.CaseStatus;
import com.hung.sprint2.btl.model.util.CaseType;
import lombok.SneakyThrows;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
public class CriminalCaseDAOJdbcTest{


    @BeforeEach
    public void cleanAndInsertDB()
    {
        String query="delete from criminal_case;";
        try(
        Connection conn = DatabaseUtility.getConnection();
        PreparedStatement statement = conn.prepareStatement(query)){
        statement.executeUpdate();
        query =  "INSERT INTO criminal_case (id,version,number,type,short_descripton,deteiled_descripton,status,notes,load_investigator)" +
                "VALUE (1,1,1,1,'q','qq',1,'q',1),(2, 1, 1,'INFRACTION', 'q', 'qq', 'INCOURT', 'q', 1);";
        statement.executeUpdate(query);
        }
        catch (Exception e)
        {
            log.error(e.toString());
        };
    }
    @Nested
    @DisplayName("Tests get criminal case by id")
    class GetById {
        @ParameterizedTest
        @ValueSource(ints = {1, 2})
        public void testGetByIdExit(int id) {
            ICriminalCaseDAO caseDAO = new CriminalCaseDAOJdbc();
            assertNotNull(caseDAO.getById(id));
        }

        @Test
        public void testGetByIdNotExit() {
            ICriminalCaseDAO caseDAO = new CriminalCaseDAOJdbc();
            assertNull(caseDAO.getById(0));
        }
    }

    @Nested
    @DisplayName("Tests add criminal case")
    class AddCriminalCase {
        @Test
        public void testAddIdNotExit() {
            Detective detective = new Detective();
            detective.setId(1);
            CriminalCase criminalCase = new CriminalCase(3, 1, LocalDateTime.now(), LocalDateTime.now(), "12",
                    CaseType.FELONY, "test", "detail test", CaseStatus.INCOURT,
                    "notes test", new HashSet<Evidence>(), detective, new HashSet<>());
            ICriminalCaseDAO caseDAO = new CriminalCaseDAOJdbc();
            assertTrue(caseDAO.add(criminalCase));
        }

        @Test
        public void testAddIdExit() {
            Detective detective = new Detective();
            detective.setId(1);
            CriminalCase criminalCase = new CriminalCase(2, 1, LocalDateTime.now(), LocalDateTime.now(), "12",
                    CaseType.FELONY, "test", "detail test", CaseStatus.INCOURT,
                    "notes test", new HashSet<Evidence>(), detective, new HashSet<>());
            ICriminalCaseDAO caseDAO = new CriminalCaseDAOJdbc();
            assertFalse(caseDAO.add(criminalCase));
        }
    }
    @Nested
    @DisplayName("Tests update criminal case")
    class UpdateCriminalCase {
    @Test
    public void testUpdateIdExit() {
        Detective detective = new Detective();
        detective.setId(1);
        CriminalCase criminalCase = new CriminalCase(2,2, LocalDateTime.now(),LocalDateTime.now(),"12",
                CaseType.FELONY,"test","detail test", CaseStatus.INCOURT,
                "notes test",new HashSet<Evidence>(),detective,new HashSet<>());
        ICriminalCaseDAO caseDAO = new CriminalCaseDAOJdbc();
        assertTrue(caseDAO.update(criminalCase));
    }
    @Test
    public void testUpdateIdNotExit() {
        Detective detective = new Detective();
        detective.setId(1);
        CriminalCase criminalCase = new CriminalCase(5,2, LocalDateTime.now(),LocalDateTime.now(),"12",
                CaseType.FELONY,"test","detail test", CaseStatus.INCOURT,
                "notes test",new HashSet<Evidence>(),detective,new HashSet<>());
        ICriminalCaseDAO caseDAO = new CriminalCaseDAOJdbc();
        assertFalse(caseDAO.update(criminalCase));
    }

    }
    @Nested
    @DisplayName("Tests remove criminal case")
    class RemoveCriminalCase {
    @Test
    public void testRemoveIdExit() {
            ICriminalCaseDAO caseDAO = new CriminalCaseDAOJdbc();
            assertTrue(caseDAO.remove(2));
    }
    @Test
    public void testRemoveIdNotExit() {
        ICriminalCaseDAO caseDAO = new CriminalCaseDAOJdbc();
        assertFalse(caseDAO.remove(3));
    }
    }

    @Test
    public void testGetAll() {
        ICriminalCaseDAO caseDAO = new CriminalCaseDAOJdbc();
        assertEquals(2,caseDAO.getAll().size());

    }
}
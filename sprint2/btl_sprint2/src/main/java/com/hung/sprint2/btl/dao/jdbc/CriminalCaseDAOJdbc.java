package com.hung.sprint2.btl.dao.jdbc;

import com.hung.sprint2.btl.dao.ICriminalCaseDAO;
import com.hung.sprint2.btl.model.CriminalCase;
import com.hung.sprint2.btl.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CriminalCaseDAOJdbc implements ICriminalCaseDAO {
    private final static Logger logger = LoggerFactory.getLogger(CriminalCase.class);
    @Override
    public CriminalCase getById(long id) {
        String query = "SELECT * FROM Criminal_Case where id = ?";
        CriminalCase criminalCase = new CriminalCase();
        try (Connection con = DatabaseUtility.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            criminalCase = DatabaseMapper.getCriminalCase(resultSet);
        } catch (Exception e) {
            logger.error(e.toString());
            return null;
        }
        return criminalCase;
    }

    @Override
    public boolean add(CriminalCase criminalCase) {
        try (Connection con = DatabaseUtility.getConnection();
             PreparedStatement stmt = con.prepareStatement("INSERT INTO criminal_case (id,version,create_at,modified_at,number,type,short_descripton,deteiled_descripton,status,notes,load_investigator)" +
                                                                    "VALUE (?,?,?,?,?,?,?,?,?,?,?)")) {
            stmt.setLong(1, criminalCase.getId());
            stmt.setInt(2, criminalCase.getVersion());
            stmt.setString(3, DateUtil.getString(criminalCase.getCreateAt()));
            stmt.setString(4, DateUtil.getString(criminalCase.getModifiedAt()));
            stmt.setString(5, criminalCase.getNumber());
            stmt.setString(6, criminalCase.getType().toString());
            stmt.setString(7, criminalCase.getShortDescription());
            stmt.setString(8, criminalCase.getDetailedDescription());
            stmt.setString(9, criminalCase.getStatus().toString());
            stmt.setString(10, criminalCase.getNotes());
            stmt.setLong(11, criminalCase.getLoadInvestigator().getId());

            if (stmt.executeUpdate() > 0) {

                return true;
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return false;
    }
    @Override
    public boolean update(CriminalCase criminalCase) {
        try (Connection con = DatabaseUtility.getConnection();
             PreparedStatement stmt = con.prepareStatement("UPDATE criminal_case " +
                     "SET version=?,create_at=?,modified_at=?,number=?,type=?,short_descripton=?,deteiled_descripton=?,status=?,notes=?,load_investigator=? " +
                     " where id =?")) {
            stmt.setInt(1, criminalCase.getVersion());
            stmt.setString(2, DateUtil.getString(criminalCase.getCreateAt()));
            stmt.setString(3, DateUtil.getString(criminalCase.getModifiedAt()));
            stmt.setString(4, criminalCase.getNumber());
            stmt.setString(5, criminalCase.getType().toString());
            stmt.setString(6, criminalCase.getShortDescription());
            stmt.setString(7, criminalCase.getDetailedDescription());
            stmt.setString(8, criminalCase.getStatus().toString());
            stmt.setString(9, criminalCase.getNotes());
            stmt.setLong(10, criminalCase.getLoadInvestigator().getId());
            stmt.setLong(11, criminalCase.getId());
            logger.debug("Query update :" + stmt.toString());
            if (stmt.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return false;
    }

    @Override
    public boolean remove(long id) {
        try (Connection con = DatabaseUtility.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("Delete from criminal_case where id = ?");
            stmt.setLong(1, id);
            if (stmt.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return false;
    }

    @Override
    public List<CriminalCase> getAll() {
        String query = "SELECT * FROM Criminal_Case";
        List<CriminalCase> criminalCaseList = new ArrayList<>();
        try (Connection con = DatabaseUtility.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                criminalCaseList.add(DatabaseMapper.getCriminalCase(rs));
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return criminalCaseList;
    }
}

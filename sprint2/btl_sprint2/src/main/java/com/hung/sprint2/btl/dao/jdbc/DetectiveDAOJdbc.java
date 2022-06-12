package com.hung.sprint2.btl.dao.jdbc;

import com.hung.sprint2.btl.dao.IDetectiveDAO;
import com.hung.sprint2.btl.model.Detective;
import com.hung.sprint2.btl.model.util.EmploymentStatus;
import com.hung.sprint2.btl.model.util.Rank;
import com.hung.sprint2.btl.util.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DetectiveDAOJdbc implements IDetectiveDAO {
    @Override
    public Detective getById(long id) {
        String query = "SELECT * FROM detective where id = ?";
        Detective detective = new Detective();
        try (Connection con = DatabaseUtility.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            detective = DatabaseMapper.getDetective(resultSet);
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
        return detective;
    }


    @Override
    public boolean add(Detective detective) {
        try (Connection con = DatabaseUtility.getConnection();
             PreparedStatement stmt =
                     con.prepareStatement("INSERT INTO detective " +
                             "(id,version,create_at,modified_at,username,firstname,lastname,password,hiring_date,badge_number,rank,armed,status)" +
                             "VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?)")) {
            stmt.setLong(1, detective.getId());
            stmt.setInt(2, detective.getVersion());
            stmt.setString(3, DateUtil.getString(detective.getCreateAt()));
            stmt.setString(4, DateUtil.getString(detective.getModifiedAt()));
            stmt.setString(5, detective.getUsername());
            stmt.setString(6, detective.getFistName());
            stmt.setString(7, detective.getLastName());
            stmt.setString(8, detective.getPassword());
            stmt.setString(9, DateUtil.getString(detective.getHiringDate()));
            stmt.setString(10, detective.getBadgeNumber());
            stmt.setString(11, detective.getRank().toString());
            stmt.setBoolean(12, detective.getArmed());
            stmt.setString(13, detective.getStatus().toString());
            if (stmt.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error(e.toString());
        }
        return false;
    }

    @Override
    public boolean update(Detective detective) {
        try (Connection con = DatabaseUtility.getConnection();
             PreparedStatement stmt = con.prepareStatement("UPDATE detective " +
                     "SET version=?,create_at=?,modified_at=?,username=?,firstname=?,lastname=?,password=?,hiring_date=?,badge_number=?,rank=?,armed=?,status=? " +
                     " where id =?")) {

            stmt.setInt(1, detective.getVersion());
            stmt.setString(2, DateUtil.getString(detective.getCreateAt()));
            stmt.setString(3, DateUtil.getString(detective.getModifiedAt()));
            stmt.setString(4, detective.getUsername());
            stmt.setString(5, detective.getFistName());
            stmt.setString(6, detective.getLastName());
            stmt.setString(7, detective.getPassword());
            stmt.setString(8, DateUtil.getString(detective.getHiringDate()));
            stmt.setString(9, detective.getBadgeNumber());
            stmt.setString(10, detective.getRank().toString());
            stmt.setBoolean(11, detective.getArmed());
            stmt.setString(12, detective.getStatus().toString());
            stmt.setLong(13, detective.getId());
            if (stmt.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error(e.toString());
        }
        return false;
    }

    @Override
    public boolean remove(long id) {
        try (Connection con = DatabaseUtility.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("Delete from detective where id = ?");
            stmt.setLong(1, id);
            if (stmt.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error(e.toString());
        }
        return false;
    }

    @Override
    public List<Detective> getAll() {
        String query = "SELECT * FROM Detective";
        List<Detective> detectiveList = new ArrayList<>();
        try (Connection con = DatabaseUtility.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                detectiveList.add(DatabaseMapper.getDetective(rs));
            }
        } catch (Exception e) {
            log.error(e.toString());
        }
        return detectiveList;
    }
}

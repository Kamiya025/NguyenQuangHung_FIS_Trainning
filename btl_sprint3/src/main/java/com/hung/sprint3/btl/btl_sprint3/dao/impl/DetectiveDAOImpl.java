package com.hung.sprint3.btl.btl_sprint3.dao.impl;

import com.hung.sprint3.btl.btl_sprint3.dao.IDetectiveDAO;
import com.hung.sprint3.btl.btl_sprint3.dao.mapper.CriminalCaseRowMapper;
import com.hung.sprint3.btl.btl_sprint3.dao.mapper.DetectiveRowMapper;
import com.hung.sprint3.btl.btl_sprint3.model.CriminalCase;
import com.hung.sprint3.btl.btl_sprint3.model.Detective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class DetectiveDAOImpl implements IDetectiveDAO {

    private RowMapper<Detective> rowMapper = new DetectiveRowMapper();

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public Detective getById(long id) {
        String sql = "select * from detective where id =?";
        Detective detective = jdbcTemplate.queryForObject(sql, rowMapper, id);
        detective.setCriminalCases(getSetCriminalCase(detective.getId()));
        return detective;
    }

    @Override
    public Detective save(Detective detective) {
        if(jdbcTemplate.update(
                "UPDATE detective " +
                        "SET version=?,create_at=?,modified_at=?,username=?,firstname=?,lastname=?,password=?," +
                        "hiring_date=?,badge_number=?,rank=?,armed=?,status=? " +
                        " where id =?",
                detective.getVersion(),detective.getCreateAt(),detective.getModifiedAt(),
                detective.getUsername(),detective.getFirstName(),detective.getLastName(),
                detective.getPassword(),detective.getHiringDate(), detective.getBadgeNumber(),
                detective.getRank(),detective.getArmed(),
                detective.getStatus(),detective.getId())>0) return detective;
        if(jdbcTemplate.update(
                "INSERT INTO detective " +
                        "(id,version,create_at,modified_at,username,firstname,lastname,password,hiring_date," +
                        "badge_number,rank,armed,status)" +
                        "VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?)",
                detective.getId(),detective.getVersion(),detective.getCreateAt(),
                detective.getModifiedAt(), detective.getUsername(),detective.getFirstName(),
                detective.getLastName(), detective.getPassword(),detective.getHiringDate(),
                detective.getBadgeNumber(), detective.getRank(),detective.getArmed(),
                detective.getStatus())>0) return detective;
        return null;
    }


    @Override
    public boolean remove(long id) {
        return jdbcTemplate.update("delete from DETECTIVE where ID =? ", id)>0;
    }

    @Override
    public List<Detective> getAll() {
        String sql = "select * from detective";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Set<CriminalCase> getSetCriminalCase(long detectiveId)
    {
        String sql = "SELECT * FROM Criminal_Case right join working_detective_case on criminal_case.id=working_detective_case.criminal_case_id" +
        " where working_detective_case.detective_id = ?";
        RowMapper<CriminalCase> criminalCaseRowMapper = new CriminalCaseRowMapper();
        return new HashSet<>(jdbcTemplate.query(sql, criminalCaseRowMapper,detectiveId));
    }
}

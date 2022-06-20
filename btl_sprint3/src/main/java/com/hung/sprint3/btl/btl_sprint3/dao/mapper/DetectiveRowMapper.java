package com.hung.sprint3.btl.btl_sprint3.dao.mapper;

import com.hung.sprint3.btl.btl_sprint3.model.Detective;
import com.hung.sprint3.btl.btl_sprint3.model.util.DateUtil;
import com.hung.sprint3.btl.btl_sprint3.model.util.EmploymentStatus;
import com.hung.sprint3.btl.btl_sprint3.model.util.Rank;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetectiveRowMapper implements RowMapper<Detective> {
    @Override
    public Detective mapRow(ResultSet rs, int rowNum) throws SQLException {

        Detective detective = new Detective();
        detective.setId(rs.getLong("id"));
        detective.setVersion(rs.getInt("version"));
        detective.setCreateAt(DateUtil.getLocalDateTime(rs.getString("create_at")));
        detective.setModifiedAt(DateUtil.getLocalDateTime(rs.getString("modified_at")));
        detective.setUsername(rs.getString("username"));
        detective.setFirstName(rs.getString("firstname"));
        detective.setLastName(rs.getString("lastname"));
        detective.setPassword(rs.getString("password"));
        detective.setHiringDate(DateUtil.getLocalDateTime(rs.getString("hiring_date")));
        detective.setBadgeNumber(rs.getString("badge_number"));
        detective.setRank(Rank.valueOf(rs.getString("rank")));
        detective.setArmed(rs.getBoolean("armed"));
        detective.setStatus(EmploymentStatus.valueOf(rs.getString("status")));

        return detective;
    }
}

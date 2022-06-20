package com.hung.sprint3.btl.btl_sprint3.dao.mapper;

import com.hung.sprint3.btl.btl_sprint3.model.CriminalCase;
import com.hung.sprint3.btl.btl_sprint3.model.Detective;
import com.hung.sprint3.btl.btl_sprint3.model.util.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CriminalCaseRowMapper implements RowMapper<CriminalCase> {
    @Override
    public CriminalCase mapRow(ResultSet rs, int rowNum) throws SQLException {

        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setId(rs.getLong("id"));
        criminalCase.setVersion(rs.getInt("version"));
        criminalCase.setCreateAt(DateUtil.getLocalDateTime(rs.getString("create_at")));
        criminalCase.setModifiedAt(DateUtil.getLocalDateTime(rs.getString("modified_at")));
        criminalCase.setNumber(rs.getString("number"));

        CaseType type = CaseType.valueOf(rs.getString("type"));
        criminalCase.setType(type);

        criminalCase.setShortDescription(rs.getString("short_descripton"));
        criminalCase.setDetailedDescription(rs.getString("deteiled_descripton"));

        CaseStatus status = CaseStatus.valueOf(rs.getString("status"));
        criminalCase.setStatus(status);
        criminalCase.setNotes(rs.getString("notes"));

        return criminalCase;
    }
}

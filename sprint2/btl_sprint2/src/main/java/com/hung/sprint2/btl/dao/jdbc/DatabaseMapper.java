package com.hung.sprint2.btl.dao.jdbc;

import com.hung.sprint2.btl.model.CriminalCase;
import com.hung.sprint2.btl.model.Detective;
import com.hung.sprint2.btl.model.Evidence;
import com.hung.sprint2.btl.model.util.CaseStatus;
import com.hung.sprint2.btl.model.util.CaseType;
import com.hung.sprint2.btl.model.util.EmploymentStatus;
import com.hung.sprint2.btl.model.util.Rank;
import com.hung.sprint2.btl.util.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashSet;

@Slf4j
public class DatabaseMapper {


    public static CriminalCase getCriminalCase(ResultSet criminalCaseResult)
    {
        CriminalCase criminalCase = new CriminalCase();
        try {

            criminalCase.setId(criminalCaseResult.getLong("id"));
            criminalCase.setVersion(criminalCaseResult.getInt("version"));
            criminalCase.setCreateAt(DateUtil.getLocalDateTime(criminalCaseResult.getString("create_at")));
            criminalCase.setModifiedAt(DateUtil.getLocalDateTime(criminalCaseResult.getString("modified_at")));
            criminalCase.setNumber(criminalCaseResult.getString("number"));

            CaseType type = CaseType.valueOf(criminalCaseResult.getString("type"));
            criminalCase.setType(type);

            criminalCase.setShortDescription(criminalCaseResult.getString("short_descripton"));
            criminalCase.setDetailedDescription(criminalCaseResult.getString("deteiled_descripton"));

            CaseStatus status = CaseStatus.valueOf(criminalCaseResult.getString("status"));
            criminalCase.setStatus(status);
            criminalCase.setNotes(criminalCaseResult.getString("notes"));


        } catch (SQLException e) {
            log.error(e.toString());
            return null;
        }

        return criminalCase;
    }
    public static Detective getDetective(ResultSet detectiveResult) {
        Detective detective = new Detective();
        try {
            detective.setId(detectiveResult.getLong("id"));
            detective.setVersion(detectiveResult.getInt("version"));
            detective.setCreateAt(DateUtil.getLocalDateTime(detectiveResult.getString("create_at")));
            detective.setModifiedAt(DateUtil.getLocalDateTime(detectiveResult.getString("modified_at")));
            detective.setUsername(detectiveResult.getString("username"));
            detective.setFistName(detectiveResult.getString("firstname"));
            detective.setLastName(detectiveResult.getString("lastname"));
            detective.setPassword(detectiveResult.getString("password"));
            detective.setHiringDate(DateUtil.getLocalDateTime(detectiveResult.getString("hiring_date")));
            detective.setBadgeNumber(detectiveResult.getString("badge_number"));
            detective.setRank(Rank.valueOf(detectiveResult.getString("rank")));
            detective.setArmed(detectiveResult.getBoolean("armed"));
            detective.setStatus(EmploymentStatus.valueOf(detectiveResult.getString("status")));
        } catch (SQLException e) {
            log.error(e.toString());
            return null;
        }
        return detective;
    }
}

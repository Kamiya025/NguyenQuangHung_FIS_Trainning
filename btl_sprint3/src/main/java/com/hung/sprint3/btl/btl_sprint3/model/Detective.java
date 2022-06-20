package com.hung.sprint3.btl.btl_sprint3.model;



import com.hung.sprint3.btl.btl_sprint3.model.util.EmploymentStatus;
import com.hung.sprint3.btl.btl_sprint3.model.util.Rank;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "detective")
public class Detective extends AbstractEntity {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    @Column(name = "hiring_date")
    private LocalDateTime hiringDate;
    @Column(name = "badge_number")
    private String badgeNumber;
    @Enumerated(EnumType.STRING)
    private Rank rank;

    private Boolean armed;

    @Enumerated(EnumType.STRING)
    private EmploymentStatus status;
    @ManyToMany
    private Set<CriminalCase> criminalCases;
//    private Set<TrackEntry> trackEntries;

    public Detective() {}
    public Detective(int version, LocalDateTime createAt, LocalDateTime modifiedAt,
                     String username, String fistName, String lastName, String password,
                     LocalDateTime hiringDate, String badgeNumber, Rank rank, Boolean armed,
                     EmploymentStatus status, Set<CriminalCase> criminalCases
//                     ,Set<TrackEntry> trackEntries
    ) {
        super(version, createAt, modifiedAt);
        this.username = username;
        this.firstName = fistName;
        this.lastName = lastName;
        this.password = password;
        this.hiringDate = hiringDate;
        this.badgeNumber = badgeNumber;
        this.rank = rank;
        this.armed = armed;
        this.status = status;
        this.criminalCases = criminalCases;
//        this.trackEntries = trackEntries;
    }
    public Detective(long id, int version, LocalDateTime createAt, LocalDateTime modifiedAt,
                     String username, String fistName, String lastName, String password,
                     LocalDateTime hiringDate, String badgeNumber, Rank rank, Boolean armed,
                     EmploymentStatus status, Set<CriminalCase> criminalCases
//                     ,Set<TrackEntry> trackEntries
    ) {
        super(id, version, createAt, modifiedAt);
        this.username = username;
        this.firstName = fistName;
        this.lastName = lastName;
        this.password = password;
        this.hiringDate = hiringDate;
        this.badgeNumber = badgeNumber;
        this.rank = rank;
        this.armed = armed;
        this.status = status;
        this.criminalCases = criminalCases;
//        this.trackEntries = trackEntries;
    }

    public void addCriminalCase(CriminalCase criminalCase)
    {
        criminalCases.add(criminalCase);
    }
    public void removeCriminalCase(CriminalCase criminalCase)
    {
        criminalCases.remove(criminalCase);
    }


}

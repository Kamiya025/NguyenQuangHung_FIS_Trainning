package com.fis.training.hung.btlsprint4.model;



import com.fis.training.hung.btlsprint4.util.EmploymentStatus;
import com.fis.training.hung.btlsprint4.util.Rank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "assigned")
    private Set<CriminalCase> criminalCases;

    @OneToMany(mappedBy = "detective",cascade = CascadeType.ALL)
    private Set<TrackEntry> trackEntries;


    public void addCriminalCase(CriminalCase criminalCase) {
        criminalCases.add(criminalCase);
    }
    public void removeCriminalCase(CriminalCase criminalCase) {
        criminalCases.add(criminalCase);
    }


}

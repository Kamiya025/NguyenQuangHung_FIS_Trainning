package com.fis.training.hung.btlsprint4.model;


import com.fis.training.hung.btlsprint4.util.CaseStatus;
import com.fis.training.hung.btlsprint4.util.CaseType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CriminalCase extends AbstractEntity {

    private String number;
    private CaseType type;
    @Column(name = "short_descripton")
    private String shortDescription;
    @Column(name = "deteiled_descripton")
    private String detailedDescription;
    @Enumerated(EnumType.STRING)
    private CaseStatus status;
    private String notes;

    @OneToMany(mappedBy = "criminalCase", cascade = CascadeType.ALL)
    private Set<Evidence> evidenceSet;

    @ManyToOne
    @JoinColumn(name = "detective_id", referencedColumnName = "id", nullable = false)
    private Detective leadInvestigator;

    @ManyToMany
    @JoinTable(name = "working_detective_case")
    private Set<Detective> assigned;

}

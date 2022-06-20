package com.hung.sprint3.btl.btl_sprint3.model;



import com.hung.sprint3.btl.btl_sprint3.model.util.CaseStatus;
import com.hung.sprint3.btl.btl_sprint3.model.util.CaseType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "criminal_case")
@Getter
@Setter
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
//    private Set<Evidence> evidenceSet;
    @OneToOne
    @JoinColumn(name = "detective_id", referencedColumnName = "id")
    private Detective loadInvestigator;

    @ManyToMany
    @JoinTable(name = "working_detective_case")
    private Set<Detective> assigned;

    public CriminalCase() {}
    public CriminalCase(int version, LocalDateTime createAt, LocalDateTime modifiedAt,
                        String number, CaseType type, String shortDescription, String detailedDescription,
                        CaseStatus status, String notes,
//                        Set<Evidence> evidenceSet,
                        Detective loadInvestigator,
                        Set<Detective> assigned) {
        super(version, createAt, modifiedAt);
        this.number = number;
        this.type = type;
        this.shortDescription = shortDescription;
        this.detailedDescription = detailedDescription;
        this.status = status;
        this.notes = notes;
//        this.evidenceSet = evidenceSet;
        this.loadInvestigator = loadInvestigator;
        this.assigned = assigned;
    }
    public CriminalCase(long id, int version, LocalDateTime createAt, LocalDateTime modifiedAt,
                        String number, CaseType type, String shortDescription, String detailedDescription,
                        CaseStatus status, String notes,
//                        Set<Evidence> evidenceSet,
                        Detective loadInvestigator,
                        Set<Detective> assigned) {
        super(id, version, createAt, modifiedAt);
        this.number = number;
        this.type = type;
        this.shortDescription = shortDescription;
        this.detailedDescription = detailedDescription;
        this.status = status;
        this.notes = notes;
//        this.evidenceSet = evidenceSet;
        this.loadInvestigator = loadInvestigator;
        this.assigned = assigned;
    }

    public void addAssigned(Detective detective)
    {
        assigned.add(detective);
    }
    public void removeAssigned(Detective detective)
    {
        assigned.remove(detective);
    }
}

package com.fis.training.hung.btlsprint4.model;

import com.fis.training.hung.btlsprint4.model.AbstractEntity;
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
public class Evidence extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "criminal_case_id", nullable = false)
    private CriminalCase criminalCase;

    @ManyToOne
    @JoinColumn(name = "storage_id")
    private Storage storage;

    private String number;
    private String itemName;
    private String notes;
    private Boolean archived;

    @OneToMany(mappedBy = "evidence", cascade = CascadeType.ALL)
    private Set<TrackEntry> trackEntries;

}

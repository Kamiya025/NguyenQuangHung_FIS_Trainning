package com.fis.training.hung.btlsprint4.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
public class Storage extends AbstractEntity {

    private String name;
    private String location;

    @OneToMany(mappedBy = "storage")
    private Set<Evidence> evidenceSet;

}

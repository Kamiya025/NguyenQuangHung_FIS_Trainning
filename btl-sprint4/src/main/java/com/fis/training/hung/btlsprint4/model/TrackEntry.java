package com.fis.training.hung.btlsprint4.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fis.training.hung.btlsprint4.util.TrackAction;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TrackEntry extends AbstractEntity {

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name="evidence_id",nullable = false)
    @JsonBackReference
    private Evidence evidence;

    @ManyToOne
    @JoinColumn(name="detective_id",nullable = false)
    @JsonBackReference
    private Detective detective;

    @Enumerated(EnumType.STRING)
    private TrackAction trackAction;

    private String reason;

}

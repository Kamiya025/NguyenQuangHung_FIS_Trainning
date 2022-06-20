package com.hung.sprint3.btl.btl_sprint3.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private int version;
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    public AbstractEntity() {
    }
    public AbstractEntity( int version, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.version = version;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
    public AbstractEntity(long id, int version, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.version = version;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }


}

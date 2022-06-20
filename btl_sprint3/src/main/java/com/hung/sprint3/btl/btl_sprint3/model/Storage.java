package com.hung.sprint3.btl.btl_sprint3.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
public class Storage extends AbstractEntity {

    private String name;
    private String location;
    private Set<Evidence> evidenceSet;

    public Storage() {
    }

    public Storage(long id, int version, LocalDateTime createAt, LocalDateTime modifiedAt, String name,
                   String location, Set<Evidence> evidenceSet) {
        super(id, version, createAt, modifiedAt);
        this.name = name;
        this.location = location;
        this.evidenceSet = evidenceSet;
    }

}

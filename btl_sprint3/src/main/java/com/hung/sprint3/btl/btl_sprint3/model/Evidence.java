package com.hung.sprint3.btl.btl_sprint3.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
public class Evidence extends AbstractEntity {

    private CriminalCase criminalCase;
    private Storage storage;
    private String number;
    private String itemName;
    private String notes;
    private Boolean archived;
    private Set<TrackEntry> trackEntries;


    public Evidence() {
    }

    public Evidence(long id, int version, LocalDateTime createAt, LocalDateTime modifiedAt, CriminalCase criminalCase, Storage storage, String number, String itemName, String notes, Boolean archived, Set<TrackEntry> trackEntries) {
        super(id, version, createAt, modifiedAt);
        this.criminalCase = criminalCase;
        this.storage = storage;
        this.number = number;
        this.itemName = itemName;
        this.notes = notes;
        this.archived = archived;
        this.trackEntries = trackEntries;
    }

}

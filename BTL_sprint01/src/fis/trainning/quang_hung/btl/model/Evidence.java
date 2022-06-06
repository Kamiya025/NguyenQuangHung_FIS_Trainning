package fis.trainning.quang_hung.btl.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class Evidence extends AbstractEntity {

    private CriminalCase criminalCase;
    private Storage storage;
    private String number;
    private String itemName;
    private String notes;
    private Boolean archived;
    private Set<TrackEntry> trackEntries;

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

    public CriminalCase getCriminalCase() {
        return criminalCase;
    }

    public void setCriminalCase(CriminalCase criminalCase) {
        this.criminalCase = criminalCase;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Set<TrackEntry> getTrackEntries() {
        return trackEntries;
    }

    public void setTrackEntries(Set<TrackEntry> trackEntries) {
        this.trackEntries = trackEntries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Evidence evidence = (Evidence) o;
        return Objects.equals(criminalCase, evidence.criminalCase) && Objects.equals(storage, evidence.storage) && Objects.equals(number, evidence.number) && Objects.equals(itemName, evidence.itemName) && Objects.equals(notes, evidence.notes) && Objects.equals(archived, evidence.archived) && Objects.equals(trackEntries, evidence.trackEntries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), criminalCase, storage, number, itemName, notes, archived, trackEntries);
    }

    @Override
    public String toString() {
        return "Evidence{" +
                "criminalCase=" + criminalCase +
                ", storage=" + storage +
                ", number='" + number + '\'' +
                ", itemName='" + itemName + '\'' +
                ", notes='" + notes + '\'' +
                ", archived=" + archived +
                ", trackEntries=" + trackEntries +
                '}';
    }
}

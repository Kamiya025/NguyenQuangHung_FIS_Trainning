package fis.trainning.quang_hung.btl.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class Storage extends AbstractEntity {

    private String name;
    private String location;
    private Set<Evidence> evidenceSet;

    public Storage(long id, int version, LocalDateTime createAt, LocalDateTime modifiedAt, String name, String location, Set<Evidence> evidenceSet) {
        super(id, version, createAt, modifiedAt);
        this.name = name;
        this.location = location;
        this.evidenceSet = evidenceSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Evidence> getEvidenceSet() {
        return evidenceSet;
    }

    public void setEvidenceSet(Set<Evidence> evidenceSet) {
        this.evidenceSet = evidenceSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Storage storage = (Storage) o;
        return Objects.equals(name, storage.name) && Objects.equals(location, storage.location) && Objects.equals(evidenceSet, storage.evidenceSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, location, evidenceSet);
    }

    @Override
    public String toString() {
        return "Storage{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", evidenceSet=" + evidenceSet +
                '}';
    }
}

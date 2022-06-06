package fis.trainning.quang_hung.btl.model;

import fis.trainning.quang_hung.btl.util.CaseStatus;
import fis.trainning.quang_hung.btl.util.CaseType;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class CriminalCase extends AbstractEntity {

    private String number;
    private CaseType type;
    private String shortDescription;
    private String detailedDescription;
    private CaseStatus status;
    private String notes;
    private Set<Evidence> evidenceSet;
    private Detective loadInvestigator;
    private Set<Detective> assigned;

    public CriminalCase(long id, int version, LocalDateTime createAt, LocalDateTime modifiedAt, String number, CaseType type, String shortDescription, String detailedDescription, CaseStatus status, String notes, Set<Evidence> evidenceSet, Detective loadInvestigator, Set<Detective> assigned) {
        super(id, version, createAt, modifiedAt);
        this.number = number;
        this.type = type;
        this.shortDescription = shortDescription;
        this.detailedDescription = detailedDescription;
        this.status = status;
        this.notes = notes;
        this.evidenceSet = evidenceSet;
        this.loadInvestigator = loadInvestigator;
        this.assigned = assigned;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public CaseType getType() {
        return type;
    }

    public void setType(CaseType type) {
        this.type = type;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public CaseStatus getStatus() {
        return status;
    }

    public void setStatus(CaseStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<Evidence> getEvidenceSet() {
        return evidenceSet;
    }

    public void setEvidenceSet(Set<Evidence> evidenceSet) {
        this.evidenceSet = evidenceSet;
    }

    public Detective getLoadInvestigator() {
        return loadInvestigator;
    }

    public void setLoadInvestigator(Detective loadInvestigator) {
        this.loadInvestigator = loadInvestigator;
    }

    public Set<Detective> getAssigned() {
        return assigned;
    }

    public void setAssigned(Set<Detective> assigned) {
        this.assigned = assigned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CriminalCase that = (CriminalCase) o;
        return Objects.equals(number, that.number) && Objects.equals(type, that.type) && Objects.equals(shortDescription, that.shortDescription) && Objects.equals(detailedDescription, that.detailedDescription) && status == that.status && Objects.equals(notes, that.notes) && Objects.equals(evidenceSet, that.evidenceSet) && Objects.equals(loadInvestigator, that.loadInvestigator) && Objects.equals(assigned, that.assigned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number, type, shortDescription, detailedDescription, status, notes, evidenceSet, loadInvestigator, assigned);
    }

    @Override
    public String toString() {
        return "CriminalCase{" +
                "number='" + number + '\'' +
                ", type=" + type +
                ", shortDescription='" + shortDescription + '\'' +
                ", detailedDescription='" + detailedDescription + '\'' +
                ", status=" + status +
                ", notes='" + notes + '\'' +
                ", evidenceSet=" + evidenceSet +
                ", loadInvestigator=" + loadInvestigator +
                ", assigned=" + assigned +
                '}';
    }
}

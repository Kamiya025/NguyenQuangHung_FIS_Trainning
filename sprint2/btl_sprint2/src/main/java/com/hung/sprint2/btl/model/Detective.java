package com.hung.sprint2.btl.model;

import com.hung.sprint2.btl.model.util.EmploymentStatus;
import com.hung.sprint2.btl.model.util.Rank;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class Detective extends AbstractEntity {

    private String username;
    private String fistName;
    private String lastName;
    private String password;
    private LocalDateTime hiringDate;
    private String badgeNumber;
    private Rank rank;
    private Boolean armed;
    private EmploymentStatus status;
    private Set<CriminalCase> criminalCases;
    private Set<TrackEntry> trackEntries;

    public Detective() {}

    public Detective(long id, int version, LocalDateTime createAt, LocalDateTime modifiedAt,
                     String username, String fistName, String lastName, String password,
                     LocalDateTime hiringDate, String badgeNumber, Rank rank, Boolean armed,
                     EmploymentStatus status, Set<CriminalCase> criminalCases,
                     Set<TrackEntry> trackEntries) {
        super(id, version, createAt, modifiedAt);
        this.username = username;
        this.fistName = fistName;
        this.lastName = lastName;
        this.password = password;
        this.hiringDate = hiringDate;
        this.badgeNumber = badgeNumber;
        this.rank = rank;
        this.armed = armed;
        this.status = status;
        this.criminalCases = criminalCases;
        this.trackEntries = trackEntries;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDateTime hiringDate) {
        this.hiringDate = hiringDate;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Boolean getArmed() {
        return armed;
    }

    public void setArmed(Boolean armed) {
        this.armed = armed;
    }

    public EmploymentStatus getStatus() {
        return status;
    }

    public void setStatus(EmploymentStatus status) {
        this.status = status;
    }

    public Set<CriminalCase> getCriminalCases() {
        return criminalCases;
    }

    public void setCriminalCases(Set<CriminalCase> criminalCases) {
        this.criminalCases = criminalCases;
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
        Detective detective = (Detective) o;
        return Objects.equals(username, detective.username) && Objects.equals(fistName, detective.fistName) && Objects.equals(lastName, detective.lastName) && Objects.equals(password, detective.password) && Objects.equals(hiringDate, detective.hiringDate) && Objects.equals(badgeNumber, detective.badgeNumber) && rank == detective.rank && Objects.equals(armed, detective.armed) && status == detective.status && Objects.equals(criminalCases, detective.criminalCases) && Objects.equals(trackEntries, detective.trackEntries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, fistName, lastName, password, hiringDate, badgeNumber, rank, armed, status, criminalCases, trackEntries);
    }

    @Override
    public String toString() {
        return "Detective{" +
                "username='" + username + '\'' +
                ", fistName='" + fistName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", hiringDate=" + hiringDate +
                ", badgeNumber='" + badgeNumber + '\'' +
                ", rank=" + rank +
                ", armed=" + armed +
                ", status=" + status +
                ", criminalCases=" + criminalCases +
                ", trackEntries=" + trackEntries +
                '}';
    }
}

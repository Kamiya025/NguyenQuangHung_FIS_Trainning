package com.hung.sprint2.btl.model;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class AbstractEntity {

    private long id;
    private int version;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public AbstractEntity() {
    }

    public AbstractEntity(long id, int version, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.version = version;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id && version == that.version && Objects.equals(createAt, that.createAt) && Objects.equals(modifiedAt, that.modifiedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, createAt, modifiedAt);
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "id=" + id +
                ", version=" + version +
                ", createAt=" + createAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}

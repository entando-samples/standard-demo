package org.entando.statements.model;

import java.time.LocalDate;

public class Statement {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String description;
    private LocalDate createdAt;
    private Boolean read;
    private String userId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Statement description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public Statement createdAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean isRead() {
        return read;
    }

    public Statement read(Boolean read) {
        this.read = read;
        return this;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public String getUserId() {
        return userId;
    }

    public Statement userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Statement)) {
            return false;
        }
        return id != null && id.equals(((Statement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Statement{" +
                "id=" + getId() +
                ", description='" + getDescription() + "'" +
                ", createdAt='" + getCreatedAt() + "'" +
                ", read='" + isRead() + "'" +
                ", userId='" + getUserId() + "'" +
                "}";
    }
}

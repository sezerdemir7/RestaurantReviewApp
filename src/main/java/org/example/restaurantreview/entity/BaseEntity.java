package org.example.restaurantreview.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @CreatedDate()
    @CurrentTimestamp
    private Timestamp createdDate;
    @LastModifiedDate
    @UpdateTimestamp
    private Timestamp LastModifiedDate;

    public BaseEntity() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BaseEntity that = (BaseEntity) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastModifiedDate() {
        return LastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        LastModifiedDate = lastModifiedDate;
    }
}

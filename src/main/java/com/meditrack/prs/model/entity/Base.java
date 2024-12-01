package com.meditrack.prs.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class Base {

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Date createdOn;

    @Column(nullable = false, length = 50)
    @CreatedBy
    private String createdBy;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date modifiedOn;

    @Column(length = 50, nullable = false)
    @LastModifiedBy
    private String modifiedBy;

    @Version
    private Long rowVersion;

    private Long hospitalId;
}

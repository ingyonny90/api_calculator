package com.api.calculator.model.audit;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;


@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    @CreatedBy
    protected String createUser;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date creationTime;

    @LastModifiedBy
    protected String updateUser;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updateTime;
}

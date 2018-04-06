package com.guilherme.desafio.concrete.desafiojava.model;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

	@Column(name = "created", nullable = false, updatable = false)
	@CreatedDate
	@Temporal(TIMESTAMP)
	protected Date created;
		
	@Column(name = "modified")
	@LastModifiedDate
	@Temporal(TIMESTAMP)
	protected Date modified;
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
	
}
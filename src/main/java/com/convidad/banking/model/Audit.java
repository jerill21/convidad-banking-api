package com.convidad.banking.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;

import com.convidad.banking.utils.DateUtils;

public class Audit {

	@Field("creationDate")
	private Date creationDate;

	@Field("modifDate")
	private Date modifDate;

	public Audit() {
		this.creationDate = DateUtils.getCurrentDate();
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getModifDate() {
		return modifDate;
	}

	public void setModifDate(Date modifDate) {
		this.modifDate = modifDate;
	}
}

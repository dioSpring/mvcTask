package org.diosoft.spring.mvcTask.model;

import java.util.Date;
import java.util.UUID;

public class User {
	private UUID id;
	private String name;
	private Date dateofbirth;

	public User() {
		id= UUID.randomUUID();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

}

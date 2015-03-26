package org.diosoft.spring.mvcTask.model;

import java.util.Date;
import java.util.UUID;

import org.hibernate.validator.constraints.NotEmpty;

public class User {
	private UUID id;
	@NotEmpty
	private String name;
	private Date dateofbirth;

	public User() {
		id= UUID.randomUUID();
		System.out.println("has been created new user: " + id);
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

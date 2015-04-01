package org.diosoft.spring.mvcTask.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_inden;

	private String id;

	@NotEmpty(message = "You should enter First Name")
	private String firstname;
	@NotEmpty(message = "You should enter First Name")
	private String lastname;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateofbirth;

	private Map<UUID, Questionaire> questionaireMap = new HashMap<>();

	public User() {
		id = UUID.randomUUID().toString();
	}

	public Long getId_inden() {
		return id_inden;
	}

	public void setId_inden(Long id_inden) {
		this.id_inden = id_inden;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public Map<UUID, Questionaire> getQuestionaireMap() {
		return questionaireMap;
	}

	public void setQuestionaireMap(Map<UUID, Questionaire> questionaireMap) {
		this.questionaireMap = questionaireMap;
	}
}

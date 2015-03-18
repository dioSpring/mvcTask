package org.diosoft.spring.mvcTask.services;

import java.io.Serializable;
import java.util.UUID;

/**
 * User data business object.
 * Stores users data and his poll answers.
 *
 * TODO add gender field
 *
 * @author Vasyl Zarva
 */
public class UserBO implements Serializable, Cloneable {

	/**
	 * Generated SerialVersionUID.
	 */
	private static final long serialVersionUID = -4066662426799700901L;

	/**
	 * Unique users identifier.
	 */
	private String id;

	/**
	 * Name.
	 */
	private String firstName;

	/**
	 * Age.
	 */
	private int age;

	/**
	 * Age, when user starts to smoke.
	 */
	private int startToSmokeAge;

	/**
	 * How many cigarettes does the user smoke per day.
	 */
	private int cigarettePerDay;

	/**
	 *
	 */
	public UserBO() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getStartToSmokeAge() {
		return startToSmokeAge;
	}

	public void setStartToSmokeAge(int startToSmokeAge) {
		this.startToSmokeAge = startToSmokeAge;
	}

	public int getCigarettePerDay() {
		return cigarettePerDay;
	}

	public void setCigarettePerDay(int cigarettePerDay) {
		this.cigarettePerDay = cigarettePerDay;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserBO userBO = (UserBO) o;

		if (id != null ? !id.equals(userBO.id) : userBO.id != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("UserBO{");
		sb.append("id='").append(id).append('\'');
		sb.append(", firstName='").append(firstName).append('\'');
		sb.append(", age=").append(age);
		sb.append(", startToSmokeAge=").append(startToSmokeAge);
		sb.append(", cigarettePerDay=").append(cigarettePerDay);
		sb.append('}');
		return sb.toString();
	}

	@Override
	protected UserBO clone() {
		try {
			return UserBO.class.cast(super.clone());
		} catch (final CloneNotSupportedException e) {
			throw new RuntimeException("Failed to cast this[" + this.toString() + "] class cause " + e.getMessage());
		}
	}
}

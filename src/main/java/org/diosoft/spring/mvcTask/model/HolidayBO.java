package org.diosoft.spring.mvcTask.model;

import java.io.Serializable;
import java.util.UUID;

public class HolidayBO implements Serializable, Cloneable {

	private static final long serialVersionUID = -4066662426799700901L;
	private UUID id;
	private String memorableHoliday;
	
	private String unlikedHoliday;

	public HolidayBO() {
		this.id = UUID.randomUUID();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getMemorableHoliday() {
		return memorableHoliday;
	}

	public void setMemorableHoliday(String memorableHoliday) {
		this.memorableHoliday = memorableHoliday;
	}

	public String getUnlikedHoliday() {
		return unlikedHoliday;
	}

	public void setUnlikedHoliday(String unlikedHoliday) {
		this.unlikedHoliday = unlikedHoliday;
	}

}

package org.diosoft.spring.mvcTask.services;

import java.util.List;

import org.diosoft.spring.mvcTask.model.Holiday;

public interface HolidayService {

	Holiday get(Holiday answer);

	Holiday save(Holiday answer);

	List<Holiday> getAllAnswers();
}

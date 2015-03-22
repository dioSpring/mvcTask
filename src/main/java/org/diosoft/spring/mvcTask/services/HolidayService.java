package org.diosoft.spring.mvcTask.services;

import java.util.List;

import org.diosoft.spring.mvcTask.model.HolidayBO;

public interface HolidayService {

	HolidayBO get(HolidayBO answer);

	HolidayBO save(HolidayBO answer);

	List<HolidayBO> getAllAnswers();
}

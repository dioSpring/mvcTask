package org.diosoft.spring.mvcTask.services;

import java.util.List;

public interface HolidayService {

	HolidayBO get(HolidayBO answer);

	HolidayBO save(HolidayBO answer);

	List<HolidayBO> getAllAnswers();
}

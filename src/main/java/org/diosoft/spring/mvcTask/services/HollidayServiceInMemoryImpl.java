package org.diosoft.spring.mvcTask.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class HollidayServiceInMemoryImpl implements HolidayService {

	private final List<HolidayBO> storage = new ArrayList<HolidayBO>();

	@Override
	public HolidayBO save(HolidayBO toSave) {
		storage.add(toSave);
		return toSave;
	}

	@Override
	public HolidayBO get(HolidayBO answer) {
		int index = storage.indexOf(answer);
		return storage.get(index);
	}

	@Override
	public List<HolidayBO> getAllAnswers() {
		return storage;
	}

}

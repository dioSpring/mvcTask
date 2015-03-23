package org.diosoft.spring.mvcTask.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.diosoft.spring.mvcTask.model.UserBO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * {@link PollService} implementation.
 *
 * @author Vasyl Zarva
 */
@Service
public class PollServiceInMemoryImpl implements PollService {

	/**
	 * Users storage.
	 * Key: users unique identifier field.
	 */
	private final Map<String, UserBO> storage = new ConcurrentHashMap<>();

	@Override
	public UserBO get(final String id) {
		if (StringUtils.isEmpty(id))
			throw new IllegalArgumentException("Parameter id is empty.");

		return storage.get(id);
	}

	@Override
	public UserBO save(final UserBO toSave) {
		if (toSave == null)
			throw new IllegalArgumentException("Parameter toSave is null.");
		if (StringUtils.isEmpty(toSave.getFirstName()))
			throw new IllegalArgumentException("Parameter toSave.firstname is empty.");
		if (StringUtils.isEmpty(toSave.getId()))
			toSave.setId(UUID.randomUUID().toString());

		storage.put(toSave.getId(), toSave.clone());
		return toSave;
	}

	@Override
	public UserBO delete(final String id) {
		if (StringUtils.isEmpty(id))
			throw new IllegalArgumentException("Parameter id is empty.");

		return storage.remove(id);
	}

	@Override
	public List<UserBO> getUsers() {
		final List<UserBO> result = new ArrayList<>();
		for (final UserBO user : storage.values())
			result.add(user.clone());

		return result;
	}
}

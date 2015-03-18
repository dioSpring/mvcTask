package org.diosoft.spring.mvcTask.services;

import java.util.List;

/**
 * Poll storage service.
 *
 * @author Vasyl Zarva
 */
public interface PollService {

	/**
	 * Get user data by given id.
	 *
	 * @param id
	 * 		user unique identifier
	 * @return {@link UserBO} or {@code null}, if not found
	 */
	UserBO get(String id);

	/**
	 * Save user data.
	 *
	 * @param toSave
	 * 		{@link UserBO}
	 * @return saved {@link UserBO}
	 */
	UserBO save(UserBO toSave);

	/**
	 * Delete user with given id.
	 *
	 * @param id
	 * 		user unique identifier
	 * @return deleted {@link UserBO}
	 */
	UserBO delete(String id);

	/**
	 * Get all users.
	 *
	 * @return {@link List} with {@link UserBO}
	 */
	List<UserBO> getUsers();
}

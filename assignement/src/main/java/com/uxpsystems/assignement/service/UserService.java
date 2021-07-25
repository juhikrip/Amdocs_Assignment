package com.uxpsystems.assignement.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uxpsystems.assignement.dao.User;
import com.uxpsystems.assignement.repository.UserRepository;

/**
 * UserService uses UserRepository for providing the implementation of the CRUD
 * operations.
 * 
 * @author JuhiKriplani
 *
 */
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	/**
	 * Fetching all the users record by using the method findaAll() of JpaRepository
	 * @return {List<User>} :- User Record
	 */
	public List<User> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}

	/**
	 * Get a specific user details by using the method findById() of JpaRepository
	 * @param id {long} -User ID
	 * @return {User} - user detail with specific ID
	 */
	public User getUserById(long id) {
		return userRepository.findById(id).get();
	}

	/**
	 * Saving a specific user record by using the method save() of JpaRepository
	 * @param users {User} - user details
	 */
	public void save(User users) {
		userRepository.save(users);
	}

	/**
	 * Deleting a specific user record by using the method deleteById() of
	 * JpaRepository
	 * @param id {long} - User Id
	 * @throws Exception -The class Exception and its subclasses are a form of
	 *                   Throwable that indicates conditions that a
	 *                   reasonableApplication might want to catch.
	 */
	public void delete(long id) throws Exception {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent())
			userRepository.deleteById(id);
		else
			throw new Exception();
	}

	/**
	 * Updating a specific user details by using User ID
	 * @param userDetails {User} - User Details
	 * @param id          {Long} - user Id
	 * @throws Exception -The class Exception and its subclasses are a form of
	 *                   Throwable that indicates conditions that a
	 *                   reasonableApplication might want to catch.
	 */

	public void update(User userDetails, long id) throws Exception {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			User userObj = user.get();
			userObj.setUserName(userDetails.getUserName());
			userObj.setPassword(userDetails.getPassword());
			userObj.setStatus(userDetails.getStatus());
			userRepository.save(userObj);
		} else
			throw new Exception();
	}

	/**
	 * Check User Id Exists or not 
	 * @param id {long} - userID
	 * @return {boolean}
	 */

	public boolean isUserExists(long id) {
		boolean result = userRepository.findById(id).isPresent();
		return result;
	}

}

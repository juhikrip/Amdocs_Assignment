package com.uxpsystems.assignement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.uxpsystems.assignement.dao.User;
import com.uxpsystems.assignement.service.UserService;
import com.uxpsystems.assignement.util.Constant;

@RestController
@RequestMapping("/assignement")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/**
	 * This method is used to fetch all the user details.
	 * @return {List} - user details list
	 */
	@GetMapping("/users")
	public List<User> getAllUsers() {
		List<User> userDetails = userService.getAllUsers();
		return userDetails;
	}
 
	/**
	 * This method is used to retrieve a specific user details on the basis of user ID
	 * @param userId {Long} - user Id
	 * @return User
	 */
	@GetMapping("/user/{id}")
	private User getUser(@PathVariable("id") long userId) {
		return userService.getUserById(userId);
	}

	/**
	 * Store the Password in encrypted format
	 * @param password {String} - User Password
	 * @return encrypted String
	 */
	private String getEncryptedPassword(String password) {
		String encrytedPassword = passwordEncoder.encode(password);
		return encrytedPassword;
	}

	/**
	 * This method is used to create a user
	 * @param users {User} - user details
	 * @return String 
	 * @throws Exception 
	 */
	//check user exists or not
	@PostMapping("/addUser")
	private String saveUser(@RequestBody User users) throws Exception {
		String password = users.getPassword();
		String encrytedPassword = getEncryptedPassword(password);
		users.setPassword(encrytedPassword);
		userService.save(users);
		return  Constant.ADDED_USER;
	}

	/**
	 * This method is used to delete a user on the basis of user ID
	 * @param userId {Long} - User ID
	 * @return String 
	 * @throws Exception -The class Exception and its subclasses are a form of Throwable that indicates
	 * 					 conditions that a reasonableApplication might want to catch.
	 */
	@DeleteMapping("/user/{id}")
	private String deleteBook(@PathVariable("id") long userId) throws Exception {
		userService.delete(userId);
		return Constant.DELETED_USER;
	}

  /**
   * This method is used to update the user details on the basis of user ID.
   * @param userId {Long} -user Id
   * @param userDetails {User} - user details like username, password,etc.
   * @return {String}
   * @throws Exception
   */
	@PutMapping("/user/{id}")
	private String update(@PathVariable("id") long userId, @RequestBody User userDetails) throws Exception {
		userService.update(userDetails, userId);
		return Constant.UPDATED_USER;
	}

}

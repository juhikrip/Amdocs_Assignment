package com.uxpsystems.assignement;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import com.uxpsystems.assignement.dao.User;
import com.uxpsystems.assignement.service.UserService;

@SpringBootTest
class AssignementApplicationTests {
	@Autowired
	private UserService service;

	/**
	 * Test Case for Create User.
	 */
	@Test
	@Rollback(false)
	@Order(1)
	public void testCreateUser() {
		User user = new User();
		user.setUserName("Anil");
		user.setPassword("Krip");
		user.setStatus(com.uxpsystems.assignement.dao.Status.Activated);
		service.save(user);
		assertThat(user.getId()).isGreaterThan(0);
	}

	/**
	 * Fetch all the User records
	 */
	@Test
	@Order(2)
	public void testListProducts() {
		List<User> user = (List<User>) service.getAllUsers();
		assertThat(user).size().isGreaterThan(0);
	}

	/**
	 * Test case for updating the username for a specific ID
	 * 
	 * @throws Exception - The class Exception and its subclasses are a form of
	 *                   Throwable that indicates conditions that a
	 *                   reasonableApplication might want to catch.
	 */
	@Test
	@Rollback(false)
	@Order(3)
	public void testUpdateProduct() throws Exception {
		User user = new User();
		user = service.getUserById(35);
		user.setUserName("ArchanaK");
		service.update(user, 35);
		User userUpdated = service.getUserById(35);
		assertThat(userUpdated.getUserName()).isEqualTo("ArchanaK");
	}

	/**
	 * Test Case for deleting the user
	 * 
	 * @throws Exception - The class Exception and its subclasses are a form of
	 *                   Throwable that indicates conditions that a
	 *                   reasonableApplication might want to catch.
	 */
	@Test
	@Rollback(false)
	@Order(4)
	public void testDeleteProduct() throws Exception {
		User user = new User();
		user = service.getUserById(42);
		service.delete(user.getId());
		boolean isUserExists = service.isUserExists(36);
		assertThat(isUserExists).isEqualTo(false);

	}

}

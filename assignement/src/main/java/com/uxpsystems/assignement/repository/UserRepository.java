package com.uxpsystems.assignement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uxpsystems.assignement.dao.User;
/**
 * UserRepository interface extends JpaRepository for CRUD operations
 * @author JuhiKriplani
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
}

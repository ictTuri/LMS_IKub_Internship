package com.project.lms.repository;

import java.util.List;

import com.project.lms.entity.UserEntity;

public interface UserRepository{

	void saveUser(UserEntity userToCreate);

	List<UserEntity> getAllUsers();

	UserEntity getUserById(Long id);

	boolean checkUserByUsernameEmail(String username, String email);

	UserEntity getUserByUsername(String username);

	boolean existUsername(String username);

	boolean existEmail(String email);

	UserEntity getActivatedUserById(long id);

	void updateUser(UserEntity userToDelete);

	UserEntity getActivatedUserByUsername(String username);

	void deleteUser(UserEntity userToHardDelete);

}

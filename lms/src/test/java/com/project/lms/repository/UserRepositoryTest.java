package com.project.lms.repository;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.project.lms.model.UserEntity;
import com.project.lms.repository.sql.UserRepositoryImpl;
import com.project.lms.utils.UserUtil;

@SpringBootTest
@Transactional
@ActiveProfiles("sql")
class UserRepositoryTest {

	@Autowired
	private UserRepositoryImpl userRepository;
	
	@Test
	void givenUsername_whenRetrieved_thenGetUserData() {
		UserEntity user = UserUtil.userTest();
		userRepository.saveUser(user);
		String username = "test";
		
		UserEntity userRetrieved = userRepository.getUserByUsername(username);
		
		Assertions.assertEquals(username, userRetrieved.getUsername());
	}
	
	@Test
	void givenUser_whenUpdate_thenGetUpdatedUser() {
		UserEntity user = UserUtil.userTest();
		userRepository.saveUser(user);
		
		UserEntity userToUpdate = userRepository.getUserByUsername(user.getUsername());
		
		userToUpdate.setFirstName("testUpdate");
		
		userRepository.updateUser(userToUpdate);
		
		Assertions.assertEquals("testUpdate", userRepository.getUserByUsername("test").getFirstName());
	}
	
	@Test
	void givenUser_whenSave_thenGetCreatedUser() {
		Integer userSize = userRepository.getAllUsers().size();
		UserEntity user = UserUtil.userAdmin();
		user.setEmail("userTest@gmail.com");
		user.setUsername("adminTest");
		userRepository.saveUser(user);
		
		Assertions.assertEquals(userSize+1, userRepository.getAllUsers().size());
		Assertions.assertNotNull(userRepository.getUserByUsername("adminTest"));
	}
	
	@Test
	void givenWrongUsername_whenRetrieved_thenGetNoResult() {
		String username = "notAUser";
		
		UserEntity user = userRepository.getUserByUsername(username);
		
		Assertions.assertNull(user);
	}
	
	
	@Test
	void givenUser_whenSoftDelete_thenGetNoResult() {
		UserEntity user = UserUtil.userAdmin();
		user.setEmail("userTest2@gmail.com");
		user.setUsername("adminTestUser");
		userRepository.saveUser(user);

		user.setActivated(Boolean.FALSE);
		userRepository.updateUser(user);
		
		Assertions.assertNull(userRepository.getActivatedUserByUsername("adminTestUser"));
	}

}

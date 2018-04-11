package com.guilherme.desafio.concrete.desafiojava.unit.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.guilherme.desafio.concrete.desafiojava.application.IUserService;
import com.guilherme.desafio.concrete.desafiojava.application.UserService;
import com.guilherme.desafio.concrete.desafiojava.repository.IUserRepository;

@RunWith(SpringRunner.class)
public class UserServiceTests {

	@TestConfiguration
	static class UserServiceTestContextConfiguration {

		@Bean
		public IUserService userService() {
			return new UserService();
		}
	}
	
	@Autowired
	private IUserService userService;
	
	@MockBean
	private IUserRepository userRepository;
	
	@Test
	public void UserExists_WithExistingId_ShouldReturnTrue() {
		
		// Arrange
		final long userId = 1;		
		Mockito.when(userRepository.exists(userId)).thenReturn(true);
				
		// Act		
		Boolean result = this.userService.UserExists(userId);
		
		// Assert
		assertTrue(result);
		
	} 
	
	

}

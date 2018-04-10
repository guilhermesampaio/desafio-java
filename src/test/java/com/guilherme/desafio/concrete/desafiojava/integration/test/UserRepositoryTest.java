package com.guilherme.desafio.concrete.desafiojava.integration.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.guilherme.desafio.concrete.desafiojava.model.User;
import com.guilherme.desafio.concrete.desafiojava.repository.IUserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private IUserRepository userRepository;
	
	
	@Test
	public void userExistsByEmail_WithExistingEmail_ShouldReturnTrue() {
		
		// Arrange
		final String email = "some.email@provider.com";
		User userTest = new User();
		userTest.setEmail(email);		
		userTest.setCreated(new Date());
		this.entityManager.persist(userTest);
		
		// Act
		Boolean result = this.userRepository.existsByEmail(email);
		
		// Assert		
		assertTrue(result);
	}
	
	@Test
	public void userExistsByEmail_WithInexistent_ShouldReturnFalse() {
		
		// Arrange
		final String email = "some.email@provider.com";
		User userTest = new User();
		userTest.setEmail(email);		
		userTest.setCreated(new Date());
		this.entityManager.persist(userTest);
		
		// Act
		Boolean result = this.userRepository.existsByEmail("inexistent.email@privder.com");
		
		// Assert		
		assertFalse(result);
	}
	

}

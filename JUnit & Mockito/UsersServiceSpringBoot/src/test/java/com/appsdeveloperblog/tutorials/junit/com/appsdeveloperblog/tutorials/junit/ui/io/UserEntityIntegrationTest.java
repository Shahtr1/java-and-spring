package com.appsdeveloperblog.tutorials.junit.com.appsdeveloperblog.tutorials.junit.ui.io;

import com.appsdeveloperblog.tutorials.junit.io.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.persistence.PersistenceException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
// Application Context with JPA-related components only
// By default, test method is Transactional and will rollback when completes
// By default, in-memory database is used
public class UserEntityIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    private UserEntity userEntity;

    @BeforeEach
    void setup(){
        userEntity = new UserEntity();
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setFirstName("Shahrukh");
        userEntity.setLastName("Tramboo");
        userEntity.setEmail("tramboos123@gmail.com");
        userEntity.setEncryptedPassword("shahrukh");
    }

    @Test
//    @DisplayName("")
    void testUserEntity_whenValidUserProvided_shouldReturnStoredUserDetails(){
//        Arrange

//        Act
        UserEntity storedUserEntity = testEntityManager.persistAndFlush(userEntity);

//        Assert
        assertTrue(storedUserEntity.getId() > 0);
        assertEquals(userEntity.getUserId(),storedUserEntity.getUserId());
        assertEquals(userEntity.getFirstName(),storedUserEntity.getFirstName());
        assertEquals(userEntity.getLastName(),storedUserEntity.getLastName());
        assertEquals(userEntity.getEncryptedPassword(),storedUserEntity.getEncryptedPassword());

    }

    @Test
    void testUserEntity_whenFirstNameIsTooLong_shouldThrowException(){
//        Arrange
        userEntity.setFirstName("qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwer" +
                "tyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwert" +
                "yuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm");


//        Assert & Act
        assertThrows(PersistenceException.class, ()->{
            testEntityManager.persistAndFlush(userEntity);

        },"Was expecting PersistenceException to be thrown");

    }

    @Test
    void testUserEntity_whenExistingUserIdProvided_shouldThrowException(){
//        Arrange
        UserEntity newEntity = new UserEntity();
        newEntity.setUserId("1");
        newEntity.setFirstName("Sharik");
        newEntity.setLastName("Tramboo");
        newEntity.setEmail("sharik@gmail.com");
        newEntity.setEncryptedPassword("shahrukh");

//        Act
        testEntityManager.persistAndFlush(newEntity);

        userEntity.setUserId("1");

//        Act & Assert
        assertThrows(PersistenceException.class, ()->{
            testEntityManager.persistAndFlush(userEntity);

        },"Was expecting PersistenceException to be thrown");

    }
}

package com.appsdeveloperblog.tutorials.junit.com.appsdeveloperblog.tutorials.junit.ui.io;

import com.appsdeveloperblog.tutorials.junit.io.UserEntity;
import com.appsdeveloperblog.tutorials.junit.io.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class UsersRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    UsersRepository usersRepository;

    private String userId1 = UUID.randomUUID().toString();
    private String userId2 = UUID.randomUUID().toString();
    private String email1 = "tramboos123@gmail.com";
    private String email2 = "sharik123@gmail.com";

    @BeforeEach
    void setup(){
//        Creating first user
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId1);
        userEntity.setFirstName("Shahrukh");
        userEntity.setLastName("Tramboo");
        userEntity.setEmail(email1);
        userEntity.setEncryptedPassword("shahrukh");
        testEntityManager.persistAndFlush(userEntity);

//        Creating second user
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUserId(userId2);
        userEntity2.setFirstName("Shahrukh");
        userEntity2.setLastName("Tramboo");
        userEntity2.setEmail(email2);
        userEntity2.setEncryptedPassword("shahrukh");
        testEntityManager.persistAndFlush(userEntity2);
    }

    @Test
    void testFindByEmail_whenGivenCorrectEmail_returnsUserEntity(){
//        Arrange


//        Act
        UserEntity storedUser =  usersRepository.findByEmail(email1);

//        Assert
        assertEquals(email1,storedUser.getEmail(),
                "Returned email address doesnt match expected value");

    }

    @Test
    void testFindByUserId_whenGivenCorrectUserId_returnsUserEntity(){
//        Act
        UserEntity storedUser =  usersRepository.findByUserId(userId2);

//        Assert
        assertNotNull(storedUser, "UserEntity object should not be null");
        assertEquals(userId2, storedUser.getUserId(),
                "Returned userId does not match expected value");
    }

    @Test
    void testFindUsersWithEmailEndingWith_whenGivenEmailDOmain_returnsUsersWithGivenDomain(){
//        Arrange
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setFirstName("Taruk");
        userEntity.setLastName("Tramboo");
        userEntity.setEmail("taruk@yahoo.in");
        userEntity.setEncryptedPassword("shahrukh");
        testEntityManager.persistAndFlush(userEntity);

        String emailDomainName = "@gmail.com";

//        Act
        List<UserEntity> userEntityList = usersRepository.findUsersWithEmailEndingWith(emailDomainName);


//        Assert
        assertEquals(2, userEntityList.size(),"There should be only two users in the list");

    }
}

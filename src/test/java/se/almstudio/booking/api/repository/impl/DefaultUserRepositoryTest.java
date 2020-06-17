package se.almstudio.booking.api.repository.impl;

import org.junit.Assert;
import se.almstudio.booking.api.model.entity.User;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DefaultUserRepositoryTest {

  @Test
  public void testCreateUserExpectNoneNullId() {
    DefaultUserRepository userRepository = new DefaultUserRepository();
    User user = new User();
    LocalDate date = LocalDate.of(1983, 4, 26);
    user.setFirstName("Herman");
    user.setLastName("Kiraly");
    user.setEmail("Herman@gmail.com");
    user.setBirthday(date);
    user.setRegistered(LocalDateTime.now());
    user.setUpdated(LocalDateTime.now());
    Long result = userRepository.create(user);
    Assert.assertNotNull(result);
    Assert.assertNotEquals(0L, result.longValue());
  }

  @Test
  public void testReadUserByIdExpectUser() {
    DefaultUserRepository userRepository = new DefaultUserRepository();
    User user = new User();
    LocalDate date = LocalDate.of(1983, 4, 26);
    user.setFirstName("Herman");
    user.setLastName("Kiraly");
    user.setBirthday(date);
    user.setEmail("Herman@gmail.com");
    user.setRegistered(LocalDateTime.now());
    user.setUpdated(LocalDateTime.now());
    Long result = userRepository.create(user);
    User userCreated = userRepository.findById(result);
    Assert.assertEquals(user.getFirstName(), userCreated.getFirstName());
    Assert.assertEquals(user.getLastName(), userCreated.getLastName());
    Assert.assertEquals(user.getBirthday(), userCreated.getBirthday());
    Assert.assertEquals(user.getEmail(), userCreated.getEmail());
  }

  @Test
  public void testUpdateUserExpectTrue() {
    DefaultUserRepository userRepository = new DefaultUserRepository();
    User user = new User();
    LocalDate date = LocalDate.of(1983, 4, 26);
    user.setFirstName("Herman");
    user.setLastName("Kiraly");
    user.setBirthday(date);
    user.setEmail("Herman@gmail.com");
    user.setRegistered(LocalDateTime.now());
    user.setUpdated(LocalDateTime.now());
    Long result = userRepository.create(user);
    LocalDate date1 = LocalDate.of(1983, 4, 27);
    user.setFirstName("Fariborz");
    user.setLastName("Markanovich");
    user.setBirthday(date1);
    user.setEmail("Markanovich@gmail.com");
    user.setId(result);
    boolean resultUpdated = userRepository.update(user);
    Assert.assertTrue(resultUpdated);
  }

  @Test
  public void testDeleteUserExpectTrue() {
    DefaultUserRepository userRepository = new DefaultUserRepository();
    User user = new User();
    LocalDate date = LocalDate.of(1983, 4, 26);
    user.setFirstName("Herman");
    user.setLastName("Kiraly");
    user.setBirthday(date);
    user.setEmail("Herman@gmail.com");
    user.setRegistered(LocalDateTime.now());
    user.setUpdated(LocalDateTime.now());
    Long result = userRepository.create(user);
    boolean resultDelete = userRepository.delete(result);
    Assert.assertTrue(resultDelete);
  }
}

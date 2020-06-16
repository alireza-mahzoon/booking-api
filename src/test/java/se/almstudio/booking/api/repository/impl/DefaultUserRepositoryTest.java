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
}

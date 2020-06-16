package se.almstudio.booking.api.repository;

import se.almstudio.booking.api.model.entity.User;

public interface UserRepository {

  /**
   * Create a new user
   *
   * @param user User information
   * @return Id for the persisted user
   */
  Long create(User user);

  /**
   * Find a user by its id
   *
   * @param userId User ID
   * @return User if found, {@code null} if not found
   */
  User findById(Long userId);

  /**
   * update user information
   *
   * @param user User to update
   * @return {@code true} if successful
   */
  boolean update(User user);

  /**
   * Delete user by its id
   *
   * @param userId User ID
   * @return {@code true} if deleted
   */
  boolean delete(Long userId);
}

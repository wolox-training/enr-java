package wolox.training.repositories;

import wolox.training.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /***
     * This method finds a user by username
     *
     * @param username is the parameter to find the user
     * @return an user object
     */
    Optional<User> findByUsername(String username);
}

package wolox.training.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import wolox.training.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /***
     * This method finds a user by username
     *
     * @param username is the parameter to find the user
     * @return an user object
     */
    Optional<User> findByUsername(String username);

    /***
     * This method find users by birthDate between 2 localDate and name
     * @param startDate start date
     * @param endDate end date
     * @param name user's name
     * @return a Users list
     */
    @Query("SELECT u FROM users u WHERE (u.birthDate BETWEEN :startDate AND :endDate) " +
            "AND (:name IS NULL OR lower(u.name) like lower(concat('%', :name,'%')))")
    List<User> findByBirthDateBetweenAndNameContainingIgnoreCase(LocalDate startDate, LocalDate endDate, String name);

    @Query("SELECT u FROM users u WHERE (:username IS NULL OR u.username = :username) AND" +
            "(:name IS NULL OR u.name = :name) AND " +
            ":birthDate IS NULL OR u.birthDate = :birthDate")
    List<User> findBy(String username, String name, LocalDate birthDate, Pageable paging);
}

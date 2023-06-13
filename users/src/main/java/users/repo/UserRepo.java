package users.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import users.model.Role;
import users.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {


    Optional<User> findByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> findByUserRole(@Param("role") Role role);

}

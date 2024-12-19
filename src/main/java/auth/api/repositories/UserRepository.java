package auth.api.repositories;

import auth.api.model.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface UserRepository extends JpaRepository<Users, Long> {
     Optional<Users> findByUsername(String username);
}

package iss.flowershop.persistance;

import iss.flowershop.model.Role;
import iss.flowershop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    User findByRole(Role role);
}

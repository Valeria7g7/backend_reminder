package com.valeria.backend.modules.auth.repository;
import java.util.UUID;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.valeria.backend.modules.user.model.User;
public interface AuthRepository extends JpaRepository<User,UUID> {
 Optional<User> findByEmail(String email);
}

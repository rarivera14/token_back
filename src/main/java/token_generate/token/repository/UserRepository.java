package token_generate.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import token_generate.token.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

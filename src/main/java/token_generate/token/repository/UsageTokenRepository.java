package token_generate.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import token_generate.token.models.UsageToken;

public interface UsageTokenRepository extends JpaRepository<UsageToken, Long> {
    @Query(value = "SELECT * FROM admi_token_usage WHERE user_id=:id_user AND token_id=:id_token", nativeQuery=true)
    UsageToken findHistoricalToken(@Param("id_user") Long id_user, @Param("id_token") Long id_token);
}

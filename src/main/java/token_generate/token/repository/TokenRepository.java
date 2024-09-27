package token_generate.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import token_generate.token.models.Token;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(value = "SELECT * FROM admi_token WHERE user_id=:id_user AND token=:token_user AND is_active = 1 LIMIT 1", nativeQuery=true)
    Token findUseToken(@Param("id_user") Long id_user, @Param("token_user") String token_user);

    @Query(value = "SELECT * FROM admi_token WHERE user_id=:id_user AND is_active = 1 ORDER BY fe_creacion ASC LIMIT 1", nativeQuery=true)
    Token findValidTokenByUserId(@Param("id_user") Long id_user);

    @Query(value = "SELECT * FROM admi_token WHERE user_id=:id_user", nativeQuery=true)
    List<Token> findAllTokensByUserId(@Param("id_user") Long id_user);
}

package token_generate.token.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import token_generate.token.utils.TokenConstants;

@Data
@Table(name = "admi_token", schema = "token_system")
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_token")
    private Long id;

    @Column(name = "token")
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = TokenConstants.TIMEZONE_DATE)
    @Column(name = "FE_CREACION")
    private Date feCreacion;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "is_active")
    private Long isActive;

}

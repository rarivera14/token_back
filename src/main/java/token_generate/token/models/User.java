package token_generate.token.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import token_generate.token.utils.TokenConstants;

import java.util.Date;


@Data
@Table(name = "ADMI_USER")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = TokenConstants.TIMEZONE_DATE)
    @Column(name = "FE_CREACION")
    private Date feCreacion;

    @Column(name = "USERNAME")
    private String username;
}

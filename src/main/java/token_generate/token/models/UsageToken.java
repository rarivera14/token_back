package token_generate.token.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import token_generate.token.utils.TokenConstants;

import java.util.Date;

@Data
@Table(name = "admi_token_usage", schema = "token_system")
@Entity
public class UsageToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usage_token")
    private Long idUsageToken;

    @Column(name = "token_id")
    private Long tokenId;

    @Column(name = "user_id")
    private Long userId;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = TokenConstants.TIMEZONE_DATE)
    @Column(name = "used_at")
    private Date usedAt;

}

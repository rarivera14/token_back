package token_generate.token.utils;

import lombok.Data;
import token_generate.token.models.Token;

@Data
public class TokenResponse {
    private Token token;
    private String message;
    private int errorCode;
    public TokenResponse(Token token) {
        this.token = token;
        this.message = "Token generado exitosamente";
        this.errorCode = 0;
    }

    public TokenResponse(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}


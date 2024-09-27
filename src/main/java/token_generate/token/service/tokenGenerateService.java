package token_generate.token.service;

import org.springframework.stereotype.Service;
import token_generate.token.models.Token;
import token_generate.token.models.User;
import token_generate.token.utils.GenericException;
import token_generate.token.utils.TokenResponse;

import java.util.List;

@Service
public interface tokenGenerateService {
    public List<Token> allTokens() throws GenericException;
    public Token saveToken(Token request) throws GenericException;
    public TokenResponse validateToken(Long user, String Token) throws GenericException;
    public Token findTokenByUser(Long user) throws GenericException;
    public List<Token> filterTokenActive(List<Token> request) throws GenericException;
    public List<Token> findAllTokensByUser(Long user) throws GenericException;
    public Token saveTokenByUser(User request) throws GenericException;
}

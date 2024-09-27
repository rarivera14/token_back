package token_generate.token.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import token_generate.token.models.Token;
import token_generate.token.models.User;
import token_generate.token.repository.TokenRepository;
import token_generate.token.repository.UsageTokenRepository;
import token_generate.token.service.tokenGenerateService;
import token_generate.token.utils.GenericException;
import token_generate.token.utils.TokenResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.security.SecureRandom;

@Service
public class tokenGenerateImpl implements tokenGenerateService {

    private final TokenRepository tokenRepository;
    private final UsageTokenRepository usageTokenRepository;
    private final SecureRandom random = new SecureRandom();

    @Autowired
    usageTokenImpl usageTokenImpl;

    public tokenGenerateImpl(TokenRepository tokenRepository, UsageTokenRepository usageTokenRepository) {
        this.tokenRepository = tokenRepository;
        this.usageTokenRepository = usageTokenRepository;
    }

    @Override
    public List<Token> allTokens() throws GenericException {
        List<Token> response;
        try {
            response = tokenRepository.findAll();
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), 500);
        }
        return response;
    }

    @Override
    public Token saveToken(Token request) throws GenericException {
        Token response;
        try {
            request.setFeCreacion(new Date());
            request.setIsActive(1L);
            int newToken = random.nextInt(900000) + 100000;
            String tokenString = String.valueOf(newToken);
            request.setToken(tokenString);
            response = tokenRepository.saveAndFlush(request);
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), 500);
        }
        return response;
    }

    @Override
    public TokenResponse validateToken(Long user, String token) throws GenericException {
        Date now = new Date();
        try{
            Token tokenFound = tokenRepository.findUseToken(user, token);

            if (tokenFound != null) {
                usageTokenImpl.updateUsageToken(user, tokenFound.getId());
                Date tokenCreationDate = tokenFound.getFeCreacion();
                long timeDifferenceInSeconds = (now.getTime() - tokenCreationDate.getTime()) / 1000;

                if (timeDifferenceInSeconds >= 60) {
                    tokenFound.setIsActive(0L);
                    tokenRepository.save(tokenFound);
                    return new TokenResponse("Token caducado", 401);
                }else{
                    return new TokenResponse(tokenFound);
                }
            }else{
                return new TokenResponse("Token no encontrado", 404);
            }

        } catch (Exception e) {
            throw new GenericException(e.getMessage(), 500);
        }
    }

    @Override
    public Token findTokenByUser(Long id_user) throws GenericException {
        Token response;
        Date now = new Date();
        try{
            response = tokenRepository.findValidTokenByUserId(id_user);

            if (response != null) {
                Date tokenCreationDate = response.getFeCreacion();
                long timeDifferenceInSeconds = (now.getTime() - tokenCreationDate.getTime()) / 1000;

                if (timeDifferenceInSeconds >= 60) {
                    response.setIsActive(0L);
                    tokenRepository.save(response);
                    return null;
                }
            }
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), 500);
        }

        return response;
    }

    @Override
    public List<Token> filterTokenActive(List<Token> request) throws GenericException {
        List<Token> validTokens = new ArrayList<>();
        Date now = new Date();

        try {
            for (Token token : request) {
                Date tokenCreationDate = token.getFeCreacion();
                long timeDifferenceInSeconds = (now.getTime() - tokenCreationDate.getTime()) / 1000;

                if (timeDifferenceInSeconds <= 60) {
                    validTokens.add(token);
                }else{
                    token.setIsActive(0L);
                    tokenRepository.save(token);
                }

            }
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), 500);
        }

        return validTokens;
    }

    @Override
    public List<Token> findAllTokensByUser(Long user) throws GenericException {
        List<Token> response;
        try {
            response = tokenRepository.findAllTokensByUserId(user);
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), 500);
        }
        return response;
    }

    @Override
    public Token saveTokenByUser(User request) throws GenericException {
        Token response;
        try {
            Token newToken = new Token();
            newToken.setUserId(request.getId());
            newToken.setIsActive(1L);
            newToken.setFeCreacion(new Date());
            int tokenGenerate = random.nextInt(900000) + 100000;
            String tokenString = String.valueOf(tokenGenerate);
            newToken.setToken(tokenString);
            response = tokenRepository.saveAndFlush(newToken);
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), 500);
        }
        return response;
    }
}

package token_generate.token.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import token_generate.token.models.Token;
import token_generate.token.service.tokenGenerateService;
import token_generate.token.utils.GenericException;
import token_generate.token.utils.TokenResponse;

import java.util.List;

@RestController
@RequestMapping("/api")
public class tokenController {

    @Autowired
    tokenGenerateService tokenGenerateService;

    @GetMapping(path = "/allTokens")
    public List<Token> generateToken() throws GenericException {
        return tokenGenerateService.allTokens();
    }

    @PostMapping("/new-token")
    public Token saveToken(@RequestBody Token request) throws GenericException {
        Token response;
        try {
            response = tokenGenerateService.saveToken(request);
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), 500);
        }
        return response;
    }

    @GetMapping(path = "/usarToken/{cliente}/{token}")
    public TokenResponse validateAuthentication(@PathVariable Long cliente, @PathVariable String token) throws GenericException {
        TokenResponse response;
        try {
            response = tokenGenerateService.validateToken(cliente, token);
            System.out.println(response);
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), 500);
        }
        return response;
    }

    @GetMapping(path = "/findValidTokenByUser/{id_user}")
    public Token findTokenByIdUser(@PathVariable Long id_user) throws GenericException {
        Token response;

        try {
            response = tokenGenerateService.findTokenByUser(id_user);
            System.out.println(response);
            //validTokens = tokenGenerateService.filterTokenActive(response);

        } catch (Exception e) {
            throw new GenericException(e.getMessage(), 500);
        }

        System.out.println(response);
        return response;
    }

    @GetMapping(path = "/findAllTokensByUser/{id_user}")
    public List<Token> findAllTokensByUser(@PathVariable Long id_user) throws GenericException {
        List<Token> response;

        try {
            response = tokenGenerateService.findAllTokensByUser(id_user);
            System.out.println(response);
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), 500);
        }

        return response;
    }


}

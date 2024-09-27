package token_generate.token.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import token_generate.token.models.Token;
import token_generate.token.models.User;
import token_generate.token.service.tokenGenerateService;
import token_generate.token.service.userService;
import token_generate.token.utils.GenericException;
import token_generate.token.utils.TokenResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class userController {
    @Autowired
    userService userService;
    @Autowired
    tokenGenerateService tokenGenerateService;

    @GetMapping(path = "/generarToken/{id_user}")
    public TokenResponse generateTokenByUser(@PathVariable Long id_user) throws GenericException {
        try {
            Optional<User> response = userService.findUserById(id_user);
            if (response.isPresent()) {
                User userFound = response.get();
                Token tokensUser = tokenGenerateService.findTokenByUser(userFound.getId());
                if (tokensUser != null) {
                    return new TokenResponse(tokensUser);
                }else{
                    Token tokenGenerate = tokenGenerateService.saveTokenByUser(userFound);
                    return new TokenResponse(tokenGenerate);
                }
            }else{
                return new TokenResponse("Usuario no encontrado", 404);
            }

        } catch (Exception e) {
            throw new GenericException(e.getMessage(), 500);
        }
    }

    @PostMapping("/new-user")
    public User saveUser(@RequestBody User request) throws GenericException {
        User response;
        try {
            response = userService.saveUser(request);
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), 500);
        }
        return response;
    }
}

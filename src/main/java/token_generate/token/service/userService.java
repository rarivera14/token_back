package token_generate.token.service;

import org.springframework.stereotype.Service;
import token_generate.token.models.Token;
import token_generate.token.models.User;
import token_generate.token.utils.GenericException;

import java.util.List;
import java.util.Optional;

@Service
public interface userService {
    public List<User> allUsers() throws GenericException;
    public Optional<User> findUserById(Long idUser) throws GenericException;
    public User saveUser(User request) throws GenericException;
}

package token_generate.token.service.impl;

import org.springframework.stereotype.Service;
import token_generate.token.models.Token;
import token_generate.token.models.User;
import token_generate.token.repository.UserRepository;
import token_generate.token.service.userService;
import token_generate.token.utils.GenericException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class userServiceImpl implements userService {
    private final UserRepository userRepository;

    public userServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> allUsers() throws GenericException {
        List<User> response = new ArrayList<User>();
        try {
            response = userRepository.findAll();
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), 500);
        }

        return response;
    }

    @Override
    public Optional<User> findUserById(Long idUser) throws GenericException {
        Optional<User> response;
        try{
            response = userRepository.findById(idUser);
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), 500);
        }
        return response;
    }

    @Override
    public User saveUser(User request) throws GenericException {
        User response;
        try {
            request.setFeCreacion(new Date());
            response = userRepository.saveAndFlush(request);
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), 500);
        }
        return response;
    }
}

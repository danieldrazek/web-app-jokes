package pl.pp.spring.jokeswebapp.services.db;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.pp.spring.jokeswebapp.model.User;
import pl.pp.spring.jokeswebapp.repositories.UserRepository;
import pl.pp.spring.jokeswebapp.services.UserService;

import java.util.List;

@Service
@Primary
public class UserDbService implements UserService {

    private final UserRepository userRepository;

    public UserDbService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User save(User user) {
        System.out.println("user db service");
        return userRepository.save(user);
    }
}

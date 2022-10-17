package pl.pp.spring.jokeswebapp.services.map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.pp.spring.jokeswebapp.model.User;
import pl.pp.spring.jokeswebapp.services.UserService;

@Service
public class UserMapService extends BaseMapService<User> implements UserService {

    @Override
    public User save(User entity) {
        System.out.println("user map service");
        return super.save(entity);
    }
}

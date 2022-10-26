package pl.pp.spring.jokeswebapp.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.pp.spring.jokeswebapp.model.User;
import pl.pp.spring.jokeswebapp.services.UserService;

@Service
@Profile("map")
public class UserMapService extends BaseMapService<User> implements UserService {

    @Override
    public User save(User entity) {
        System.out.println("user map service");
        return super.save(entity);
    }
}

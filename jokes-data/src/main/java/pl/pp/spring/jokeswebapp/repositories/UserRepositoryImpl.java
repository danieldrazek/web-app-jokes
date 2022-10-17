package pl.pp.spring.jokeswebapp.repositories;

import org.springframework.stereotype.Repository;
import pl.pp.spring.jokeswebapp.model.User;

@Repository
class UserRepositoryImpl implements UserRepository{
    @Override
    public User save(User user) {
        System.out.println("Simulation of implementation to user table");
        return null;
    }
}

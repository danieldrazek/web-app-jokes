package pl.pp.spring.jokeswebapp.repositories;

import pl.pp.spring.jokeswebapp.model.User;

public interface UserRepository {

    User save(User user);
}

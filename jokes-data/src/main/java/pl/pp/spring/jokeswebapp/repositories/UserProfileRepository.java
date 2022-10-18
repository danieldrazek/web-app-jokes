package pl.pp.spring.jokeswebapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.pp.spring.jokeswebapp.model.UserProfile;

public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {

}

package pl.pp.spring.jokeswebapp.services.map;

import org.springframework.stereotype.Service;
import pl.pp.spring.jokeswebapp.model.UserProfile;
import pl.pp.spring.jokeswebapp.services.UserProfileService;

@Service
public class UserProfileMapService extends BaseMapService<UserProfile> implements UserProfileService {

}

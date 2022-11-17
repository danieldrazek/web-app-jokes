package pl.pp.spring.jokeswebapp.services.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.pp.spring.jokeswebapp.model.UserProfile;
import pl.pp.spring.jokeswebapp.repositories.UserProfileRepository;
import pl.pp.spring.jokeswebapp.services.UserProfileService;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
@Profile("db")
public class UserProfileDbService implements UserProfileService {
    private Logger log = LoggerFactory.getLogger(UserProfileDbService.class);

    private final UserProfileRepository userProfileRepository;

    public UserProfileDbService(UserProfileRepository userRepository) {
        this.userProfileRepository = userRepository;
    }

    @Override
    public List<UserProfile> findAll() {
        log.info("finding all");
        List<UserProfile> userProfiles = new ArrayList<>();
        userProfileRepository.findAll().forEach(userProfiles::add);
        return userProfiles;
    }

    @Override
    public UserProfile findById(Long id) {
        log.info("finding by id: {}", id);
        return userProfileRepository.findById(id).orElse(null);
    }

    @Override
    public UserProfile save(UserProfile userProfile) {
        log.info("saving userProfile with first name: {}", userProfile.getFirstName());
        return userProfileRepository.save(userProfile);
    }

    @Override
    public void deleteById(Long id) {
        log.info("deleting user profile by id: {}", id);
        userProfileRepository.deleteById(id);
    }
}

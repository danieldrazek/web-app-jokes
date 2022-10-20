package pl.pp.spring.jokeswebapp.services.db;

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

    private final UserProfileRepository userProfileRepository;

    public UserProfileDbService(UserProfileRepository userRepository) {
        this.userProfileRepository = userRepository;
    }

    @Override
    public List<UserProfile> findAll() {
        List<UserProfile> userProfiles = new ArrayList<>();
        userProfileRepository.findAll().forEach(userProfiles::add);
        return userProfiles;
    }

    @Override
    public UserProfile findById(Long id) {
        return userProfileRepository.findById(id).orElse(null);
    }

    @Override
    public UserProfile save(UserProfile userProfile) {
        System.out.println("userProfile db service");
        return userProfileRepository.save(userProfile);
    }
}

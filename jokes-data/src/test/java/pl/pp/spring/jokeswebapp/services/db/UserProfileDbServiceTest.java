package pl.pp.spring.jokeswebapp.services.db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pp.spring.jokeswebapp.model.UserProfile;
import pl.pp.spring.jokeswebapp.repositories.UserProfileRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserProfileDbServiceTest {

    @Mock
    private UserProfileRepository userProfileRepository;

    @InjectMocks
    private UserProfileDbService userProfileDbService;

    private final UserProfile userProfile = new UserProfile();

    @Test
    void findAllShouldReturnEmptyList() {
        when(userProfileRepository.findAll()).thenReturn(new HashSet<>());

        List<UserProfile> userProfiles = userProfileDbService.findAll();

        assertEquals(0, userProfiles.size());
    }

    @Test
    void findAllShouldReturnListWithOneElement() {
        when(userProfileRepository.findAll()).thenReturn(Set.of(userProfile));

        List<UserProfile> userProfiles = userProfileDbService.findAll();

        assertEquals(1, userProfiles.size());
    }

    @Test
    void findByNotExistId() {
        when(userProfileRepository.findById(anyLong())).thenReturn(Optional.empty());

        UserProfile userProfile = userProfileDbService.findById(1L);

        assertNull(userProfile);
    }

    @Test
    void findByExistId() {
        when(userProfileRepository.findById(anyLong())).thenReturn(Optional.of(userProfile));

        UserProfile userProfile = userProfileDbService.findById(1L);

        assertNotNull(userProfile);
    }


    @Test
    void save() {
        userProfile.setFirstName("Daniel");

        userProfileDbService.save(userProfile);

        verify(userProfileRepository).save(any(UserProfile.class));
    }
}
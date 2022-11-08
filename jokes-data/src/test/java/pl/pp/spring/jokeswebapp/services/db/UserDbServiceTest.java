package pl.pp.spring.jokeswebapp.services.db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pp.spring.jokeswebapp.model.User;
import pl.pp.spring.jokeswebapp.repositories.UserRepository;

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
class UserDbServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDbService userDbService;

    private final User user = new User("Jan Kowalski");

    @Test
    void findAllShouldReturnEmptyList() {
        when(userRepository.findAll()).thenReturn(new HashSet<>());

        List<User> users = userDbService.findAll();

        assertEquals(0, users.size());
    }

    @Test
    void findAllShouldReturnListWithOneElement() {
        when(userRepository.findAll()).thenReturn(Set.of(user));

        List<User> users = userDbService.findAll();

        assertEquals(1, users.size());
    }

    @Test
    void findByNotExistId() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        User user = userDbService.findById(1L);

        assertNull(user);
    }

    @Test
    void findByExistId() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        User user = userDbService.findById(1L);

        assertNotNull(user);
    }

    @Test
    void save() {
        userDbService.save(user);

        verify(userRepository).save(any(User.class));
    }
}
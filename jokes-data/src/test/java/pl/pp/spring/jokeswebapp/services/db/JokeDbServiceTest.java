package pl.pp.spring.jokeswebapp.services.db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pp.spring.jokeswebapp.model.Joke;
import pl.pp.spring.jokeswebapp.repositories.JokeRepository;

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
class JokeDbServiceTest {

    @Mock
    private JokeRepository jokeRepository;
    @InjectMocks
    private JokeDbService jokeDbService;

    private final Joke joke = new Joke("joke test", "content");


    @Test
    void findAllShouldReturnEmptyList() {
        when(jokeRepository.findAll()).thenReturn(new HashSet<>());

        List<Joke> jokes = jokeDbService.findAll();

        assertEquals(0, jokes.size());
    }

    @Test
    void findAllShouldReturnListWithOneElement() {
        when(jokeRepository.findAll()).thenReturn(Set.of(joke));

        List<Joke> jokes = jokeDbService.findAll();

        assertEquals(1, jokes.size());
    }

    @Test
    void findByNotExistId() {
        when(jokeRepository.findById(anyLong())).thenReturn(Optional.empty());

        Joke joke = jokeDbService.findById(1L);

        assertNull(joke);
    }

    @Test
    void findByExistId() {
        when(jokeRepository.findById(anyLong())).thenReturn(Optional.of(joke));

        Joke joke = jokeDbService.findById(1L);

        assertNotNull(joke);
    }

    @Test
    void save() {

        jokeDbService.save(joke);

        verify(jokeRepository).save(any(Joke.class));
    }
}
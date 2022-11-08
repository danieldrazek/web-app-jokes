package pl.pp.spring.jokeswebapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.pp.spring.jokeswebapp.model.Category;
import pl.pp.spring.jokeswebapp.model.Joke;
import pl.pp.spring.jokeswebapp.services.CategoryService;
import pl.pp.spring.jokeswebapp.services.JokeService;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class JokeControllerTest {

    @Mock
    private JokeService jokeService;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private JokeController jokeController;

    private MockMvc mockMvc;

    private final Category category = new Category("test category");

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(jokeController).build();

        category.getJokes().add(new Joke("title", "content"));
        category.getJokes().add(new Joke("title 2", "content 2"));
    }

    @Test
    void showJokesForNotExistCategory() throws Exception {
        mockMvc.perform(get("/jokes?categoryId=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("jokes", hasSize(0)));

        verify(categoryService).findById(anyLong());
        verify(categoryService).findAll();
    }

    @Test
    void showJokesForExistCategory() throws Exception {
        when(categoryService.findById(anyLong())).thenReturn(category);

        mockMvc.perform(get("/jokes?categoryId=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("jokes", hasSize(2)));

        verify(categoryService).findById(anyLong());
        verify(categoryService).findAll();
    }

    @Test
    void addJokeForm() throws Exception {
        mockMvc.perform(get("/jokes/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("jokes/add"));

        verify(categoryService).findAll();
    }

    @Test
    void addJoke() throws Exception {
        when(categoryService.findById(anyLong())).thenReturn(category);

        mockMvc.perform(post("/jokes/add?category=1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(categoryService).findById(anyLong());
        verify(categoryService).save(any(Category.class));
        verify(jokeService).save(any(Joke.class));
    }
}
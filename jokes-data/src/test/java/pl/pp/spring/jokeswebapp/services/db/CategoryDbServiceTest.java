package pl.pp.spring.jokeswebapp.services.db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pp.spring.jokeswebapp.model.Category;
import pl.pp.spring.jokeswebapp.repositories.CategoryRepository;

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
class CategoryDbServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryDbService categoryDbService;

    private final Category category = new Category("test category");

    @Test
    void findAllShouldReturnEmptyList() {
        when(categoryRepository.findAll()).thenReturn(new HashSet<>());

        List<Category> categories = categoryDbService.findAll();

        assertEquals(0, categories.size());
    }

    @Test
    void findAllShouldReturnListWithOneElement() {
        when(categoryRepository.findAll()).thenReturn(Set.of(category));

        List<Category> categories = categoryDbService.findAll();

        assertEquals(1, categories.size());
    }

    @Test
    void findByNotExistId() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        Category category = categoryDbService.findById(1L);

        assertNull(category);
    }

    @Test
    void findByExistId() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));

        Category category = categoryDbService.findById(1L);

        assertNotNull(category);
    }

    @Test
    void save() {

        categoryDbService.save(category);

        verify(categoryRepository).save(any(Category.class));
    }
}
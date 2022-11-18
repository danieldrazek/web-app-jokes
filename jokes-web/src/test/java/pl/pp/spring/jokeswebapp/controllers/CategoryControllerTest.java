package pl.pp.spring.jokeswebapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.pp.spring.jokeswebapp.exceptions.NotFoundException;
import pl.pp.spring.jokeswebapp.model.Category;
import pl.pp.spring.jokeswebapp.services.CategoryService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    private MockMvc mockMvc;

    private Category category1 = new Category("category 1");
    private List<Category> categories;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();

        categories = new ArrayList<>();
        categories.add(category1);
        categories.add(new Category("category 2"));
    }

    @Test
    void showCategoriesList() throws Exception {
        when(categoryService.findAll()).thenReturn(categories);

        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("categories", hasSize(2)))
                .andExpect(view().name("categories/list"));

        verify(categoryService).findAll();
    }

    @Test
    void showAddCategoryForm() throws Exception {
        mockMvc.perform(get("/categories/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("category"))
                .andExpect(view().name("categories/save"));
    }

    @Test
    void showEditCategoryForm() throws Exception {
        when(categoryService.findById(anyLong())).thenReturn(category1);

        mockMvc.perform(get("/categories/1/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("category"))
                .andExpect(view().name("categories/save"));

        verify(categoryService).findById(1L);
    }

    @Test
    void processCategoryForm() throws Exception {
        mockMvc.perform(post("/categories/save"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/categories"));

        verify(categoryService).save(any(Category.class));
    }

    @Test
    void deleteExistCategory() throws Exception {
        mockMvc.perform(get("/categories/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/categories"));

        verify(categoryService).deleteById(1L);
    }

    @Test
    void deleteNotExistCategory() throws Exception {
        doThrow(NotFoundException.class).when(categoryService).deleteById(anyLong());

        mockMvc.perform(get("/categories/1/delete"))
                .andExpect(status().isNotFound());

        verify(categoryService).deleteById(1L);
    }
}
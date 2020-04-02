package com.movieservice.movieservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieservice.movieservice.entities.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MovieControllerTest {
    final String url = "/api/movies";
    String title = "Cinderella";
    String year = "2015";
    String rated = "PG";
    LocalDate released = LocalDate.parse("2015-03-15");
    String director = "Kenneth Branagh";
    String actors = "Cate Blanchett, Lily James, Richard Madden, Helena Bonham Carter";
    String imdbID = "tt1661199";

    Movie movie;
    @Autowired
    MockMvc mvc;

//    @MockBean
//    MovieService service;

    @BeforeEach
    void setup() {
        movie = new Movie(imdbID, actors, director, title, year, released);
    }
    @Autowired
    ObjectMapper mapper;
//    POST: add a movie to the database
    @Test
    void addMovie() throws Exception {
        movie.setId(1L);
        String json = mapper.writeValueAsString(movie);
        mvc.perform(post(url).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(movie.getId()))
                .andExpect(jsonPath("$.imdbId").value(movie.getImdbId()))
                .andExpect(jsonPath("$.title").value(movie.getTitle()))
                .andExpect(jsonPath("$.director").value(movie.getDirector()))
                .andExpect(jsonPath("$.actors").value(movie.getActors()))
                .andExpect(jsonPath("$.year").value(movie.getYear()))
                .andExpect(jsonPath("$.released").value(movie.getReleased()));
    }
//    GET: all movies in the database
    @Test
    void getAllMovies() throws Exception {
        movie.setId(1L);
        String json = mapper.writeValueAsString(movie);
        mvc.perform(post(url).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(movie.getId()))
                .andExpect(jsonPath("$.imdbId").value(movie.getImdbId()))
                .andExpect(jsonPath("$.title").value(movie.getTitle()))
                .andExpect(jsonPath("$.director").value(movie.getDirector()))
                .andExpect(jsonPath("$.actors").value(movie.getActors()))
                .andExpect(jsonPath("$.year").value(movie.getYear()))
                .andExpect(jsonPath("$.released").value(movie.getReleased()));
    }
//    GET: one movie by imdbid
//    GET: all movies by Title
//    GET: Search for movies by actor (optional), director (optional), genre (optional), title containing a search string (required)
//    PATCH: add or update a star rating for a movie (1 - 5)
//    DELETE
}
package com.movieservice.movieservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieservice.movieservice.entities.Movie;
import com.movieservice.movieservice.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @MockBean
    MovieService service;

    @BeforeEach
    void setup() {
        movie = new Movie(imdbID, actors, director, title, year, released);
    }
    @Autowired
    ObjectMapper mapper;
//    POST: add a movie to the database
    @Test
    void createMovie() throws Exception {
        movie.setId(1L);
        String json = mapper.writeValueAsString(movie);
        when(service.createMovie(ArgumentMatchers.any(Movie.class))).thenReturn(json);
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
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(movie);
        when(service.getAllMovies()).thenReturn(movies);
        mvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(movies.size())))
                .andExpect(jsonPath("$[0].title").value(movie.getTitle()))
                .andExpect(jsonPath("$[0].imdbId").value(movie.getImdbId()));
    }
//    GET: one movie by imdbid
    @Test
    void getMovieByImdbId() throws Exception {
        movie.setId(1L);
        String json = mapper.writeValueAsString(movie);
        when(service.getMovieByImdbId(movie.getImdbId())).thenReturn(json);
        mvc.perform(get(url)
                .param("imdbId", movie.getImdbId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(movie.getTitle()))
                .andExpect(jsonPath("$.imdbId").value(movie.getImdbId()));
    }
//    GET: all movies by Title
    @Test
    void getMoviesByTitle() throws Exception {
        movie.setId(1L);
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(movie);
        when(service.getMoviesByTitle(movie.getTitle())).thenReturn(movies);
        mvc.perform(get(url)
                .param("title", movie.getTitle())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(movies.size())))
                .andExpect(jsonPath("$[0].title").value(movie.getTitle()))
                .andExpect(jsonPath("$[0].imdbId").value(movie.getImdbId()));
    }
//    GET: Search for movies by actor (optional), director (optional), genre (optional), title containing a search string (required)
    @Test
    void getMoviesByActor() throws Exception {
        movie.setId(1L);
        String actorQuery = "Cate Blanchett";
        String json = mapper.writeValueAsString(movie);
        when(service.getMoviesByActor(movie.getTitle())).thenReturn(json);
        mvc.perform(get(url)
                .param("actor", actorQuery)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(movies.size())))
                .andExpect(jsonPath("$[0].title").value(movie.getTitle()))
                .andExpect(jsonPath("$[0].imdbId").value(movie.getImdbId()));
    }
//    PATCH: add or update a star rating for a movie (1 - 5)
    @Test
    void updateMovieRating() throws Exception {
        movie.setId(1L);
        movie.setRating("5");
        String json = mapper.writeValueAsString(movie);
        when(service.updateMovieRating(movie.getId())).thenReturn(movie);
        mvc.perform(patch(url + movie.getId())
                .param("rating", "5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(movies.size())))
                .andExpect(jsonPath("$[0].title").value(movie.getTitle()))
                .andExpect(jsonPath("$[0].imdbId").value(movie.getImdbId()));
    }
//    DELETE
    @Test
    void deleteMovieById() throws Exception {
        movie.setId(1L);
        String json = mapper.writeValueAsString(movie);
        when(service.delete(ArgumentMatchers.any(Long.class))).thenReturn(true);
        mvc.perform(patch(url + movie.getId())
                .param("rating", "5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(movies.size())))
                .andExpect(jsonPath("$[0].title").value(movie.getTitle()))
                .andExpect(jsonPath("$[0].imdbId").value(movie.getImdbId()));
        verify(service).delete(1L);
    }
}
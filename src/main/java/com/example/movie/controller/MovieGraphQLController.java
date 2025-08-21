package com.example.movie.controller;

import com.example.movie.dto.MovieInput;
import com.example.movie.dto.MovieResponse;
import com.example.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieGraphQLController {
    private final MovieService movieService;

    @QueryMapping
    public List<MovieResponse> allMovies() {
        return movieService.getAllMovies();
    }

    @QueryMapping
    public MovieResponse movieById(@Argument Long id) {
        return movieService.getMovieById(id);
    }

    @QueryMapping
    public List<MovieResponse> moviesByGenre(@Argument String genre) {
        return movieService.getMoviesByGenre(genre);
    }

    @MutationMapping
    public MovieResponse createMovie(@Argument MovieInput movieInput) {
        return movieService.createMovie(movieInput);
    }
}
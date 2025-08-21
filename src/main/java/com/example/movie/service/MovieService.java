package com.example.movie.service;

import com.example.movie.dto.MovieInput;
import com.example.movie.dto.MovieResponse;
import com.example.movie.dto.GenreResponse;
import com.example.movie.exception.NotFoundException;
import com.example.movie.model.Genre;
import com.example.movie.model.Movie;
import com.example.movie.repository.GenreRepository;
import com.example.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    @Transactional
    public MovieResponse createMovie(MovieInput movieInput) {
        List<Genre> genres = genreRepository.findAllById(movieInput.getGenreIds());

        if (genres.size() != movieInput.getGenreIds().size()) {
            throw new NotFoundException("One or more genres not found");
        }

        Movie movie = Movie.builder()
                .title(movieInput.getTitle())
                .directors(movieInput.getDirectors())
                .actors(movieInput.getActors())
                .genres(genres)
                .build();

        movie = movieRepository.save(movie);
        return mapToMovieResponse(movie);
    }

    @Transactional(readOnly = true)
    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(this::mapToMovieResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MovieResponse getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Movie not found with id: " + id));
        return mapToMovieResponse(movie);
    }

    @Transactional(readOnly = true)
    public List<MovieResponse> getMoviesByGenre(String genreName) {
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getGenres().stream()
                        .anyMatch(genre -> genre.getName().equalsIgnoreCase(genreName)))
                .map(this::mapToMovieResponse)
                .collect(Collectors.toList());
    }

    private MovieResponse mapToMovieResponse(Movie movie) {
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .directors(movie.getDirectors())
                .actors(movie.getActors())
                .genres(movie.getGenres().stream()
                        .map(genre -> GenreResponse.builder()
                                .id(genre.getId())
                                .name(genre.getName())
                                .description(genre.getDescription())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}

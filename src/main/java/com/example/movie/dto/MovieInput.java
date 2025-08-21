package com.example.movie.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieInput {
    private String title;
    private List<String> directors;
    private List<String> actors;
    private List<Long> genreIds;
}

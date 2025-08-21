CREATE TABLE genre (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT
);

CREATE TABLE movie (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL
);

CREATE TABLE movie_directors (
    movie_id BIGINT NOT NULL,
    director_name VARCHAR(255),
    FOREIGN KEY (movie_id) REFERENCES movie(id)
);

CREATE TABLE movie_actors (
    movie_id BIGINT NOT NULL,
    actor_name VARCHAR(255),
    FOREIGN KEY (movie_id) REFERENCES movie(id)
);

CREATE TABLE movie_genre (
    movie_id BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    PRIMARY KEY (movie_id, genre_id),
    FOREIGN KEY (movie_id) REFERENCES movie(id),
    FOREIGN KEY (genre_id) REFERENCES genre(id)
);
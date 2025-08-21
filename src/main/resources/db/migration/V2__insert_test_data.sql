-- Insert genres
INSERT INTO genre (name, description) VALUES
('Action', 'Action-packed movies with fights and explosions'),
('Comedy', 'Funny movies to make you laugh'),
('Drama', 'Serious plot-driven movies'),
('Sci-Fi', 'Science fiction movies with futuristic elements'),
('Horror', 'Scary movies to frighten you');

-- Insert movies
INSERT INTO movie (title) VALUES
('The Matrix'),
('Inception'),
('The Shawshank Redemption'),
('The Hangover');

-- Insert directors
INSERT INTO movie_directors (movie_id, director_name) VALUES
(1, 'Lana Wachowski'),
(1, 'Lilly Wachowski'),
(2, 'Christopher Nolan'),
(3, 'Frank Darabont'),
(4, 'Todd Phillips');

-- Insert actors
INSERT INTO movie_actors (movie_id, actor_name) VALUES
(1, 'Keanu Reeves'),
(1, 'Laurence Fishburne'),
(2, 'Leonardo DiCaprio'),
(2, 'Joseph Gordon-Levitt'),
(3, 'Tim Robbins'),
(3, 'Morgan Freeman'),
(4, 'Bradley Cooper'),
(4, 'Zach Galifianakis');

-- Insert movie-genre relationships
INSERT INTO movie_genre (movie_id, genre_id) VALUES
(1, 1), (1, 4), -- The Matrix: Action, Sci-Fi
(2, 1), (2, 4), -- Inception: Action, Sci-Fi
(3, 3),         -- Shawshank: Drama
(4, 2);          -- Hangover: Comedy
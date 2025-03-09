package com.example.movieinfoservice.resources;

import com.example.movieinfoservice.Entity.Movie;
import com.example.movieinfoservice.Repository.MovieRepository;
import com.example.movieinfoservice.models.MovieModel;
import com.example.movieinfoservice.models.MovieSummary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;


    private final RestTemplate restTemplate;
    private final MovieRepository movieRepository;

    public MovieResource(RestTemplate restTemplate, MovieRepository movieRepository) {
        this.restTemplate = restTemplate;
        this.movieRepository = movieRepository;
    }

    @RequestMapping("/{movieId}")
    public MovieModel getMovieInfo(@PathVariable("movieId") String movieId) {

        Movie cachedMovie = movieRepository.findById(movieId).orElse(null);
        if (cachedMovie != null) {
            System.out.println("Used the cached movie");
            return MovieModel
                .builder()
                .movieId(cachedMovie.getMovieId())
                .name(cachedMovie.getName())
                .description(cachedMovie.getDescription())
                .build();
        }

        // Get the movie info from TMDB
        final String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
        MovieSummary movieSummary = restTemplate.getForObject(url, MovieSummary.class);

        if (movieSummary != null) {
            MovieModel movieModel = MovieModel.builder()
                .movieId(movieId)
                .name(movieSummary.getTitle())
                .description(movieSummary.getOverview())
                .build();

            Movie movie = Movie.builder()
                .movieId(movieId)
                .name(movieSummary.getTitle())
                .description(movieSummary.getOverview())
                .build();

            movieRepository.save(movie);

            return movieModel;
        }

        return null;
    }
}

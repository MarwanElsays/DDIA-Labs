package com.example.movieinfoservice.resources;

import com.example.movieinfoservice.Entity.Movie;
import com.example.movieinfoservice.Repository.MovieRepository;
import com.example.movieinfoservice.models.MovieModel;
import com.example.movieinfoservice.models.MovieSummary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    private static final String DATA_FILE = "C:\\Users\\MARWAN\\Desktop\\CSED\\Computer Engineering Year 4\\2nd Semester\\DDIA\\Labs\\Lab2\\fetch-Ids\\movie_data.csv";
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
        final String url = "https://api.themoviedb.org/3/movie/" + 12 + "?api_key=" + apiKey;
        restTemplate.getForObject(url, MovieSummary.class);

        MovieModel movieModel = createDummyMovie(movieId);
        if (movieModel != null) {
            Movie movie = Movie.builder()
                    .movieId(movieId)
                    .name(movieModel.getName())
                    .description(movieModel.getDescription())
                    .build();
            movieRepository.save(movie);
            return movieModel;
        }

        return null;
    }

    @GetMapping("/getTop10MoviesInfo")
    public List<MovieModel> getTop10MoviesInfo(@RequestParam List<String> movieIds) {

        List<MovieModel> movieModels = new ArrayList<>();
        for (String movieId : movieIds) {
            Movie cachedMovie = movieRepository.findById(movieId).orElse(null);
            if (cachedMovie != null) {
                movieModels.add(MovieModel
                        .builder()
                        .movieId(cachedMovie.getMovieId())
                        .name(cachedMovie.getName())
                        .description(cachedMovie.getDescription())
                        .build());
            }else{
//                final String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
//                MovieSummary movieSummary = restTemplate.getForObject(url, MovieSummary.class);
                MovieModel movieModel = createDummyMovie(movieId);
                if (movieModel != null) {
                    movieModels.add(MovieModel.builder()
                            .movieId(movieId)
                            .name(movieModel.getName())
                            .description(movieModel.getDescription())
                            .build());
                }
            }
        }
        return movieModels;
    }

    public MovieModel createDummyMovie(String movieId) {
        return MovieModel.builder()
                .movieId(movieId)
                .name("Dummy Movie")
                .description("Dark is a German science fiction thriller streaming television series co-created by Baran bo Odar and Jantje Friese")
                .build();
    }
}

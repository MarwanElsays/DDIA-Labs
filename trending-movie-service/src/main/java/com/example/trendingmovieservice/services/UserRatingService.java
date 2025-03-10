package com.example.trendingmovieservice.services;

import com.example.trendingmovieservice.models.MovieModel;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.proto.Schema.MovieInfo;
import org.example.proto.Schema.Top10RatingsResponse;
import org.example.proto.Schema.Top10RatingsRequest;
import org.example.proto.TrendingMovieServiceGrpc;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;

@GrpcService
public class UserRatingService extends TrendingMovieServiceGrpc.TrendingMovieServiceImplBase {
    @Override
    public void getTop10Ratings(Top10RatingsRequest request, StreamObserver<Top10RatingsResponse> responseObserver) {
        List<String> top10MovieIds = getTop10MovieIds();
        List<MovieModel> movieModels = getTop10MoviesInfo(top10MovieIds);
        List<MovieInfo> movies = movieModels.stream().map(m -> MovieInfo.newBuilder()
                .setMovieId(m.getMovieId())
                .setName(m.getName())
                .setDescription(m.getDescription())
                .build()).toList();

        Top10RatingsResponse response = Top10RatingsResponse.newBuilder().addAllMovies(movies).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private final RestTemplate restTemplate;

    public UserRatingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @SuppressWarnings("unchecked")
    public List<String> getTop10MovieIds() {
        String ratingsUrl = "http://ratings-data-service/ratings/getTop10Ratings";
        return Objects.requireNonNull(restTemplate.getForObject(ratingsUrl, List.class));
    }

    @SuppressWarnings("unchecked")
    public List<MovieModel> getTop10MoviesInfo(List<String> top10MovieIds) {
        String baseUrl = "http://movie-info-service/movies/getTop10MoviesInfo";

        String urlWithParams = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("movieIds", top10MovieIds)  // Use correct param name
                .encode()
                .toUriString();

        ResponseEntity<List<MovieModel>> response = restTemplate.exchange(
                urlWithParams,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MovieModel>>() {}
        );

        return response.getBody();
    }



}

package com.moviecatalogservice.services;

import com.moviecatalogservice.models.Movie;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.proto.Schema.MovieInfo;
import org.example.proto.Schema.Top10RatingsResponse;
import org.example.proto.Schema.Top10RatingsRequest;
import org.example.proto.TrendingMovieServiceGrpc;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrendingMovieService {
    @GrpcClient("trending-movie-service")
    TrendingMovieServiceGrpc.TrendingMovieServiceBlockingStub synchronousClient;
    public List<Movie> getTop10Ratings() {
        Top10RatingsRequest request = Top10RatingsRequest.newBuilder().build();
        Top10RatingsResponse response = synchronousClient.getTop10Ratings(request);
        return response.getMoviesList().stream().map(m -> Movie.builder()
                .movieId(m.getMovieId())
                .name(m.getName())
                .description(m.getDescription())
                .build()).toList();
    }
}


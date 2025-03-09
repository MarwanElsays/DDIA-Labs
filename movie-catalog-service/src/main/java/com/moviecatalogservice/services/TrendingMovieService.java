package com.moviecatalogservice.services;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.proto.Schema.Top10RatingsResponse;
import org.example.proto.Schema.Top10RatingsRequest;
import org.example.proto.TrendingMovieServiceGrpc;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrendingMovieService {

    @GrpcClient("grpc-trending-movie-service")
    TrendingMovieServiceGrpc.TrendingMovieServiceBlockingStub synchronousClient;
    public List<String> getTop10Ratings() {
        // Create a request
        Top10RatingsRequest request = Top10RatingsRequest.newBuilder().build();
        // Call the RPC and get the response
        Top10RatingsResponse response = synchronousClient.getTop10Ratings(request);

        return response.getMovieIdsList();
    }
}


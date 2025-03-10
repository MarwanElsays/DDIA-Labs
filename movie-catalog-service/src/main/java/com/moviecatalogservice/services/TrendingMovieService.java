package com.moviecatalogservice.services;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.proto.Schema.Top10RatingsResponse;
import org.example.proto.Schema.Top10RatingsRequest;
import org.example.proto.TrendingMovieServiceGrpc;
import org.springframework.stereotype.Service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;

@Service
public class TrendingMovieService {

    @GrpcClient("grpc-trending-movie-service")
    TrendingMovieServiceGrpc.TrendingMovieServiceBlockingStub synchronousClient;

    public List<String> getTop10Ratings() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8084) // Use the correct server port
                .usePlaintext() // Use plaintext (no TLS)
                .build();

        // Create a blocking stub
        TrendingMovieServiceGrpc.TrendingMovieServiceBlockingStub synchronousClient = TrendingMovieServiceGrpc
                .newBlockingStub(channel);
        // Create a request
        Top10RatingsRequest request = Top10RatingsRequest.newBuilder().build();
        // Call the RPC and get the response
        Top10RatingsResponse response = synchronousClient.getTop10Ratings(request);

        // Shutdown the channel
        channel.shutdown();

        return response.getMovieIdsList();
    }
}
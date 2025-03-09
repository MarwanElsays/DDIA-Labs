package com.example.trendingmovieservice.services;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.proto.Schema.Top10RatingsResponse;
import org.example.proto.Schema.Top10RatingsRequest;
import org.example.proto.TrendingMovieServiceGrpc;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@GrpcService
public class UserRatingService extends TrendingMovieServiceGrpc.TrendingMovieServiceImplBase {
    @Override
    public void getTop10Ratings(Top10RatingsRequest request, StreamObserver<Top10RatingsResponse> responseObserver) {
        List<String> top10MovieIds = getTop10MovieIds();
        Top10RatingsResponse response = Top10RatingsResponse.newBuilder().addAllMovieIds(top10MovieIds).build();
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
}

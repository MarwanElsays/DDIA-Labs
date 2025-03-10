package com.moviecatalogservice.models;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatalogItem {
    private String name;
    private String description;
    private int rating;
}

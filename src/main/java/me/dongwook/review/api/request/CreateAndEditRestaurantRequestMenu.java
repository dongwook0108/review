package me.dongwook.review.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateAndEditRestaurantRequestMenu {

    private String name;
    private Integer price;


}

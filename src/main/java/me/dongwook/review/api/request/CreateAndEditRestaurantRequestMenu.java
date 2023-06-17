package me.dongwook.review.api.request;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateAndEditRestaurantRequestMenu {

    private String name;
    private Integer price;


}

package me.dongwook.review.api.request;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateAndEditRestaurantRequest {

    private String name;
    private String address;
    private List<CreateAndEditRestaurantRequestMenu> menus = new ArrayList<>();


}

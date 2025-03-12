package com.diningreview.controllers;

import com.diningreview.entities.Restaurant;
import com.diningreview.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants")
@CrossOrigin(origins = "http://localhost:4200")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public Restaurant saveNewRestaurant(@RequestBody Restaurant restaurant){
        return restaurantService.save(restaurant);
    }

    @GetMapping
    public Iterable<Restaurant> findAll(){
        return restaurantService.findAll();
    }

    @GetMapping("/{id}")
    public Restaurant findById(@PathVariable Long id){
        return restaurantService.findById(id);
    }
}

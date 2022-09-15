package com.example.GoReview.services;

import com.example.GoReview.models.*;
import com.example.GoReview.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    private List<Review> reviews;



    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(Long id){
        return restaurantRepository.findById(id);
    }

//    public Restaurant saveRestaurant(Restaurant restaurant){
//        restaurantRepository.save(restaurant);
//        return restaurant;
//    }

    public void saveRestaurant(Restaurant restaurant){
        restaurantRepository.save(restaurant);
    }

    public Reply addNewRestaurant (Restaurant restaurant) {
        saveRestaurant(restaurant);
        return new Reply(String.format("Restaurant %s was successfully created!", restaurant.getName()));
    }

//    public List<Restaurant> getRestaurantsByUserId(Long user_id){
//        return restaurantRepository.findByUserId(user_id);
//    }


    public void deleteRestaurant(Long id){
        restaurantRepository.deleteById(id);
    }

    public List<Restaurant> getAllRestaurantsByCuisine(Cuisine cuisine){
        return restaurantRepository.findByCuisine(cuisine);
    }

    /*
    public List<Restaurant> getAllRestaurantsByDiet(Diet diet) {
        return restaurantRepository.getRestaurantsByDiet(diet.ordinal());
    }
    
     */


    // in restaurant service:
// getRestaurantByRating(int) : List<Restaurants>
//    public List<Restaurant> getRestaurantByRating(int rating){
//        return reviewRepository.findByRating(rating);
//    }


// getRestaurantByFeatures() : List<Restaurant>


}

package com.example.GoReview.services;


import com.example.GoReview.models.*;
import com.example.GoReview.repositories.RestaurantRepository;
import com.example.GoReview.repositories.ReviewRepository;
import com.example.GoReview.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    UserRepository userRepository;

    public ReviewService(){}

    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }


    public Optional<Review> getReviewById(long id){
        return reviewRepository.findById(id);
    }


//    getReviewByUserId(long) : List<Review>
    public List<Review> getAllReviewsByUserId(long id){
        return reviewRepository.findAllByUserId(id);
    }

    public Reply processReview(Map<String, String> params) {
        long restaurantId = Long.parseLong(params.get("restaurant_id"));
        String username = (params.get("username"));
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        User user = userRepository.findByUsername(username).get();
        Rating rating = Rating.valueOf(params.get("rating"));
        Review review = new Review(user,restaurant,params.get("dateOfVisit"),rating);
        if(!(params.get("message") ==null)){
            review.setOptionalMessage(params.get("message"));
        } else if (!(params.get("accessibility") ==null)) {
            Accessibility accessibility = Accessibility.valueOf(params.get("accessibility"));
            review.addAccessibility(accessibility);
        }
        reviewRepository.save(review);
        return new Reply(String.format("Review id %d submitted successfully by user %s!", review.getId(), review.getUser().getUsername()));
    }


    /*
    public Reply checkExistingRestaurant (Review review) {
        if(restaurantService.getAllRestaurants().contains(review.getRestaurant())) {
            return processReview(review);
        } else {
            return new Reply(String.format("Could not find restaurant %s. Would you like to add this restaurant to the list?",
                    review.getRestaurant().getName()));
        }
    }

     */



    //    get all reviews by username
    public List<Review> getAllReviewsByUsername(String username){
        return reviewRepository.findAllByUserUsername(username);
    }

//  delete a review by review ID

    public void deleteReview(long id){
        reviewRepository.deleteById(id);
    }








}

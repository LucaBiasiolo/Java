package com.diningreview.services;

import com.diningreview.entities.DiningReview;
import com.diningreview.entities.DiningReviewStatus;
import com.diningreview.entities.Restaurant;
import com.diningreview.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private DiningReviewService diningReviewService;

    public void calculateScores(Restaurant restaurant){
        List<DiningReview> restaurantApprovedReviews = diningReviewService.findAllByRestaurantIdAndStatusApproved(restaurant.getId(), DiningReviewStatus.APPROVED);

        double peanutsScore = 0;
        double eggScore = 0;
        double dairyScore = 0;
        for (DiningReview review : restaurantApprovedReviews){
            if(review.getPeanutScore() != null) {
                peanutsScore += review.getPeanutScore();
            }
            if (review.getEggScore() != null){
                eggScore += review.getEggScore();
            }
            if (review.getDairyScore() != null){
                dairyScore += review.getDairyScore();
            }
        }
        List<DiningReview> reviewsWithPeanutsScore = restaurantApprovedReviews.stream().filter(diningReview -> diningReview.getPeanutScore() != null).toList();
        List<DiningReview> reviewsWithEggScore = restaurantApprovedReviews.stream().filter(diningReview -> diningReview.getEggScore() != null).toList();
        List<DiningReview> reviewsWithDairyScore = restaurantApprovedReviews.stream().filter(diningReview -> diningReview.getDairyScore() != null).toList();

        peanutsScore = peanutsScore / reviewsWithPeanutsScore.size();
        eggScore = eggScore / reviewsWithEggScore.size();
        dairyScore = dairyScore / reviewsWithDairyScore.size();

        restaurant.setPeanutsScore(peanutsScore);
        restaurant.setEggScore(eggScore);
        restaurant.setDairyScore(dairyScore);
        restaurant.setOverallScore((peanutsScore + eggScore + dairyScore) /3);
        restaurantRepository.save(restaurant);
    }

    public void calculateScoresStream(Restaurant restaurant){
        List<DiningReview> restaurantApprovedReviews = diningReviewService.findAllByRestaurantIdAndStatusApproved(restaurant.getId(),
                DiningReviewStatus.APPROVED);

        OptionalDouble averagePeanutScore = restaurantApprovedReviews.stream()
                .mapToDouble(DiningReview::getPeanutScore)
                .average();

        OptionalDouble averageEggScore = restaurantApprovedReviews.stream()
                .mapToDouble(DiningReview::getEggScore)
                .average();

        OptionalDouble averageDairyScore = restaurantApprovedReviews.stream()
                .mapToDouble(DiningReview::getDairyScore)
                .average();

        if(averagePeanutScore.isPresent()){
            restaurant.setPeanutsScore(averagePeanutScore.getAsDouble());
        }
        if(averageEggScore.isPresent()){
            restaurant.setEggScore(averageEggScore.getAsDouble());
        }
        if(averageDairyScore.isPresent()){
            restaurant.setDairyScore(averageDairyScore.getAsDouble());
        }
        restaurant.setOverallScore((averagePeanutScore.orElse(0) + averageEggScore.orElse(0) + averageDairyScore.orElse(0)) /3);
        restaurantRepository.save(restaurant);
    }

    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Iterable<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }
}

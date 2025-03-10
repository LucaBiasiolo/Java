package com.diningreview.services;

import com.diningreview.entities.DiningReview;
import com.diningreview.entities.DiningReviewStatus;
import com.diningreview.repositories.DiningReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiningReviewService {

    @Autowired
    private DiningReviewRepository diningReviewRepository;

    public List<DiningReview> findAllByRestaurantIdAndStatusApproved(Long restaurantId, DiningReviewStatus status){
        return diningReviewRepository.findAllByRestaurantIdAndStatus(restaurantId, status);
    }
}

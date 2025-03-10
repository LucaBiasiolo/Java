package com.diningreview.repositories;

import com.diningreview.entities.DiningReview;
import com.diningreview.entities.DiningReviewStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*As a registered user, I want to submit a dining review.
(save)
As an admin, I want to get the list of all dining reviews that are pending approval.
(findAllByStatus)
As an admin, I want to approve or reject a given dining review.
(updateStatus)
As part of the backend process that updates a restaurant’s set of scores,
I want to fetch the set of all approved dining reviews belonging to this restaurant.
(findAllByStatus)
*/
@Repository
public interface DiningReviewRepository extends CrudRepository<DiningReview, Long> {
    List<DiningReview> findAllByRestaurantIdAndStatus(Long restaurantId, DiningReviewStatus status);
}

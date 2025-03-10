package com.diningreview.repositories;

import com.diningreview.entities.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*As an application experience, I want to submit a new restaurant entry.
Should a restaurant with the same name and with the same zip code already exist, I will see a failure.
(save)
As an application experience, I want to fetch the details of a restaurant, given its unique Id.
(findById)
As an application experience, I want to fetch restaurants that match a given zip code and that also
have at least one user-submitted score for a given allergy. I want to see them sorted in descending order.
(findByZipCodeAndAllergyScoreOrderByAllergyScoreDesc)
*/
@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant,Long> {

    // TODO: complete parameters
    Iterable<Restaurant> findByZipCodeOrderByOverallScoreDesc(String zipCode);
}

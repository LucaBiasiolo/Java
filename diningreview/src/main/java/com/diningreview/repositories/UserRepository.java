package com.diningreview.repositories;

import com.diningreview.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*User entity-related scenarios:

As an unregistered user, I want to create my user profile using a display name that’s unique only to me.
(save)
As a registered user, I want to update my user profile. I cannot modify my unique display name.
(update)
As an application experience, I want to fetch the user profile belonging to a given display name.
(findByUsername)
As part of the backend process that validates a user-submitted dining review,
I want to verify that the user exists, based on the user display name associated with the dining review.
(findByUsername)
*/

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}

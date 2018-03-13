package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.User;

import java.util.List;
import java.util.Set;

public interface UserRepository {
    User save( User user );

    // false if not found
    boolean delete( int userId );

    // null if not found
    User get( int userId );

    // null if not found
    User getByEmail( String email );

    List<Integer> getMeals( int userId );

    User addMeal( int userId, int mealId );

    List<User> getAll();
}

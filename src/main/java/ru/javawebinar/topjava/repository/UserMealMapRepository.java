package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.Collection;
import java.util.List;

public interface UserMealMapRepository {
    User save( User user, Collection<Meal> meals );

    // false if not found
    boolean delete( User user, Meal meal );

    // null if not found
    Collection<Meal> getMealsbyUser( User user );
}
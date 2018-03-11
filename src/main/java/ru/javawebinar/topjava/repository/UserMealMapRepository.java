package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.Collection;
import java.util.List;

public interface UserMealMapRepository {
    List<Meal> save( User user, List<Meal> meals );

    void delete( User user, List<Meal> meal );

    // null if not found
    List<Meal> getMealsbyUser( User user );
}

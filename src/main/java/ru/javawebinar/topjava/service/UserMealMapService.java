package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

public interface UserMealMapService {

    List<Meal> create( User user, List<Meal> meals );

    void delete( User user, List<Meal> meals ) throws NotFoundException;

    List<Meal> getMealsbyUser( User user ) throws NotFoundException;

    void update( User user, List<Meal> meals );

}
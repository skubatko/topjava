package ru.javawebinar.topjava.service;


import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;
import java.util.Set;

public interface UserService {

    User create( User user );

    void delete( int userId ) throws NotFoundException;

    User get( int userId ) throws NotFoundException;

    User getByEmail( String email ) throws NotFoundException;

    void update( User user );

    List<Integer> getMeals( int userId );

    void addMeal( int userId, int mealId );

    List<User> getAll();
}

package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserMealMapRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserMealMapServiceImpl implements UserMealMapService {

    private final UserMealMapRepository repository;

    @Autowired
    public UserMealMapServiceImpl( UserMealMapRepository repository ) {
        this.repository = repository;
    }

    @Override
    public List<Meal> create( User user, List<Meal> meals ) {
        repository.save( user, meals );
        return meals;
    }

    @Override
    public void delete( User user, List<Meal> meals ) throws NotFoundException {
        repository.delete( user, meals );
    }

    @Override
    public List<Meal> getMealsbyUser( User user ) throws NotFoundException {
        return repository.getMealsbyUser( user );
    }

    @Override
    public void update( User user, List<Meal> meals ) {
        repository.save( user, meals );
    }
}
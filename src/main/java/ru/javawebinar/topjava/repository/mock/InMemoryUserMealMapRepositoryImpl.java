package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserMealMapRepository;
import ru.javawebinar.topjava.util.UserMealMapUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserMealMapRepositoryImpl implements UserMealMapRepository {
    private Map<User, List<Meal>> repository = new ConcurrentHashMap<>();

    {
        UserMealMapUtil.usersMealsMap.forEach( this::save );
    }

    @Override
    public List<Meal> save( User user, List<Meal> meals ) {
        // treat case: no such user presented
        if ( user.isNew() ) {
            repository.put( user, meals );
            return meals;
        }

        // treat case: update, but absent in storage
        repository.computeIfPresent( user, ( oldUser, oldMeals ) -> meals );
        return meals;
    }

    @Override
    public void delete( User user, List<Meal> meals ) {
        meals.forEach( o -> repository.get( user ).remove( o ) );
    }

    @Override
    public List<Meal> getMealsbyUser( User user ) {
        return repository.get( user ).stream().sorted( ( o1, o2 ) -> o2.getTime().compareTo( o1.getTime() ) ).collect( Collectors.toList() );
    }
}

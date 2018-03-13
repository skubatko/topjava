package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;
import java.util.Set;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl( UserRepository repository ) {
        this.repository = repository;
    }

    @Override
    public User create( User user ) {
        return repository.save( user );
    }

    @Override
    public void update( User user ) {
        checkNotFoundWithId( repository.save( user ), user.getId() );
    }

    @Override
    public void delete( int id ) throws NotFoundException {
        checkNotFoundWithId( repository.delete( id ), id );
    }

    @Override
    public User get( int id ) throws NotFoundException {
        return checkNotFoundWithId( repository.get( id ), id );
    }

    @Override
    public User getByEmail( String email ) throws NotFoundException {
        return checkNotFound( repository.getByEmail( email ), "email=" + email );
    }

    @Override
    public List<Integer> getMeals( int userId ) {
        return checkNotFoundWithId( repository.get( userId ).getMeals(), userId );
    }

    @Override
    public void addMeal( int userId, int mealId ) {
        checkNotFoundWithId( repository.get( userId ).addMeal( mealId ), userId );
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
}
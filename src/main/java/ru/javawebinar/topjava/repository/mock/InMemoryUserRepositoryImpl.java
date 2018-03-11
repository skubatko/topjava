package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UsersUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger( 0 );

    {
        UsersUtil.USERS.forEach( this::save );
    }

    @Override
    public User save( User user ) {
        if ( user.isNew() ) {
            user.setId( counter.incrementAndGet() );
            repository.put( user.getId(), user );
            return user;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent( user.getId(), ( id, oldMeal ) -> user );
    }

    @Override
    public boolean delete( int id ) {
        return repository.remove( id ) != null;
    }

    @Override
    public User get( int id ) {
        return repository.get( id );
    }

    @Override
    public User getByEmail( String email ) {
        return repository.values().stream().filter( user -> user.getEmail().equals( email ) ).findFirst().get();
    }

    @Override
    public Collection<User> getAll() {
        return repository.values().stream().sorted().collect( Collectors.toList() );
    }
}

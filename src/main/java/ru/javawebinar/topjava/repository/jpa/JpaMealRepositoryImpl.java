package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional( readOnly = true )
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save( Meal meal, int userId ) {
        meal.setUser( em.getReference( User.class, userId ) );
        if ( meal.isNew() ) {
            em.persist( meal );
            return meal;
        } else {
            return em.merge( meal );
        }
    }

    @Override
    public Meal get( int id, int userId ) {
        return em.find( Meal.class, id );
    }

    @Override
    @Transactional
    public boolean delete( int id, int userId ) {
        return em.createNamedQuery( Meal.DELETE )
                .setParameter( "id", id )
                .setParameter( "userId", userId )
                .executeUpdate() != 0;
    }

    @Override
    public List<Meal> getAll( int userId ) {
        return em.createNamedQuery( Meal.ALL, Meal.class )
                .setParameter( "userId", userId )
                .getResultList();
    }

    @Override
    public List<Meal> getBetween( LocalDateTime startDate, LocalDateTime endDate, int userId ) {
        return em.createNamedQuery( Meal.BETWEEN, Meal.class )
                .setParameter( 1, userId )
                .setParameter( 2, startDate )
                .setParameter( 3, endDate )
                .getResultList();
    }
}
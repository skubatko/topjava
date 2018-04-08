package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional( readOnly = true )
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
    @Query( "DELETE FROM Meal m WHERE m.id=:id AND user_id=:uesrId" )
    int delete( @Param( "id" ) int id, @Param( "userId" ) int userId );

    @Transactional
    Meal save( Meal meal, int userId);

    Optional<Meal> findById( Integer id, int userId );

    List<Meal> findAll( Sort sort, int userId);

    List<Meal> getBetween( LocalDateTime startDate, LocalDateTime endDate, int userId );

}

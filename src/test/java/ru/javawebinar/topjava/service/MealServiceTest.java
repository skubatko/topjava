package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration( {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
} )
@RunWith( SpringRunner.class )
@Sql( scripts = "classpath:db/populateDB.sql", config = @SqlConfig( encoding = "UTF-8" ) )
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;


    @Test
    public void create() throws Exception {
        Meal newMeal = new Meal( LocalDateTime.of( 2015, Month.MAY, 31, 21, 0 ), "NewMeal", 555 );
        Meal created = service.create( newMeal, ADMIN_ID );
        newMeal.setId( created.getId() );
        List<Meal> meals = new ArrayList<>( ADMIN_MEALS );
        meals.add( newMeal );
        assertMatch( service.getAll( ADMIN_ID ), meals );
    }

    @Test( expected = DataAccessException.class )
    public void duplicateDateTimeCreate() throws Exception {
        service.create( new Meal( null, LocalDateTime.of( 2015, Month.MAY, 30, 20, 0 ), "Dublicated", 170 ), USER_ID );
    }

    @Test
    public void get() throws Exception {
        Meal userMeal = service.get( MEAL_START_ID, USER_ID );
        MealTestData.assertMatch( userMeal, USER_MEALS.get( 0 ) );
    }

    @Test( expected = NotFoundException.class )
    public void getNotFound() throws Exception {
        service.get( 1, USER_ID );
    }

    @Test
    public void update() {
        Meal updated = new Meal( ADMIN_MEALS.get( 0 ) );
        updated.setDescription( "updatedDescription" );
        updated.setCalories( DEFAULT_CALORIES_PER_DAY );
        service.update( updated, ADMIN_ID );
        assertMatch( service.get( ADMIN_MEALS.get( 0 ).getId(), ADMIN_ID ), updated );
    }

    @Test
    public void delete() throws Exception {
        service.delete( MEAL_START_ID + 1, USER_ID );
        service.delete( MEAL_START_ID + 2, USER_ID );
        assertMatch( service.getAll( USER_ID ), USER_MEALS.get( 0 ) );

    }

    @Test( expected = NotFoundException.class )
    public void notFoundDelete() throws Exception {
        service.delete( 1, USER_ID );
    }

    @Test
    public void getBetweenDates() throws Exception {
        LocalDate startDate = LocalDate.of( 2015, Month.MAY, 30 );
        LocalDate endDate = LocalDate.of( 2015, Month.MAY, 31 );
        assertMatch( service.getBetweenDates( startDate, endDate, USER_ID ), USER_MEALS );
    }

    @Test
    public void getBetweenDateTimes() {
        LocalDateTime startDate = LocalDateTime.of( 2015, Month.MAY, 30, 14, 0 );
        LocalDateTime endDate = LocalDateTime.of( 2015, Month.MAY, 30, 21, 0 );
        assertMatch( service.getBetweenDateTimes( startDate, endDate, USER_ID ), USER_MEALS.get( 2 ) );
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> userMeal = service.getAll( USER_ID );
        MealTestData.assertMatch( userMeal, USER_MEALS );
    }

}
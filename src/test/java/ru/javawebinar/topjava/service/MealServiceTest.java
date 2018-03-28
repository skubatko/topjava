package ru.javawebinar.topjava.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration( {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
} )
@RunWith( SpringJUnit4ClassRunner.class )
@Sql( scripts = "classpath:db/populateDB.sql", config = @SqlConfig( encoding = "UTF-8" ) )
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    private static final Logger log = LoggerFactory.getLogger( MealServiceTest.class );
    private static final Map<String, Long> timers = new ConcurrentHashMap<>();

    @Autowired
    private MealService service;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @PostConstruct
    public void postConstruct() {
        timers.clear();
    }

    @PreDestroy
    public void preDestroy() {
        log.info( "+++ Final report:" );
        timers.forEach( ( n, m ) -> log.info( "Test: " + n + " completed in: " + m + " seconds" ) );
    }

    @Test
    public void delete() throws Exception {
        long startTime = System.currentTimeMillis();

        service.delete( MEAL1_ID, USER_ID );
        assertMatch( service.getAll( USER_ID ), MEAL6, MEAL5, MEAL4, MEAL3, MEAL2 );

        long runTime = ( System.currentTimeMillis() - startTime ) / 1_000;
        log.info( "Test: " + Thread.currentThread().getName() + " completed in: " + runTime + " seconds" );
        timers.put( Thread.currentThread().getName(), runTime );
    }

    @Test
    public void deleteNotFound() throws Exception {
        long startTime = System.currentTimeMillis();

        thrown.expect( NotFoundException.class );
        service.delete( MEAL1_ID, 1 );

        long runTime = ( System.currentTimeMillis() - startTime ) / 1_000;
        log.info( "Test: " + Thread.currentThread().getName() + " completed in: " + runTime + " seconds" );
        timers.put( Thread.currentThread().getName(), runTime );
    }

    @Test
    public void save() throws Exception {
        long startTime = System.currentTimeMillis();

        Meal created = getCreated();
        service.create( created, USER_ID );
        assertMatch( service.getAll( USER_ID ), created, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1 );

        long runTime = ( System.currentTimeMillis() - startTime ) / 1_000;
        log.info( "Test: " + Thread.currentThread().getName() + " completed in: " + runTime + " seconds" );
        timers.put( Thread.currentThread().getName(), runTime );
    }

    @Test
    public void get() throws Exception {
        long startTime = System.currentTimeMillis();

        Meal actual = service.get( ADMIN_MEAL_ID, ADMIN_ID );
        assertMatch( actual, ADMIN_MEAL1 );

        long runTime = ( System.currentTimeMillis() - startTime ) / 1_000;
        log.info( "Test: " + Thread.currentThread().getName() + " completed in: " + runTime + " seconds" );
        timers.put( Thread.currentThread().getName(), runTime );
    }

    @Test
    public void getNotFound() throws Exception {
        long startTime = System.currentTimeMillis();

        thrown.expect( NotFoundException.class );
        service.get( MEAL1_ID, ADMIN_ID );

        long runTime = ( System.currentTimeMillis() - startTime ) / 1_000;
        log.info( "Test: " + Thread.currentThread().getName() + " completed in: " + runTime + " seconds" );
        timers.put( Thread.currentThread().getName(), runTime );
    }

    @Test
    public void update() throws Exception {
        long startTime = System.currentTimeMillis();

        Meal updated = getUpdated();
        service.update( updated, USER_ID );
        assertMatch( service.get( MEAL1_ID, USER_ID ), updated );

        long runTime = ( System.currentTimeMillis() - startTime ) / 1_000;
        log.info( "Test: " + Thread.currentThread().getName() + " completed in: " + runTime + " seconds" );
        timers.put( Thread.currentThread().getName(), runTime );
    }

    @Test( expected = NotFoundException.class )
    public void updateNotFound() throws Exception {
        long startTime = System.currentTimeMillis();

        service.update( MEAL1, ADMIN_ID );

        long runTime = ( System.currentTimeMillis() - startTime ) / 1_000;
        log.info( "Test: " + Thread.currentThread().getName() + " completed in: " + runTime + " seconds" );
        timers.put( Thread.currentThread().getName(), runTime );
    }

    @Test
    public void getAll() throws Exception {
        long startTime = System.currentTimeMillis();

        assertMatch( service.getAll( USER_ID ), MEALS );

        long runTime = ( System.currentTimeMillis() - startTime ) / 1_000;
        log.info( "Test: " + Thread.currentThread().getName() + " completed in: " + runTime + " seconds" );
        timers.put( Thread.currentThread().getName(), runTime );
    }

    @Test
    public void getBetween() throws Exception {
        long startTime = System.currentTimeMillis();

        assertMatch( service.getBetweenDates(
                LocalDate.of( 2015, Month.MAY, 30 ),
                LocalDate.of( 2015, Month.MAY, 30 ), USER_ID ), MEAL3, MEAL2, MEAL1 );

        long runTime = ( System.currentTimeMillis() - startTime ) / 1_000;
        log.info( "Test: " + Thread.currentThread().getName() + " completed in: " + runTime + " seconds" );
        timers.put( Thread.currentThread().getName(), runTime );
    }
}
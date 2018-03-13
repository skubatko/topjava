package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger( getClass() );

    @Autowired
    private MealService service;

    public Meal get( int id ) {
        log.info( "get meals:{}", id );
        return service.get( id );
    }

    public Meal create( Meal meal ) {
        log.info( "create meal:{}", meal );
        return service.create( meal );
    }

    public void delete( int id ) {
        log.info( "delete meal:{}", id );
        service.delete( id );
    }

    public void update( Meal meal ) {
        log.info( "update meal:{}", meal );
        service.update( meal );
    }

}

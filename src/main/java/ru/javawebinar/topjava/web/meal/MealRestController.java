package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserMealMapService;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger( getClass() );

    @Autowired
    private UserMealMapService service;

    public List<Meal> get( User user ) {
        log.info( "get meals by user:{}", user );
        return service.getMealsbyUser( user );
    }

    public List<Meal> create( User user, List<Meal> meals ) {
        log.info( "create meals:{} at user:{}", meals, user );
        return service.create( user, meals );
    }

    public void delete( User user, List<Meal> meals ) {
        log.info( "delete user:{} meals:{}", user, meals );
        service.delete( user, meals );
    }

    public void update( User user, List<Meal> meals ) {
        log.info( "update user:{} with meals:{}", user, meals );
        service.update( user, meals );
    }

}

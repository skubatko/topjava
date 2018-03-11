package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class UserMealMapUtil {
    public static final Map<User, Collection<Meal>> usersMealsMap;

    static {
        Map<User, Collection<Meal>> usersMealsMapInit = new HashMap<>();
        usersMealsMapInit.put( new User( "User10", "user10@topjaca.ru", "pass10", Role.ROLE_USER ),
                Arrays.asList(
                        new Meal( LocalDateTime.of( 2015, Month.MAY, 30, 10, 0 ), "Завтрак", 500 ),
                        new Meal( LocalDateTime.of( 2015, Month.MAY, 30, 13, 0 ), "Обед", 1000 ),
                        new Meal( LocalDateTime.of( 2015, Month.MAY, 30, 20, 0 ), "Ужин", 500 )
                )
        );
        usersMealsMapInit.put( new User( "User2", "user10@topjaca.ru", "pass10", Role.ROLE_USER ),
                Arrays.asList(
                        new Meal( LocalDateTime.of( 2015, Month.MAY, 31, 10, 0 ), "Завтрак", 1000 ),
                        new Meal( LocalDateTime.of( 2015, Month.MAY, 31, 13, 0 ), "Обед", 500 ),
                        new Meal( LocalDateTime.of( 2015, Month.MAY, 31, 20, 0 ), "Ужин", 510 )
                )
        );
        usersMealsMapInit.put( new User( "User43", "user10@topjaca.ru", "pass10", Role.ROLE_USER ),
                Arrays.asList(
                        new Meal( LocalDateTime.of( 2015, Month.MAY, 30, 10, 0 ), "Завтрак", 500 ),
                        new Meal( LocalDateTime.of( 2015, Month.MAY, 30, 13, 0 ), "Обед", 1000 ),
                        new Meal( LocalDateTime.of( 2015, Month.MAY, 30, 20, 0 ), "Ужин", 500 )
                )
        );
        usersMealsMapInit.put( new User( "User14", "user10@topjaca.ru", "pass10", Role.ROLE_USER ),
                Arrays.asList(
                        new Meal( LocalDateTime.of( 2015, Month.MAY, 31, 10, 0 ), "Завтрак", 1000 ),
                        new Meal( LocalDateTime.of( 2015, Month.MAY, 31, 13, 0 ), "Обед", 500 ),
                        new Meal( LocalDateTime.of( 2015, Month.MAY, 31, 20, 0 ), "Ужин", 510 )
                )
        );
        usersMealsMapInit.put( new User( "User5", "user10@topjaca.ru", "pass10", Role.ROLE_USER ),
                Arrays.asList(
                        new Meal( LocalDateTime.of( 2015, Month.MAY, 30, 10, 0 ), "Завтрак", 500 ),
                        new Meal( LocalDateTime.of( 2015, Month.MAY, 30, 13, 0 ), "Обед", 1000 ),
                        new Meal( LocalDateTime.of( 2015, Month.MAY, 30, 20, 0 ), "Ужин", 500 )
                )
        );
        usersMealsMap = Collections.unmodifiableMap( usersMealsMapInit );
    }
}

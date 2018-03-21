package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static final int MEAL_START_ID = START_SEQ + 2;

    public static final List<Meal> USER_MEALS = Arrays.asList(
            new Meal( MEAL_START_ID, LocalDateTime.of( 2015, Month.MAY, 30, 10, 0 ), "Завтрак", 500 ),
            new Meal( MEAL_START_ID + 1, LocalDateTime.of( 2015, Month.MAY, 30, 13, 0 ), "Обед", 1000 ),
            new Meal( MEAL_START_ID + 2, LocalDateTime.of( 2015, Month.MAY, 30, 20, 0 ), "Ужин", 500 )
    );

    public static final List<Meal> ADMIN_MEALS = Arrays.asList(
            new Meal( MEAL_START_ID + 3, LocalDateTime.of( 2015, Month.MAY, 31, 10, 0 ), "Завтрак", 1000 ),
            new Meal( MEAL_START_ID + 4, LocalDateTime.of( 2015, Month.MAY, 31, 13, 0 ), "Обед", 500 ),
            new Meal( MEAL_START_ID + 5, LocalDateTime.of( 2015, Month.MAY, 31, 20, 0 ), "Ужин", 510 )
    );

    public static void assertMatch( Meal actual, Meal expected ) {
        assertThat( actual ).isEqualTo( expected );
    }

    public static void assertMatch( Iterable<Meal> actual, Meal... expected ) {
        assertMatch( actual, Arrays.asList( expected ) );
    }

    public static void assertMatch( Iterable<Meal> actual, Iterable<Meal> expected ) {
        assertThat( actual ).isEqualTo( expected );
    }
}

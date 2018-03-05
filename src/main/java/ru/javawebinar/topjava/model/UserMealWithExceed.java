package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMealWithExceed extends UserMeal {

    private final boolean exceed;

    public UserMealWithExceed( UserMeal userMeal, boolean exceed ) {
        super( userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories() );
        this.exceed = exceed;
    }

    @Override
    public String toString() {
        return String.valueOf( super.getDateTime() ) + " " + super.getDescription() + " " + String.valueOf( exceed );
    }
}

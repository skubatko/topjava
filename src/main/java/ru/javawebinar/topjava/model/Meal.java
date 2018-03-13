package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.model.AbstractNamedEntity;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal extends AbstractNamedEntity implements Comparable {

    private final LocalDateTime dateTime;

    private final int calories;

    public Meal( LocalDateTime dateTime, String description, int calories ) {
        this( null, dateTime, description, calories );
    }

    public Meal( Integer id, LocalDateTime dateTime, String description, int calories ) {
        super( id, description );
        this.dateTime = dateTime;
        this.calories = calories;
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return getName();
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + getName() + '\'' +
                ", calories=" + calories +
                '}';
    }

    @Override
    public int compareTo( Object o ) {
        Meal meal = (Meal) o;
        return meal.getTime().compareTo( this.getTime() );
    }

}

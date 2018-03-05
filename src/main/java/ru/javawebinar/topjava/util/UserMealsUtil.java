package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main( String[] args ) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal( LocalDateTime.of( 2015, Month.MAY, 30, 10, 0 ), "Завтрак", 500 ),
                new UserMeal( LocalDateTime.of( 2015, Month.MAY, 30, 13, 0 ), "Обед", 1000 ),
                new UserMeal( LocalDateTime.of( 2015, Month.MAY, 30, 20, 0 ), "Ужин", 500 ),
                new UserMeal( LocalDateTime.of( 2015, Month.MAY, 31, 10, 0 ), "Завтрак", 1000 ),
                new UserMeal( LocalDateTime.of( 2015, Month.MAY, 31, 13, 0 ), "Обед", 500 ),
                new UserMeal( LocalDateTime.of( 2015, Month.MAY, 31, 20, 0 ), "Ужин", 510 )
        );
        System.out.println( Arrays.toString( getFilteredWithExceeded( mealList, LocalTime.of( 7, 0 ), LocalTime.of( 12, 0 ), 2000 ).toArray() ) );
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded( List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay ) {

        return getByCycles( mealList, startTime, endTime, caloriesPerDay );
//        return getByRecursion( mealList, startTime, endTime, caloriesPerDay );
//        return getByStreamAPI( mealList, startTime, endTime, caloriesPerDay );
    }


    public static List<UserMealWithExceed> getByCycles( List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay ) {
        List<UserMealWithExceed> result = new ArrayList<>();

        HashMap<LocalDate, Integer> mealsPerDayMap = new HashMap<>();
        for ( UserMeal o : mealList ) {
            LocalDate oDate = o.getDateTime().toLocalDate();
            int calories = o.getCalories();
            if ( mealsPerDayMap.containsKey( oDate ) ) {
                calories += mealsPerDayMap.get( oDate );
            }
            mealsPerDayMap.put( oDate, calories );
        }

        HashMap<LocalDate, Boolean> mealsExceededPerDayMap = new HashMap<>();
        for ( Map.Entry<LocalDate, Integer> map : mealsPerDayMap.entrySet() ) {
            if ( map.getValue() > caloriesPerDay ) {
                mealsExceededPerDayMap.put( map.getKey(), true );
            } else {
                mealsExceededPerDayMap.put( map.getKey(), false );
            }
        }

        for ( UserMeal o : mealList ) {
            if ( startTime.isBefore( o.getDateTime().toLocalTime() ) && endTime.isAfter( o.getDateTime().toLocalTime() ) ) {
                result.add( new UserMealWithExceed( o, mealsExceededPerDayMap.get( o.getDateTime().toLocalDate() ) ) );
            }
        }

        return result;
    }

    public static List<UserMealWithExceed> getByRecursion( List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay ) {
        List<UserMealWithExceed> result = new ArrayList<>();

        int caloriesToday = sumCalories( mealList );
        boolean isExceeded = caloriesToday > caloriesPerDay;

        for ( UserMeal o : mealList ) {
            if ( startTime.isBefore( o.getDateTime().toLocalTime() ) && endTime.isAfter( o.getDateTime().toLocalTime() ) ) {
                result.add( new UserMealWithExceed( o, isExceeded ) );
            }
            caloriesToday += o.getCalories();
        }
        return result;

    }

    public static int sumCalories( List<UserMeal> userMeals ) {
        if ( userMeals.size() > 1 ) {
            return userMeals.get( 0 ).getCalories() + sumCalories( userMeals.subList( 1, userMeals.size() ) );
        } else {
            return userMeals.get( 0 ).getCalories();
        }
    }

    public static List<UserMealWithExceed> getByStreamAPI( List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay ) {
        List<UserMealWithExceed> result = new ArrayList<>();
        return result;
    }

}

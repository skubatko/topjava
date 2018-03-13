package ru.javawebinar.topjava.model;

import java.util.*;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class User extends AbstractNamedEntity implements Comparable {

    private String email;

    private String password;

    private boolean enabled = true;

    private Date registered = new Date();

    private Set<Role> roles;

    private List<Integer> meals = new Vector<>();

    private int caloriesPerDay = DEFAULT_CALORIES_PER_DAY;

    public User( Integer id, String name ) {
        super( id, name );
    }

    public User( String name, String email, String password, Role role, Role... roles ) {
        this( null, name, email, password, DEFAULT_CALORIES_PER_DAY, true, null, EnumSet.of( role, roles ) );
    }

    public User( String name, String email, String password, List<Integer> meals, Role role, Role... roles ) {
        this( null, name, email, password, DEFAULT_CALORIES_PER_DAY, true, meals, EnumSet.of( role, roles ) );
    }

    public User( Integer id, String name, String email, String password, Role role, Role... roles ) {
        this( id, name, email, password, DEFAULT_CALORIES_PER_DAY, true, null, EnumSet.of( role, roles ) );
    }

    public User( Integer id, String name, String email, String password, int caloriesPerDay, boolean enabled, List<Integer> meals, Set<Role> roles ) {
        super( id, name );
        this.email = email;
        this.password = password;
        this.caloriesPerDay = caloriesPerDay;
        this.enabled = enabled;
        this.meals = meals;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered( Date registered ) {
        this.registered = registered;
    }

    public void setEnabled( boolean enabled ) {
        this.enabled = enabled;
    }

    public int getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public void setCaloriesPerDay( int caloriesPerDay ) {
        this.caloriesPerDay = caloriesPerDay;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public List<Integer> getMeals() {
        return meals;
    }

    public void addMeal( int mealId ) {
        meals.add( mealId );
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User (" +
                "id=" + id +
                ", email=" + email +
                ", name=" + name +
                ", enabled=" + enabled +
                ", meals=" + meals +
                ", roles=" + roles +
                ", caloriesPerDay=" + caloriesPerDay +
                ')';
    }

    @Override
    public int compareTo( Object o ) {
        User user = (User) o;
        return this.name.compareTo( user.getName() );
    }

}
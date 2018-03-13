package ru.javawebinar.topjava.web.user;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.User;

import java.util.List;
import java.util.Set;

@Controller
public class AdminRestController extends AbstractUserController {

    @Override
    public User get(int id) {
        return super.get(id);
    }

    @Override
    public User create(User user) {
        return super.create(user);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void update(User user, int id) {
        super.update(user, id);
    }

    @Override
    public User getByMail(String email) {
        return super.getByMail(email);
    }

    @Override
    public List<Integer> getMeals( int userId ) {
        return super.getMeals( userId );
    }

    @Override
    public void addMeal( int userId, int mealId ) {
        super.addMeal( userId, mealId );
    }

    @Override
    public List<User> getAll() {
        return super.getAll();
    }
}
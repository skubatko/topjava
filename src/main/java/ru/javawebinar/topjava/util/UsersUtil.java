package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User( "User10", "user10@topjaca.ru", "pass10", Role.ROLE_USER ),
            new User( "User2", "user2@topjaca.ru", "pass2", Role.ROLE_ADMIN ),
            new User( "User43", "user43@topjaca.ru", "pass43", Role.ROLE_USER ),
            new User( "User14", "user14@topjaca.ru", "pass14", Role.ROLE_USER ),
            new User( "User5", "user5@topjaca.ru", "pass5", Role.ROLE_ADMIN )
    );
}
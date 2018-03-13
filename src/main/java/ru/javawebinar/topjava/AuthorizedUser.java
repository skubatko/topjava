package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.User;

public class AuthorizedUser extends User {

    public AuthorizedUser( Integer id, String name ) {
        super( id, name );
    }

    public static int id() {
        return 1;
    }

}
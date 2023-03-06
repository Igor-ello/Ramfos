package com.example.myapplication;

public class User {
    public static User uniqueUser;

    private User() {}

    public static User getInstance() {
        if (uniqueUser == null) {
            uniqueUser = new User();
        }
        return uniqueUser;
    }

}

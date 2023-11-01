package com.example.systemgestiondesdettefournisseurs.Models;

public class getData {

    public   static String username;
    public   static String password;

    public getData() {
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        getData.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        getData.password = password;
    }
}


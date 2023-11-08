package com.example.systemgestiondesdettefournisseurs.Models;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author BakJunior
 * My email is number --> bakbassjunior@gmail.com (+225 0758084992)
 * Thanks! <
 */
public class database {

    public static Connection connectDb() {

        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/dettefournisseur", "root", "");
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

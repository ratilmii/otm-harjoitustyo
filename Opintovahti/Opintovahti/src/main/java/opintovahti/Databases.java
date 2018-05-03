/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opintovahti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Miika
 */
public class Databases {
        
    private Databases(){
        
    }
    
    public static void createUser(String username, byte[] password) throws Exception {
        
        
        Connection connection = DriverManager.getConnection("jdbc:sqlite:users.db");
        
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Users (username, password) VALUES ('" + username + "', '" + password + "');");
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            String usr = rs.getString("username");
            String psd = rs.getString("password");

            System.out.println(usr + " " + psd);
        }
        
        stmt.close();
        rs.close();

        connection.close();
       
    }
}

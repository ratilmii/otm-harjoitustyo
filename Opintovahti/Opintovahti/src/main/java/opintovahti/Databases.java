package opintovahti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Databases {
        
    private Databases(){
        
    }
    
    public static void createUser(String username, String hash) throws Exception {
        
        Connection connection = DriverManager.getConnection("jdbc:sqlite:users.db");
        
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO User (username, hash) VALUES ('" + username + "', '" + hash + "');");
        stmt.executeUpdate();
        
        stmt.close();
        connection.close();
    }
        
    public static boolean checkIfUserExists(String username) throws Exception {
        
        Connection connection = DriverManager.getConnection("jdbc:sqlite:users.db");
        
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User WHERE username = '" + username + "';");
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            stmt.close();
            connection.close();
            return true;
        }else{
            stmt.close();
            connection.close();
            return false;
        }
        
    }
    
    public static String getHash(String username) throws Exception {
        
        Connection connection = DriverManager.getConnection("jdbc:sqlite:users.db");
        
        PreparedStatement stmt = connection.prepareStatement("SELECT hash FROM User WHERE username = '" + username + "';");
        ResultSet rs = stmt.executeQuery();
        
        String hash = rs.getString("hash");
        
        
        stmt.close();
        connection.close();
        
        return hash;
        
    }    
}

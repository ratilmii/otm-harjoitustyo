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
        Integer id = getUserId(username);
        PreparedStatement stmt2 = connection.prepareStatement("INSERT INTO CurrentPeriod (periodNumber, user_id) VALUES (1, " + id + ");");
        stmt2.executeUpdate();
        stmt2.close();
        
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
    
    public static Integer getUserId(String username) throws Exception {
        
        Connection connection = DriverManager.getConnection("jdbc:sqlite:users.db");
        
        PreparedStatement stmt = connection.prepareStatement("SELECT id FROM User WHERE username = '" + username + "';");
        ResultSet rs = stmt.executeQuery();
        
        Integer id = rs.getInt("id");

        stmt.close();
        connection.close();
        
        return id;
        
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
    
    public static Integer checkCurrentPeriod(Integer userId) throws Exception {
        
        Connection connection = DriverManager.getConnection("jdbc:sqlite:users.db");
        
        PreparedStatement stmt = connection.prepareStatement("SELECT periodNumber FROM CurrentPeriod, User WHERE " + userId + " = CurrentPeriod.user_id;");
        ResultSet rs = stmt.executeQuery();
        
        Integer period = rs.getInt("periodNumber");
        
        stmt.close();
        connection.close();
        
        return period;
    }
    
    
    public static void saveCurrentPeriod(Integer newPeriodNumber, Integer userId) throws Exception {
        
        Connection connection = DriverManager.getConnection("jdbc:sqlite:users.db");
        
        PreparedStatement stmt = connection.prepareStatement("UPDATE CurrentPeriod SET periodNumber = " + newPeriodNumber + " WHERE user_id = " + userId + ";");
        stmt.executeUpdate();
        stmt.close();
  
        connection.close();
    }
}

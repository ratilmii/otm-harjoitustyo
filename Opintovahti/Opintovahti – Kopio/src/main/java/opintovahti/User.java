package opintovahti;

import java.util.logging.Level;
import java.util.logging.Logger;


public class User {
    private static String username;
    private static String passwordPlain;
    private static String hash;
    public static Integer id;
    
    
    public User(String username, String passwordPlain){
        this.username = username;
        this.passwordPlain = passwordPlain;
        this.hash = Passwords.hash(passwordPlain);
    }
    
    public String getName(){
        return username;
    }
    
    public String getHash(){
        return hash;
    }
    
    public static Integer getId() throws Exception {
        
        id = Databases.getUserId(username);
        
        System.out.println(id);
        return id;

    }
    
}

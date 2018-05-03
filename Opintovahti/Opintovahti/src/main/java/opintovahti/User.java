/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opintovahti;

/**
 *
 * @author Miika
 */
public class User {
    private static String username;
    private static String passwordPlain;
    
    public User(String username, String passwordPlain){
        this.username = username;
        this.passwordPlain = passwordPlain;
    }
    
    public static String getName(){
        return username;
    }
    
    public static byte[] getHashedPass(){
        char[] toBeHashed = passwordPlain.toCharArray();
        return Passwords.hash(toBeHashed, Passwords.getNextSalt());
        
    }
    
}

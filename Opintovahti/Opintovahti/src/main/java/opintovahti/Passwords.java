/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opintovahti;

import org.mindrot.jbcrypt.BCrypt;


public class Passwords {
    
    private Passwords(){
        }

    public static String hash(String password) {
        String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        return hash;
    }
    
}

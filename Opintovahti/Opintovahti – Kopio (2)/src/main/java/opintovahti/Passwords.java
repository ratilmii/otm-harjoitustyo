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

package opintovahti;


import org.mindrot.jbcrypt.BCrypt;


public class Passwords {
    
    private Passwords(){
        }

    public static String hash(String password) {
        return  BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean checkPw(String loginPassword, String hash) {
        return BCrypt.checkpw(loginPassword, hash);
    }
}

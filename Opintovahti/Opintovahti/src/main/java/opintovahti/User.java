package opintovahti;

public class User {
    private static String username;
    private static String passwordPlain;
    private static byte[] salt;
    
    public User(String username, String passwordPlain){
        this.username = username;
        this.passwordPlain = passwordPlain;
        this.salt = Passwords.getNextSalt();
    }
    
    public static String getName(){
        return username;
    }
    
    public static byte[] getSalt(){
        return salt;
    }
    
    public static byte[] getHash(){
        char[] toBeHashed = passwordPlain.toCharArray();
        return Passwords.hash(toBeHashed, salt);
        
    }
    
}

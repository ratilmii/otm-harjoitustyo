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
    
    public String getName(){
        return username;
    }
    
    public byte[] getSalt(){
        return salt;
    }
    
    public byte[] getHash(){
        char[] toBeHashed = passwordPlain.toCharArray();
        return Passwords.hash(toBeHashed, salt);
        
    }
    
}

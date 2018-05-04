package opintovahti;


public class User {
    private static String username;
    private static String passwordPlain;
    private static String hash;
    
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
    
}

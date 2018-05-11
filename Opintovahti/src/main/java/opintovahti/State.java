
package opintovahti;

import java.sql.SQLException;

public class State {
    
    public static State globalState;
    
    private Integer currentUser;
    private Integer currentPeriod;

    Databases db;
    
    public State() throws SQLException {
        db = new Databases();
    }
    
    public void setUserId(Integer userId) {
        this.currentUser = userId;
    }
    
    public Integer getUserId() {
        return this.currentUser;
    }
    
    public void setPeriodId(Integer periodId) {
        this.currentPeriod = periodId;
    }
    
    public Integer getPeriodId() {
        return this.currentPeriod;
    }

    public Databases getDatabase(){
        return this.db;
    }
    
}
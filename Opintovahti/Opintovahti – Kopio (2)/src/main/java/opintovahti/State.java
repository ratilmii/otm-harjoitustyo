
package opintovahti;


public class State {
    
    public static State globalState;
    
    private Integer currentUser;
    private Integer currentPeriod;
    
    
    
    public State() {
        
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
    
}

public class HealthGoal implements Goal{
    int minimumHealth;

    HealthGoal(int minimumHealth){
        this.minimumHealth = minimumHealth;
    }

    public boolean isFulfilled(Player player){
        if(player.getHealth >= this.minimumHealth ){
            return true;
        }else{
            return false;
        }
    }
}


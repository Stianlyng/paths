
public class GoldGoal implements Goal{
    int minimumGold;

    GoldGoal(int minimumGold){
        this.minimumGold = minimumGold;
    }

    public boolean isFulfilled(Player player){
        if(player.getGold >= this.minimumGold){
            return true;
        }else{
            return false;
        }
    }

}


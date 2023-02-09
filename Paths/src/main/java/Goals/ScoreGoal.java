
public class ScoreGoal implements Goal{
    int minimumPoints;

    ScoreGoal(int minimumPoints){
        this.minimumPoints = minimumPoints;
    }

    public boolean isFulfilled(Player player){
        if (player.getScore >= this.minimumPoints){
            return true;
        } else {
            return false;
        }
    }
}


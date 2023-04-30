package edu.ntnu.g60.models.actions;

import edu.ntnu.g60.models.player.Player;

public class ScoreAction implements Action{
    int score;

    public ScoreAction(int score){
        this.score = score;
    }

    public void execute(Player player){
        player.addScore(score);
    }

}
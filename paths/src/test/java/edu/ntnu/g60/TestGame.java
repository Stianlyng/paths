package edu.ntnu.g60;


public class TestGame {

    // main method
    public static void main(String[] args) {
  
      Player player = new Player("Bobby", 10, 10, 10);
      Passage openingPassage = new Passage("Level 1", "You are in a dark room.");
      Story story = new Story("The Dark World", openingPassage);
  
      Game game = new Game(player, story);
  
      System.out.println("Welcome to " + story.getTitle());
      System.out.println("You are " + player);
      System.out.println("You are in " + openingPassage.getTitle());
  
  
    }
  }
  
  
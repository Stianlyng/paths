public class TestPlayer {

  static Player player1 = new Player("Bjørn", 54, 0, 0);

  public static void main(String[] args) {
    System.out.println(player1);
    player1.addToInventory("Sword");
    player1.addScore(100);
    player1.addGold(100);
    player1.addHealth(100);
    System.out.println(player1);
  }

}

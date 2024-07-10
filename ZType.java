import tester.*;     
import java.util.Random;           
import javalib.worldimages.*;   
import javalib.funworld.*;      
import java.awt.Color;

//to represent a ZTypeGame
interface IZTypeGame {
  
  int SCREEN_HEIGHT = 500;
  int SCREEN_WIDTH = 500;
  double TICK_RATE = .9;
  String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
  //black rectangle for background in game
  WorldImage BLACK_RECT =
      new RectangleImage(SCREEN_WIDTH, SCREEN_HEIGHT, OutlineMode.SOLID, Color.BLACK);
  //background stars
  WorldImage STARS1 =
      new StarImage(Math.random() * 8, 8, 2, OutlineMode.SOLID, Color.BLUE.brighter());
  WorldImage STARS2 =
      new StarImage(Math.random() * 10, 8, 2, OutlineMode.SOLID, Color.WHITE.brighter());
  //background stars for level 2
  WorldImage STARS3 =
      new StarImage(Math.random() * 8, 8, 2, OutlineMode.SOLID, Color.RED);
  WorldImage STARS4 =
      new StarImage(Math.random() * 10, 8, 2, OutlineMode.SOLID, Color.PINK);
  //background stars for level 3
  WorldImage STARS5 =
      new StarImage(Math.random() * 8, 8, 2, OutlineMode.SOLID, Color.GREEN);
  WorldImage STARS6 =
      new StarImage(Math.random() * 10, 8, 2, OutlineMode.SOLID, Color.ORANGE);
  //text that appears on end game screen
  WorldImage END_TEXT = 
      new TextImage("GAME OVER", 60, FontStyle.BOLD, Color.BLUE);
  //test that appears once game is completed
  WorldImage FINISH_TEXT =
      new TextImage("YOU WON", 60, FontStyle.BOLD, Color.BLUE);
  //spaceship image
  WorldImage SHIP =
      new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
          new HexagonImage(10, OutlineMode.SOLID, Color.GRAY),
          0, -10,
      new OverlayOffsetAlign(AlignModeX.LEFT, AlignModeY.BOTTOM,
          new HexagonImage(10, OutlineMode.SOLID, Color.GRAY),
          -20, 0,
      new OverlayOffsetAlign(AlignModeX.RIGHT, AlignModeY.BOTTOM,
          new HexagonImage(10, OutlineMode.SOLID, Color.GRAY),
          10, -10,
          new RectangleImage(20, 40, OutlineMode.SOLID, Color.ORANGE))));
     
  //random for tests
  Random RAND_COORD = new Random(IZTypeGame.SCREEN_WIDTH);
  int RAND_COORD_X = RAND_COORD.nextInt(IZTypeGame.SCREEN_WIDTH);
  int RAND_COORD_Y = RAND_COORD.nextInt(IZTypeGame.SCREEN_HEIGHT);
}

//to represent a ZType World with necessary fields and
//methods to animate a list of words on a screen
class ZTypeWorld extends World {

  ILoWord words;
  int ticks;
  /*TEMPLATE
   * Fields:
   * ... this.words ...                     -- ILoWord
   * Methods:
   * ... this.makeScene() ...               -- WorldScene
   * Methods for fields:
   * ... this.words.draw(WorldScene) ...    -- WorldScene
   */
  
  ZTypeWorld(ILoWord words, int ticks) {
    this.words = words;
    this.ticks = ticks;
  }
  
  //draw score on screen
  public WorldImage drawScore() {
    return new TextImage(
        "Score: " + String.valueOf(this.words.drawScoreHelp()), 24, FontStyle.BOLD, Color.RED);
  }
  
  //draw words on background
  public WorldScene makeScene() {
    if (this.words.drawScoreHelp() < 10 && this.words.drawScoreHelp() >= 0) {
      return this.words.draw(new WorldScene(IZTypeGame.SCREEN_WIDTH, IZTypeGame.SCREEN_HEIGHT)
          .placeImageXY(IZTypeGame.BLACK_RECT, IZTypeGame.SCREEN_WIDTH / 2, 
              IZTypeGame.SCREEN_HEIGHT / 2)
          .placeImageXY(IZTypeGame.STARS1, IZTypeGame.SCREEN_WIDTH / 16, 
              IZTypeGame.SCREEN_HEIGHT / 16)
          .placeImageXY(IZTypeGame.STARS2, IZTypeGame.SCREEN_WIDTH / 3, 
              IZTypeGame.SCREEN_HEIGHT / 5)
          .placeImageXY(IZTypeGame.STARS2, IZTypeGame.SCREEN_WIDTH / 8, 
              IZTypeGame.SCREEN_HEIGHT / 3)
          .placeImageXY(IZTypeGame.STARS2, IZTypeGame.SCREEN_WIDTH / 4, 
              IZTypeGame.SCREEN_HEIGHT / 7)
          .placeImageXY(IZTypeGame.STARS1, 
              IZTypeGame.SCREEN_WIDTH / 16 + 100, IZTypeGame.SCREEN_HEIGHT / 16)
          .placeImageXY(IZTypeGame.STARS1, 
              IZTypeGame.SCREEN_WIDTH / 3 + 100, IZTypeGame.SCREEN_HEIGHT / 5)
          .placeImageXY(IZTypeGame.STARS1, 
              IZTypeGame.SCREEN_WIDTH / 8 + 100, IZTypeGame.SCREEN_HEIGHT / 3)
          .placeImageXY(IZTypeGame.STARS2, 
              IZTypeGame.SCREEN_WIDTH / 4 + 100, IZTypeGame.SCREEN_HEIGHT / 7)
          .placeImageXY(IZTypeGame.STARS2, 
              IZTypeGame.SCREEN_WIDTH / 16 + 200, IZTypeGame.SCREEN_HEIGHT / 16)
          .placeImageXY(IZTypeGame.STARS1, 
              IZTypeGame.SCREEN_WIDTH / 3 + 200, IZTypeGame.SCREEN_HEIGHT / 5)
          .placeImageXY(IZTypeGame.STARS1, 
              IZTypeGame.SCREEN_WIDTH / 8 + 200, IZTypeGame.SCREEN_HEIGHT / 3)
          .placeImageXY(IZTypeGame.STARS1, 
              IZTypeGame.SCREEN_WIDTH / 4 + 200, IZTypeGame.SCREEN_HEIGHT / 7)
          .placeImageXY(IZTypeGame.STARS2, 
              IZTypeGame.SCREEN_WIDTH / 16, IZTypeGame.SCREEN_HEIGHT / 16 + 100)
          .placeImageXY(IZTypeGame.STARS2, 
              IZTypeGame.SCREEN_WIDTH / 3, IZTypeGame.SCREEN_HEIGHT / 5 + 100)
          .placeImageXY(IZTypeGame.STARS2, 
              IZTypeGame.SCREEN_WIDTH / 8, IZTypeGame.SCREEN_HEIGHT / 3 + 100)
          .placeImageXY(IZTypeGame.STARS1, 
              IZTypeGame.SCREEN_WIDTH / 4, IZTypeGame.SCREEN_HEIGHT / 7 + 100)
          .placeImageXY(IZTypeGame.STARS2, 
              IZTypeGame.SCREEN_WIDTH / 16 + 100, IZTypeGame.SCREEN_HEIGHT / 16 - 200)
          .placeImageXY(IZTypeGame.STARS1, 
              IZTypeGame.SCREEN_WIDTH / 3 + 100, IZTypeGame.SCREEN_HEIGHT / 5 + 200)
          .placeImageXY(IZTypeGame.STARS1, 
              IZTypeGame.SCREEN_WIDTH / 8 + 100, IZTypeGame.SCREEN_HEIGHT / 3 + 200)
          .placeImageXY(IZTypeGame.STARS2, 
              IZTypeGame.SCREEN_WIDTH / 4 + 100, IZTypeGame.SCREEN_HEIGHT / 7 - 200)
          .placeImageXY(IZTypeGame.STARS1, 
              IZTypeGame.SCREEN_WIDTH / 16 + 200, IZTypeGame.SCREEN_HEIGHT / 16 + 300)
          .placeImageXY(IZTypeGame.STARS1, 
              IZTypeGame.SCREEN_WIDTH / 3 + 200, IZTypeGame.SCREEN_HEIGHT / 5 + 300)
          .placeImageXY(IZTypeGame.STARS2, 
              IZTypeGame.SCREEN_WIDTH / 8 + 200, IZTypeGame.SCREEN_HEIGHT / 3 + 300)
          .placeImageXY(IZTypeGame.STARS2, 
              IZTypeGame.SCREEN_WIDTH / 4 + 200, IZTypeGame.SCREEN_HEIGHT / 7 + 300)
          .placeImageXY(IZTypeGame.STARS2, 
              IZTypeGame.SCREEN_WIDTH - 50, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 10)
          .placeImageXY(IZTypeGame.STARS1, 
              IZTypeGame.SCREEN_WIDTH - 70, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 50)
          .placeImageXY(IZTypeGame.STARS1, 
              IZTypeGame.SCREEN_WIDTH - 50, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 80)
          .placeImageXY(IZTypeGame.STARS2, 
              IZTypeGame.SCREEN_WIDTH - 40, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 100)
          .placeImageXY(IZTypeGame.STARS2, 
              IZTypeGame.SCREEN_WIDTH - 50, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 140)
          .placeImageXY(IZTypeGame.STARS2, 
              IZTypeGame.SCREEN_WIDTH - 70, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 170)
          //place image of score
          .placeImageXY(this.drawScore(), 
              IZTypeGame.SCREEN_WIDTH - IZTypeGame.SCREEN_WIDTH + 70 ,
              IZTypeGame.SCREEN_HEIGHT - 25))
          //place image of ship
          .placeImageXY(IZTypeGame.SHIP, IZTypeGame.SCREEN_WIDTH / 2, 
              IZTypeGame.SCREEN_HEIGHT - 40)
          //place image of level
          .placeImageXY(new TextImage("Level 1", 24, FontStyle.BOLD, Color.RED),
              IZTypeGame.SCREEN_WIDTH - IZTypeGame.SCREEN_WIDTH + 420 ,
              IZTypeGame.SCREEN_HEIGHT - 25);
    }
    else if (this.words.drawScoreHelp() < 20 && this.words.drawScoreHelp() >= 10) {
      return this.words.draw(new WorldScene(IZTypeGame.SCREEN_WIDTH, 
          IZTypeGame.SCREEN_HEIGHT)
          .placeImageXY(IZTypeGame.BLACK_RECT, 
              IZTypeGame.SCREEN_WIDTH / 2, IZTypeGame.SCREEN_HEIGHT / 2)
          .placeImageXY(IZTypeGame.STARS3, 
              IZTypeGame.SCREEN_WIDTH / 16, IZTypeGame.SCREEN_HEIGHT / 16)
          .placeImageXY(IZTypeGame.STARS3, 
              IZTypeGame.SCREEN_WIDTH / 3, IZTypeGame.SCREEN_HEIGHT / 5)
          .placeImageXY(IZTypeGame.STARS4, 
              IZTypeGame.SCREEN_WIDTH / 8, IZTypeGame.SCREEN_HEIGHT / 3)
          .placeImageXY(IZTypeGame.STARS4, 
              IZTypeGame.SCREEN_WIDTH / 4, IZTypeGame.SCREEN_HEIGHT / 7)
          .placeImageXY(IZTypeGame.STARS3, 
              IZTypeGame.SCREEN_WIDTH / 16 + 100, IZTypeGame.SCREEN_HEIGHT / 16)
          .placeImageXY(IZTypeGame.STARS3, 
              IZTypeGame.SCREEN_WIDTH / 3 + 100, IZTypeGame.SCREEN_HEIGHT / 5)
          .placeImageXY(IZTypeGame.STARS3, 
              IZTypeGame.SCREEN_WIDTH / 8 + 100, IZTypeGame.SCREEN_HEIGHT / 3)
          .placeImageXY(IZTypeGame.STARS3, 
              IZTypeGame.SCREEN_WIDTH / 4 + 100, IZTypeGame.SCREEN_HEIGHT / 7)
          .placeImageXY(IZTypeGame.STARS4, 
              IZTypeGame.SCREEN_WIDTH / 16 + 200, IZTypeGame.SCREEN_HEIGHT / 16)
          .placeImageXY(IZTypeGame.STARS4, 
              IZTypeGame.SCREEN_WIDTH / 3 + 200, IZTypeGame.SCREEN_HEIGHT / 5)
          .placeImageXY(IZTypeGame.STARS4, 
              IZTypeGame.SCREEN_WIDTH / 8 + 200, IZTypeGame.SCREEN_HEIGHT / 3)
          .placeImageXY(IZTypeGame.STARS3, 
              IZTypeGame.SCREEN_WIDTH / 4 + 200, IZTypeGame.SCREEN_HEIGHT / 7)
          .placeImageXY(IZTypeGame.STARS3, 
              IZTypeGame.SCREEN_WIDTH / 16, IZTypeGame.SCREEN_HEIGHT / 16 + 100)
          .placeImageXY(IZTypeGame.STARS4, 
              IZTypeGame.SCREEN_WIDTH / 3, IZTypeGame.SCREEN_HEIGHT / 5 + 100)
          .placeImageXY(IZTypeGame.STARS3, 
              IZTypeGame.SCREEN_WIDTH / 8, IZTypeGame.SCREEN_HEIGHT / 3 + 100)
          .placeImageXY(IZTypeGame.STARS4, 
              IZTypeGame.SCREEN_WIDTH / 4, IZTypeGame.SCREEN_HEIGHT / 7 + 100)
          .placeImageXY(IZTypeGame.STARS3, 
              IZTypeGame.SCREEN_WIDTH / 16 + 100, IZTypeGame.SCREEN_HEIGHT / 16 - 200)
          .placeImageXY(IZTypeGame.STARS4, 
              IZTypeGame.SCREEN_WIDTH / 3 + 100, IZTypeGame.SCREEN_HEIGHT / 5 + 200)
          .placeImageXY(IZTypeGame.STARS4, 
              IZTypeGame.SCREEN_WIDTH / 8 + 100, IZTypeGame.SCREEN_HEIGHT / 3 + 200)
          .placeImageXY(IZTypeGame.STARS3, 
              IZTypeGame.SCREEN_WIDTH / 4 + 100, IZTypeGame.SCREEN_HEIGHT / 7 - 200)
          .placeImageXY(IZTypeGame.STARS4, 
              IZTypeGame.SCREEN_WIDTH / 16 + 200, IZTypeGame.SCREEN_HEIGHT / 16 + 300)
          .placeImageXY(IZTypeGame.STARS3, 
              IZTypeGame.SCREEN_WIDTH / 3 + 200, IZTypeGame.SCREEN_HEIGHT / 5 + 300)
          .placeImageXY(IZTypeGame.STARS3, 
              IZTypeGame.SCREEN_WIDTH / 8 + 200, IZTypeGame.SCREEN_HEIGHT / 3 + 300)
          .placeImageXY(IZTypeGame.STARS2, 
              IZTypeGame.SCREEN_WIDTH / 4 + 200, IZTypeGame.SCREEN_HEIGHT / 7 + 300)
          .placeImageXY(IZTypeGame.STARS4, 
              IZTypeGame.SCREEN_WIDTH - 50, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 10)
          .placeImageXY(IZTypeGame.STARS4, 
              IZTypeGame.SCREEN_WIDTH - 70, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 50)
          .placeImageXY(IZTypeGame.STARS4, 
              IZTypeGame.SCREEN_WIDTH - 50, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 80)
          .placeImageXY(IZTypeGame.STARS4, 
              IZTypeGame.SCREEN_WIDTH - 40, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 100)
          .placeImageXY(IZTypeGame.STARS3, 
              IZTypeGame.SCREEN_WIDTH - 50, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 140)
          .placeImageXY(IZTypeGame.STARS4, 
              IZTypeGame.SCREEN_WIDTH - 70, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 170)
          //place image of score
          .placeImageXY(this.drawScore(), 
              IZTypeGame.SCREEN_WIDTH 
              - 
              IZTypeGame.SCREEN_WIDTH + 70 , IZTypeGame.SCREEN_HEIGHT - 25))
          //place image of ship
          .placeImageXY(IZTypeGame.SHIP, IZTypeGame.SCREEN_WIDTH / 2, 
              IZTypeGame.SCREEN_HEIGHT - 40)
          //place image of level
          .placeImageXY(new TextImage("Level 2", 24, FontStyle.BOLD, Color.RED),
              IZTypeGame.SCREEN_WIDTH - IZTypeGame.SCREEN_WIDTH + 420 ,
              IZTypeGame.SCREEN_HEIGHT - 25);
    }
    else if (this.words.drawScoreHelp() >= 20 && this.words.drawScoreHelp() < 25) {
      return this.words.draw(new WorldScene(IZTypeGame.SCREEN_WIDTH, IZTypeGame.SCREEN_HEIGHT)
          .placeImageXY(IZTypeGame.BLACK_RECT, IZTypeGame.SCREEN_WIDTH / 2, 
              IZTypeGame.SCREEN_HEIGHT / 2)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 16, IZTypeGame.SCREEN_HEIGHT / 16)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 3, IZTypeGame.SCREEN_HEIGHT / 5)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 8, IZTypeGame.SCREEN_HEIGHT / 3)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 4, IZTypeGame.SCREEN_HEIGHT / 7)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 16 + 100, IZTypeGame.SCREEN_HEIGHT / 16)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 3 + 100, IZTypeGame.SCREEN_HEIGHT / 5)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 8 + 100, IZTypeGame.SCREEN_HEIGHT / 3)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 4 + 100, IZTypeGame.SCREEN_HEIGHT / 7)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 16 + 200, IZTypeGame.SCREEN_HEIGHT / 16)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 3 + 200, IZTypeGame.SCREEN_HEIGHT / 5)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 8 + 200, IZTypeGame.SCREEN_HEIGHT / 3)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 4 + 200, IZTypeGame.SCREEN_HEIGHT / 7)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 16, IZTypeGame.SCREEN_HEIGHT / 16 + 100)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 3, IZTypeGame.SCREEN_HEIGHT / 5 + 100)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 8, IZTypeGame.SCREEN_HEIGHT / 3 + 100)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 4, IZTypeGame.SCREEN_HEIGHT / 7 + 100)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 16 + 100, IZTypeGame.SCREEN_HEIGHT / 16 - 200)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 3 + 100, IZTypeGame.SCREEN_HEIGHT / 5 + 200)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 8 + 100, IZTypeGame.SCREEN_HEIGHT / 3 + 200)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 4 + 100, IZTypeGame.SCREEN_HEIGHT / 7 - 200)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 16 + 200, IZTypeGame.SCREEN_HEIGHT / 16 + 300)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 3 + 200, IZTypeGame.SCREEN_HEIGHT / 5 + 300)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 8 + 200, IZTypeGame.SCREEN_HEIGHT / 3 + 300)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 4 + 200, IZTypeGame.SCREEN_HEIGHT / 7 + 300)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH - 50,
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 10)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH - 70, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 50)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH - 50, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 80)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH - 40, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 100)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH - 50, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 140)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH - 70, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 170)
          //place image of score
          .placeImageXY(this.drawScore(), 
              IZTypeGame.SCREEN_WIDTH 
              - 
              IZTypeGame.SCREEN_WIDTH + 70 , IZTypeGame.SCREEN_HEIGHT - 25))
          //place image of ship
          .placeImageXY(IZTypeGame.SHIP, IZTypeGame.SCREEN_WIDTH / 2, 
              IZTypeGame.SCREEN_HEIGHT - 40)
          //place image of level
          .placeImageXY(new TextImage("Level 3", 24, FontStyle.BOLD, Color.RED),
              IZTypeGame.SCREEN_WIDTH - IZTypeGame.SCREEN_WIDTH + 420 ,
              IZTypeGame.SCREEN_HEIGHT - 25);
    }
    else {
      return new WorldScene(IZTypeGame.SCREEN_WIDTH, IZTypeGame.SCREEN_HEIGHT)
          .placeImageXY(IZTypeGame.BLACK_RECT, IZTypeGame.SCREEN_WIDTH / 2, 
              IZTypeGame.SCREEN_HEIGHT / 2)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 16, IZTypeGame.SCREEN_HEIGHT / 16)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 3, IZTypeGame.SCREEN_HEIGHT / 5)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 8, IZTypeGame.SCREEN_HEIGHT / 3)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 4, IZTypeGame.SCREEN_HEIGHT / 7)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 16 + 100, IZTypeGame.SCREEN_HEIGHT / 16)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 3 + 100, IZTypeGame.SCREEN_HEIGHT / 5)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 8 + 100, IZTypeGame.SCREEN_HEIGHT / 3)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 4 + 100, IZTypeGame.SCREEN_HEIGHT / 7)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 16 + 200, IZTypeGame.SCREEN_HEIGHT / 16)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 3 + 200, IZTypeGame.SCREEN_HEIGHT / 5)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 8 + 200, IZTypeGame.SCREEN_HEIGHT / 3)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 4 + 200, IZTypeGame.SCREEN_HEIGHT / 7)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 16, IZTypeGame.SCREEN_HEIGHT / 16 + 100)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 3, IZTypeGame.SCREEN_HEIGHT / 5 + 100)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 8, IZTypeGame.SCREEN_HEIGHT / 3 + 100)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 4, IZTypeGame.SCREEN_HEIGHT / 7 + 100)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 16 + 100, IZTypeGame.SCREEN_HEIGHT / 16 - 200)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 3 + 100, IZTypeGame.SCREEN_HEIGHT / 5 + 200)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 8 + 100, IZTypeGame.SCREEN_HEIGHT / 3 + 200)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 4 + 100, IZTypeGame.SCREEN_HEIGHT / 7 - 200)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 16 + 200, IZTypeGame.SCREEN_HEIGHT / 16 + 300)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH / 3 + 200, IZTypeGame.SCREEN_HEIGHT / 5 + 300)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 8 + 200, IZTypeGame.SCREEN_HEIGHT / 3 + 300)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH / 4 + 200, IZTypeGame.SCREEN_HEIGHT / 7 + 300)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH - 50, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 10)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH - 70, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 50)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH - 50, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 80)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH - 40, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 100)
          .placeImageXY(IZTypeGame.STARS5, 
              IZTypeGame.SCREEN_WIDTH - 50, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 140)
          .placeImageXY(IZTypeGame.STARS6, 
              IZTypeGame.SCREEN_WIDTH - 70, 
              IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 170)
          //place image of score
          .placeImageXY(this.drawScore(), 
              IZTypeGame.SCREEN_WIDTH 
              - IZTypeGame.SCREEN_WIDTH + 70 , IZTypeGame.SCREEN_HEIGHT - 25)
          //place image of ship
          .placeImageXY(IZTypeGame.SHIP, IZTypeGame.SCREEN_WIDTH / 2, 
              IZTypeGame.SCREEN_HEIGHT - 40)
          //place image of level
          .placeImageXY(new TextImage("Level 3", 24, FontStyle.BOLD, Color.RED),
              IZTypeGame.SCREEN_WIDTH - IZTypeGame.SCREEN_WIDTH + 420 ,
              IZTypeGame.SCREEN_HEIGHT - 25)
          .placeImageXY(IZTypeGame.FINISH_TEXT,
              IZTypeGame.SCREEN_WIDTH / 2, IZTypeGame.SCREEN_HEIGHT / 2); 
    }
  }
  
  //move the words on the screen every tick, moves words down the screen
  //adds words periodically when user is not typing
  public World onTick() {
    if (this.ticks % 7 == 0) {
      this.ticks++;
      return new ZTypeWorld(this.words.move(), this.ticks);
    }
    else {
      this.ticks++;
      return new ZTypeWorld(this.words.addWord().move(), this.ticks);
    }
  }

  //onTick method for testing
  public World onTickForTesting() {
    if (this.ticks % 7 == 0) {
      this.ticks++;
      return new ZTypeWorld(this.words.move(), this.ticks);
    }
    else {
      this.ticks++;
      return new ZTypeWorld(this.words.addWordForTesting().move(), this.ticks);
    }
  }
  
  //handle the key event if key pressed matches the first letter of any word on screen
  public ZTypeWorld onKeyEvent(String key) {
    return new ZTypeWorld(this.words.checkAndReduce(key), 0);
  }
  
  //restarts the game when the user presses mouse
  public ZTypeWorld onMouseClicked(Posn pos) {
    
    /*TEMPLATE
     * Everything in class template plus..
     * Fields of parameters
     * ... pos.x ...    -- int
     * ... pox.y ...    -- int
     */
    
    if (pos.x >= 0 && pos.y >= 0) {
      return new ZTypeWorld(new ConsLoWord(new InactiveWord(new Utils().randomWord()), 
          new ConsLoWord(new InactiveWord(new Utils().randomWord()), 
              new ConsLoWord(new InactiveWord(new Utils().randomWord()),
                  new ConsLoWord(
                      new InactiveWord(new Utils().randomWord()), new MtLoWord())))), 0);
    }
    else {
      return this;
    }
  }
  
  //restarts the game when the user presses mouse
  public ZTypeWorld onMouseClickedForTesting(Posn pos) {
    
    /*TEMPLATE
     * Everything in class template plus..
     * Fields of parameters
     * ... pos.x ...    -- int
     * ... pox.y ...    -- int
     */
    
    if (pos.x >= 0 && pos.y >= 0) {
      return new ZTypeWorld(
          new ConsLoWord(new InactiveWord(new Utils(new Random(5)).randomWord(), 
          IZTypeGame.RAND_COORD_X, IZTypeGame.RAND_COORD_Y), 
          new ConsLoWord(new InactiveWord(new Utils(new Random(6)).randomWord(), 
              IZTypeGame.RAND_COORD_X, IZTypeGame.RAND_COORD_Y), 
              new ConsLoWord(new InactiveWord(new Utils(new Random(7)).randomWord(), 
                  IZTypeGame.RAND_COORD_X, IZTypeGame.RAND_COORD_Y),
                  new ConsLoWord(new InactiveWord(new Utils(new Random(8)).randomWord(), 
                      IZTypeGame.RAND_COORD_X, IZTypeGame.RAND_COORD_Y), new MtLoWord())))), 0);
    }
    else {
      return this;
    }
  }
  
  //ends game if word reaches bottom of screen
  public WorldEnd worldEnds() {
    if (this.words.reachBottom()) {
      return new WorldEnd(true, this.endScreen());
    }
    else if (this.words.drawScoreHelp() == 25) {
      return new WorldEnd(
          true, new WorldScene(IZTypeGame.SCREEN_WIDTH, IZTypeGame.SCREEN_HEIGHT)
          .placeImageXY(IZTypeGame.BLACK_RECT, 
              IZTypeGame.SCREEN_WIDTH / 2, IZTypeGame.SCREEN_HEIGHT / 2)
          .placeImageXY(IZTypeGame.FINISH_TEXT, 
              IZTypeGame.SCREEN_WIDTH / 2, IZTypeGame.SCREEN_HEIGHT / 2));
    }
    else {
      return new WorldEnd(false, this.makeScene());
    }
  }
  
  //scene that displays when game is ended
  public WorldScene endScreen() {
    return new WorldScene(IZTypeGame.SCREEN_WIDTH, IZTypeGame.SCREEN_HEIGHT)
        .placeImageXY(IZTypeGame.BLACK_RECT, 
            IZTypeGame.SCREEN_WIDTH / 2, IZTypeGame.SCREEN_HEIGHT / 2)
        .placeImageXY(IZTypeGame.END_TEXT, 
            IZTypeGame.SCREEN_WIDTH / 2, IZTypeGame.SCREEN_HEIGHT / 2);
  }
}

//to represent a list of words
interface ILoWord {
  
  //draw this list on words onto the WorldScene,
  //passes in accumulator of image of scene so far
  WorldScene draw(WorldScene acc);
  
  //move the words in list down the screen
  ILoWord move();
  
  //add a random word
  ILoWord addWord();
  
  //addWord method for testing, uses seeded
  //random object
  ILoWord addWordForTesting();
  
  //takes in a string of length 1 and produces list
  //of words where any !active! words in the list
  //are reduced by removing the first letter only
  //if given string matches its first letter 
  //!case sensitive!
  ILoWord checkAndReduce(String letterRemover);
  
  //determines if word in list has reached bottom of screen
  boolean reachBottom();
  
  //determines if a word in list is an empty string,
  //if so add to score
  int drawScoreHelp();
}

//to represent an empty list of words
class MtLoWord implements ILoWord {
  
  /*TEMPLATE
   * Methods:
   * ... this.draw(WorldScene) ...        -- WorldScene
   * ... this.move() ...                  -- ILoWord
   * ... this.addWord() ...               -- ILoWord
   * ... this.addWordForTesting ...       -- ILoWord
   * ... this.checkAndReduce(String) ...  -- ILoWord
   * ... this.reachBottom() ...           -- boolean
   * ... this.drawScoreHelp ...           -- int
   */
  
  //draw this list on words onto the WorldScene,
  //passes in accumulator of image of scene so far
  public WorldScene draw(WorldScene acc) {
    
    /*TEMPLATE
     * Everything in class template plus ...
     * Methods on parameters:
     * ... acc.draw(WorldScene) ...    -- WorldScene
     */
    
    return acc;
  }
  
  //move the words in list down the screen
  public ILoWord move() {
    
    return this;
    
  }
  
  //add a random word
  public ILoWord addWord() {
    return new ConsLoWord(new InactiveWord(new Utils().randomWord()), this);
  }
  
  //addWord method for testing, uses seeded
  //random object
  public ILoWord addWordForTesting() {
    return new ConsLoWord(new InactiveWord(new Utils(new Random(20)).randomWord(), 
        IZTypeGame.RAND_COORD_X, IZTypeGame.RAND_COORD_Y), this);
  }
  
  //takes in a string of length 1 and produces list
  //of words where any !active! words in the list
  //are reduced by removing the first letter only
  //if given string matches its first letter 
  //!case sensitive! For empty lists it just returns
  //empty list
  public ILoWord checkAndReduce(String letterRemover) {
    return this;
  }
  
  //determines if word in list has reached bottom of screen
  public boolean reachBottom() {
    return false;
  }
  
  //determines if a word in list is an empty string,
  //if so add to score
  public int drawScoreHelp() {
    return 0;
  }
  
}

//to represent a non-empty list of words
class ConsLoWord implements ILoWord {
  IWord first;
  ILoWord rest;
  
  ConsLoWord(IWord first, ILoWord rest) {
    this.first = first;
    this.rest = rest;
  }
  
  /*TEMPLATE
   * Fields:
   * ... this.first ...                                        -- IWord
   * ... this.rest ...                                         -- ILoWord
   * * Methods:
   * ... this.draw(WorldScene) ...                             -- WorldScene
   * ... this.move() ...                                       -- ILoWord
   * ... this.addWord() ...                                    -- ILoWord
   * ... this.addWordForTesting ...                            -- ILoWord
   * ... this.checkAndReduce(String) ...                       -- ILoWord
   * ... this.reachBottom() ...                                -- boolean
   * ... this.drawScoreHelp ...                                -- int
   * Methods on fields:
   * ... this.first.draw() ...                                 -- WorldImage
   * ... this.first.drawWordOnSceneHelp(WorldScene) ...        -- WorldScene
   * ... this.first.move() ...                                 -- IWord
   * ... this.first.checkAndReduceHelp(String) ...             -- IWord
   * ... this.first.reachBottomHelper() ...                    -- Boolean
   * ... this.first.scoreWord() ...                            -- int
   * ... this.first.rest.draw() ...                            -- WorldImage
   * ... this.rest.draw(WorldScene) ...                        -- WorldScene
   * ... this.rest.move() ...                                  -- ILoWord
   * ... this.rest.addWord() ...                               -- ILoWord
   * ... this.rest.addWordForTesting ...                       -- ILoWord
   * ... this.rest.checkAndReduce(String) ...                  -- ILoWord
   * ... this.rest.reachBottom() ...                           -- boolean
   * ... this.rest.drawScoreHelp ...                           -- int
   */
  
  //draw this list on words onto the WorldScene,
  //passes in accumulator of image of scene so far
  public WorldScene draw(WorldScene acc) {
    
    /*TEMPLATE
     * Everything in class template plus ...
     * Methods on parameters:
     * ... acc.draw(WorldScene) ...             -- WorldScene
     */
    
    return this.rest.draw(this.first.drawWordOnSceneHelp(acc));
  }
  
  //move the words in list down the screen
  public ILoWord move() {
    return new ConsLoWord(this.first.move(), this.rest.move());
  }
  
  
  //add a random word
  public ILoWord addWord() {
    return new ConsLoWord(new InactiveWord(new Utils().randomWord()), this);
  }
  
  //addWord method for testing, uses seeded
  //random object
  public ILoWord addWordForTesting() {
    return new ConsLoWord(new InactiveWord(new Utils(new Random(21)).randomWord(), 
        IZTypeGame.RAND_COORD_X, IZTypeGame.RAND_COORD_Y), this);
  }
  
  //takes in a string of length 1 and produces list
  //of words where any !active! words in the list
  //are reduced by removing the first letter only
  //if given string matches its first letter 
  //!case sensitive!
  public ILoWord checkAndReduce(String letterRemover) {
    return new ConsLoWord(this.first.checkAndReduceHelp(letterRemover),
        this.rest.checkAndReduce(letterRemover));
  }
  
  //determines if word in list has reached bottom of screen
  public boolean reachBottom() {
    return this.first.reachBottomHelper()
        || this.rest.reachBottom();
  }
  
  //determines if a word in list is an empty string,
  //if so add to score
  public int drawScoreHelp() {
    return this.first.scoreWord() 
        + this.rest.drawScoreHelp();
  }
  
}

//to represent a word
interface IWord {
  
  //draw a word on scene
  WorldImage draw();
  
  //helper method to place background image of word 
  WorldScene drawWordOnSceneHelp(WorldScene acc);
  
  //to move individual words
  IWord move();
  
  //helper for checkAndReduce method, is able to access
  //substring of word and determine if letterRemover
  //applies to the first character of word
  IWord checkAndReduceHelp(String letterRemover);
  
  //helper for reachBottom, determines if single
  //word has reached bottom
  boolean reachBottomHelper();
  
  //determine if a word is an empty string
  //if so add to score 
  int scoreWord();
}

//to represent an Active Word, which
//is a word that the user is currently typing
class ActiveWord implements IWord {
  String word;
  int x;
  int y;
  Random rand = new Random();
  int randX = rand.nextInt(IZTypeGame.SCREEN_WIDTH);
  int randY = rand.nextInt(IZTypeGame.SCREEN_HEIGHT);
  
  //constructor with random coordinates
  ActiveWord(String word) {
    //this.rand = new Random();
    this.word = word;
    
    if (this.randX <= 50) {
      this.x = randX + 50;
    }
    else if (this.randX >= IZTypeGame.SCREEN_WIDTH) {
      this.x = randX - 100;
    }
    else {
      this.x = randX;
    }
    
    if (this.randY <= 20) {
      this.y = randY + 20;
    }
    else if (this.randY >= IZTypeGame.SCREEN_HEIGHT - 200) {
      this.y = randY - 200;
    }
    else {
      this.y = randY;
    }
  }
  
  //constructor with exact coordinates for tests
  ActiveWord(String word, int x, int y) {
    this.word = word;
    this.x = x;
    this.y = y;
    this.rand = IZTypeGame.RAND_COORD;
    this.randX = IZTypeGame.RAND_COORD_X;
    this.randY = IZTypeGame.RAND_COORD_Y;
  }
  
  
  /*TEMPLATE
   * Fields:
   * ... this.word ...                              -- String
   * ... this.x ...                                 -- int
   * ... this.y ...                                 -- int
   * ... this.random ...                            -- Random
   * ... this.randX ...                             -- int
   * ... this.randY ...                             -- int
   * Methods:
   * ... this.draw() ...                            -- WorldImage
   * ... this.drawWordOnSceneHelp(WorldScene) ...   -- WorldScene
   * ... this.move() ...                            -- IWord
   * ... this.checkAndReduceHelp(String) ...        -- IWord
   * ... this.reachBottomHelper() ...               -- Boolean
   * ... this.scoreWord() ...                       -- int
   */
  
  //helper for draw method, draws each word according
  //to string and type, active words are red and inactive
  //words are cyan
  public WorldImage draw() {
    return new TextImage(this.word, 30, FontStyle.BOLD, Color.RED);
  }
  
  //helper for drawOnScene, access word string and location to
  //draw correct text image
  public WorldScene drawWordOnSceneHelp(WorldScene acc) {
 
    /*
     * TEMPLATE:
     * Everything in class template, plus
     * Methods on parameters:
     * ... acc.draw(WorldScene) ...       -- WorldScene
     */
    
    return acc.placeImageXY(new RectangleImage(this.word.length() * 20, 50, 
        OutlineMode.SOLID, Color.DARK_GRAY), this.x, this.y)
        .placeImageXY(this.draw(), this.x, this.y);
  }
  
  //move the words in list down the screen
  public IWord move() {
    if (!this.word.equals("")) {
      return new ActiveWord(this.word, this.x, this.y + 10);
    }
    else {
      return this;
    }
  }
  
  //helper for checkAndReduce method, accesses first character of word substring,
  //determines if the passed in letterRemover is equal, and then reduces word
  //if it applies
  public IWord checkAndReduceHelp(String letterRemover) {
    if (this.word.length() == 0) {
      return this;
    }
    else if (this.word.substring(0,1).equals(letterRemover)) {
      return new ActiveWord(this.word.substring(1, this.word.length()), this.x, this.y);
    }
    
    else {
      return this;
    }
  }
  
  //helper for reachBottom, determines if single
  //word has reached bottom
  public boolean reachBottomHelper() {
    return this.y >= IZTypeGame.SCREEN_HEIGHT - 30;
  }
  
  //determine if a word is an empty string
  //if so add to score 
  public int scoreWord() {
    if (this.word.equals("")) {
      return 1;
    }
    else {
      return 0;
    }
  }
}

//to represent an Inactive word, which is a
//word that the user has not typed yet
class InactiveWord implements IWord {
  String word;
  int x;
  int y;
  Random rand = new Random();
  int randX = rand.nextInt(IZTypeGame.SCREEN_WIDTH);
  int randY = rand.nextInt(IZTypeGame.SCREEN_HEIGHT);
  
  //constructor with random coordinates
  InactiveWord(String word) {
    this.word = word;
    
    if (this.randX <= 50) {
      this.x = randX + 50;
    }
    else if (this.randX >= IZTypeGame.SCREEN_WIDTH - 50) {
      this.x = randX - 100;
    }
    else {
      this.x = randX;
    }
    
    if (this.randY <= 20) {
      this.y = randY + 20;
    }
    else if (this.randY >= IZTypeGame.SCREEN_HEIGHT - 200) {
      this.y = randY - 200;
    }
    else {
      this.y = randY;
    }
  }

  //constructor with exact coordinates for tests
  InactiveWord(String word, int x, int y) {
    this.word = word;
    this.x = x;
    this.y = y;
    this.rand = IZTypeGame.RAND_COORD;
    this.randX = IZTypeGame.RAND_COORD_X;
    this.randY = IZTypeGame.RAND_COORD_Y;
  }
  
  /*TEMPLATE
   * Fields:
   * ... this.word ...                              -- String
   * ... this.x ...                                 -- int
   * ... this.y ...                                 -- int
   * ... this.random ...                            -- Random
   * ... this.randX ...                             -- int
   * ... this.randY ...                             -- int
   * Methods:
   * ... this.draw() ...                            -- WorldImage
   * ... this.drawWordOnSceneHelp(WorldScene) ...   -- WorldScene
   * ... this.move() ...                            -- IWord
   * ... this.checkAndReduceHelp(String) ...        -- IWord
   * ... this.reachBottomHelper() ...               -- Boolean
   * ... this.scoreWord() ...                       -- int
   */
  
  
  //helper for draw method, draws each word according
  //to string and type, active words are red and inactive
  //words are cyan
  public WorldImage draw() {
    return new TextImage(this.word, 30, FontStyle.BOLD, Color.WHITE);
  }
  
  //helper for drawOnScene, access word string and location to
  //draw correct text image
  public WorldScene drawWordOnSceneHelp(WorldScene acc) {
    
    /*
     * TEMPLATE:
     * Everything in class template, plus
     * Methods on parameters:
     * ... acc.draw(WorldScene) ...       -- WorldScene
     */
    
    return acc.placeImageXY(new RectangleImage(this.word.length() * 20, 50, 
        OutlineMode.SOLID, Color.darkGray),this.x, this.y)
        .placeImageXY(this.draw(), this.x, this.y);
  }
  
  //move the words in list down the screen
  public IWord move() {
    if (!this.word.equals("")) {
      return new InactiveWord(this.word, this.x, this.y + 10);
    }
    else {
      return this;
    }
  }
  
  //helper for checkAndReduce method, accesses first character of word substring,
  //determines if the passed in letterRemover is equal, and then reduces word
  //if it applies
  public IWord checkAndReduceHelp(String letterRemover) {
    if (this.word.length() == 0) {
      return this;
    }
    else if (this.word.substring(0,1).equals(letterRemover)) {
      return new ActiveWord(this.word.substring(1, this.word.length()), this.x, this.y);
    }
    else {
      return this;
    }
  }
  
  //helper for reachBottom, determines if single
  //word has reached bottom
  public boolean reachBottomHelper() {
    return this.y >= IZTypeGame.SCREEN_HEIGHT - 30;
  }
  
  //determine if a word is an empty string
  //if so add to score 
  public int scoreWord() {
    if (this.word.equals("")) {
      return 1;
    }
    else {
      return 0;
    }
  }
  
}

//class containing Util methods
class Utils {
  
  /*TEMPLATE
   * Fields:
   * ... this.rand ...            -- Random
   * Methods:
   * ... this.randomWord() ...    -- String
   */
  
  Random rand;
  
  Utils() {
    this.rand = new Random();
  }
  
  Utils(Random rand) {
    this.rand = rand;
  }
  
  //generates a word with 6 random alphabetic characters 
  String randomWord() {
    return "" + IZTypeGame.ALPHABET.charAt(rand.nextInt(25)) 
      + IZTypeGame.ALPHABET.charAt(rand.nextInt(25)) 
      + IZTypeGame.ALPHABET.charAt(rand.nextInt(25)) 
      + IZTypeGame.ALPHABET.charAt(rand.nextInt(25)) 
      + IZTypeGame.ALPHABET.charAt(rand.nextInt(25)) 
      + IZTypeGame.ALPHABET.charAt(rand.nextInt(25));
  }
}

//examples for ZTypeGame
class ExamplesZTypeGame {
  
  //random for tests
  Random randExample = new Random(25);
  Random randExample2 = new Random(24);
  
  //word examples
  
  //active words
  IWord apple = new ActiveWord("apple", 250, 250);
  //apple for testing
  //IWord appleT = new ActiveWord("apple", 250, 250, 
  //IZTypeGame.RAND_COORD, IZTypeGame.RAND_COORD_X, IZTypeGame.RAND_COORD_Y);
  IWord appleMixed = new ActiveWord("aPPlE", 400, 400); //mixed case
  IWord orange = new ActiveWord("orange", 120, 340);
  IWord grape = new ActiveWord("grape", 50, 200);
  IWord grapeMixed = new ActiveWord("GRAPE", 64, 350); //mixed case
  IWord mango = new ActiveWord("Mango", 100, 490); //starts with upper case
  IWord make = new ActiveWord("Make", 320, 110); //starts with upper case
  IWord a = new ActiveWord("a", 410, 100); //single character string
  IWord mtString = new ActiveWord("", 50, 320); //empty string
  
  //random active words
  
  IWord appleRand = new ActiveWord("apple");
  IWord appleMixedRand = new ActiveWord("aPPlE");
  IWord orangeRand = new ActiveWord("orange");
  IWord grapeRand = new ActiveWord("grape");
  IWord grapeMixedRand = new ActiveWord("GRAPE");
  IWord mangoRand = new ActiveWord("Mango");
  IWord makeRand = new ActiveWord("Make");
  IWord aRand = new ActiveWord("a");
  IWord mtStringRand = new ActiveWord("");
  //random word using util method
  IWord utilARand = new ActiveWord(new Utils().randomWord());
  IWord utilARandT = new ActiveWord(new Utils(new Random(25)).randomWord(), 300, 300);

  
  //inactive words
  IWord ice = new InactiveWord("ice", 50, 289);
  IWord iceMixed = new InactiveWord("iCE", 310, 50); //mixed case
  IWord idea = new InactiveWord("idea", 50, 150);
  IWord igloo = new InactiveWord("igloo", 400, 290);
  IWord iglooMixed = new InactiveWord("IGLOO", 83, 300); //mixed case
  IWord ignore = new InactiveWord("ignore", 400, 300);
  IWord inner = new InactiveWord("Inner", 200, 250); //starts with upper case
  
  //random inactive words
  IWord iceRand = new ActiveWord("ice");
  IWord iceMixedRand = new InactiveWord("iCE");
  IWord ideaRand = new InactiveWord("idea");
  IWord iglooRand = new InactiveWord("igloo");
  IWord iglooMixedRand = new InactiveWord("IGLOO");
  IWord ignoreRand = new InactiveWord("ignore");
  IWord innerRand = new InactiveWord("Inner");
  //random word using util method
  IWord utilIRand = new InactiveWord(new Utils().randomWord());
 
  //list examples
  
  //active word lists
  
  //empty list
  ILoWord emptyList = new MtLoWord();
  //list with empty string
  ILoWord mtStringList = new ConsLoWord(this.mtStringRand, this.emptyList);
  //list with some empty strings mixed between words
  ILoWord someMtStringsList = new ConsLoWord(this.mtString, 
      new ConsLoWord(this.apple, 
          new ConsLoWord(this.mtString,
              new ConsLoWord(this.mango, this.emptyList))));
  //list with just apple word
  ILoWord listA = new ConsLoWord(this.apple, this.emptyList);
  //list with just apple word but random coordinates
  ILoWord listARand = new ConsLoWord(this.appleRand, this.emptyList);
  //list with two active word: apple and orange
  ILoWord listB = new ConsLoWord(this.orange, this.listA);
  //list with two active word: apple and orange
  ILoWord listBRand = new ConsLoWord(this.orangeRand, this.listARand);
  
  //list of active word using utils random method
  ILoWord listAUtil = new ConsLoWord(this.utilARand, this.emptyList);
  //list of inactive word using utils random method
  ILoWord listIUtil = new ConsLoWord(this.utilIRand, this.emptyList);
  //list of both active and inactive words using utils random method
  ILoWord listAIUtil = new ConsLoWord(new ActiveWord(new Utils().randomWord()), 
      new ConsLoWord(new InactiveWord(new Utils().randomWord()), 
          new ConsLoWord(new InactiveWord(new Utils().randomWord()),
              new ConsLoWord(new InactiveWord(new Utils().randomWord()), new MtLoWord()))));
  //list of random words that are all inactive, used to start the game
  ILoWord listAIUtilGameStart = new ConsLoWord(new InactiveWord(new Utils().randomWord()), 
      new ConsLoWord(new InactiveWord(new Utils().randomWord()), 
          new ConsLoWord(new InactiveWord(new Utils().randomWord()),
              new ConsLoWord(new InactiveWord(new Utils().randomWord()), new MtLoWord()))));
  //listAIUtil but for testing
  ILoWord listAIUtilT = new ConsLoWord(new ActiveWord(new Utils(new Random(22)).randomWord(), 
      IZTypeGame.RAND_COORD_X, IZTypeGame.RAND_COORD_Y), 
      new ConsLoWord(new InactiveWord(new Utils(new Random(19)).randomWord(), 
          IZTypeGame.RAND_COORD_X, IZTypeGame.RAND_COORD_Y), 
          new ConsLoWord(new InactiveWord(new Utils(new Random(21)).randomWord(), 
              IZTypeGame.RAND_COORD_X, IZTypeGame.RAND_COORD_Y),
              new ConsLoWord(new InactiveWord(new Utils(new Random(23)).randomWord(), 
                  IZTypeGame.RAND_COORD_X, IZTypeGame.RAND_COORD_Y), new MtLoWord()))));
  //list of words for level 2 testing
  ILoWord listLevel2Empties = new ConsLoWord(this.mtString,
      new ConsLoWord(this.mtString, 
          new ConsLoWord(this.mtString, 
              new ConsLoWord(this.mtString, 
                  new ConsLoWord(this.mtString, 
                      new ConsLoWord(this.mtString, 
                          new ConsLoWord(this.mtString, 
                              new ConsLoWord(this.mtString, 
                                  new ConsLoWord(this.mtString, 
                                      new ConsLoWord(this.mtString, 
                                          new ConsLoWord(this.mtString, 
                                              new ConsLoWord(this.mtString, 
                                                  this.emptyList))))))))))));
  //list of words for level 3 testing
  ILoWord listLevel3Empties = new ConsLoWord(this.mtString, 
          new ConsLoWord(this.mtString, 
              new ConsLoWord(this.mtString, 
                  new ConsLoWord(this.mtString, 
                      new ConsLoWord(this.mtString, 
                          new ConsLoWord(this.mtString, 
                              new ConsLoWord(this.mtString, 
                                  new ConsLoWord(this.mtString, 
                                      new ConsLoWord(this.mtString, 
                                          new ConsLoWord(this.mtString, 
                                              this.listLevel2Empties))))))))));
                                                 
  //list of words for game over testing
  ILoWord listGameOverEmpties = new ConsLoWord(this.mtString,
      new ConsLoWord(this.mtString, 
          new ConsLoWord(this.mtString,
              new ConsLoWord(this.mtString, this.listLevel3Empties))));
                 
  //list of words for game won testing
  ILoWord listGameWon = new ConsLoWord(this.mtString, 
      new ConsLoWord(this.mtString, 
          new ConsLoWord(this.mtString, this.listLevel3Empties)));
  
  //example of a WorldStates for the ZTypeGamew
  ZTypeWorld ztw1 = new ZTypeWorld(this.listAIUtilGameStart, 0);
  ZTypeWorld ztw2 = new ZTypeWorld(this.listIUtil, 0);
  ZTypeWorld ztw3 = new ZTypeWorld(this.listAUtil, 0);
  ZTypeWorld ztw4 = new ZTypeWorld(this.listB, 0);
  ZTypeWorld ztw5 = new ZTypeWorld(this.mtStringList, 0);
  ZTypeWorld ztwLevel2 = new ZTypeWorld(this.listLevel2Empties, 0);
  ZTypeWorld ztwLevel3 = new ZTypeWorld(this.listLevel3Empties, 0);
  ZTypeWorld ztwGameOver = new ZTypeWorld(this.listGameOverEmpties, 0);
  ZTypeWorld ztw0 = new ZTypeWorld(this.emptyList, 0);
  ZTypeWorld ztwSomeEmpty = new ZTypeWorld(this.someMtStringsList, 0);
  ZTypeWorld ztwGameWon = new ZTypeWorld(this.listGameWon, 0);
  ZTypeWorld ztwDiffTickPassed = new ZTypeWorld(this.someMtStringsList, 3);
  
  //test big bang for ZTypeWorld
  boolean testBigBang(Tester t) {
    //test BigBang on a world state with a set of random Util words 
    return ztw1.bigBang(IZTypeGame.SCREEN_WIDTH, IZTypeGame.SCREEN_HEIGHT, IZTypeGame.TICK_RATE);
  }
  
  //test ZTypeWorld methods
  
  //test drawScore method
  boolean testDrawScore(Tester t) {
    //draws score that has increased
    return t.checkExpect(this.ztw5.drawScore(), 
        (new TextImage("Score: " + String.valueOf(this.ztw5.words.drawScoreHelp()), 
            24, FontStyle.BOLD, Color.RED))) 
        && 
        // draws score that has not increased
        t.checkExpect(this.ztw4.drawScore(),
            (new TextImage("Score: " + String.valueOf(this.ztw4.words.drawScoreHelp()), 
            24, FontStyle.BOLD, Color.RED)));
  }
  
  //test makeScene method on ZTypeWorld
  boolean testMakeScene(Tester t) {
    //test makeScene on ztw World with the set of random words
    //used to start the game
    return t.checkExpect(this.ztw1.makeScene(), 
        this.listAIUtilGameStart.draw(new WorldScene(IZTypeGame.SCREEN_WIDTH, 
            IZTypeGame.SCREEN_HEIGHT)
            .placeImageXY(IZTypeGame.BLACK_RECT, IZTypeGame.SCREEN_WIDTH / 2, 
                IZTypeGame.SCREEN_HEIGHT / 2)
            .placeImageXY(IZTypeGame.STARS1, IZTypeGame.SCREEN_WIDTH / 16, 
                IZTypeGame.SCREEN_HEIGHT / 16)
            .placeImageXY(IZTypeGame.STARS2, IZTypeGame.SCREEN_WIDTH / 3, 
                IZTypeGame.SCREEN_HEIGHT / 5)
            .placeImageXY(IZTypeGame.STARS2, IZTypeGame.SCREEN_WIDTH / 8, 
                IZTypeGame.SCREEN_HEIGHT / 3)
            .placeImageXY(IZTypeGame.STARS2, IZTypeGame.SCREEN_WIDTH / 4, 
                IZTypeGame.SCREEN_HEIGHT / 7)
            .placeImageXY(IZTypeGame.STARS1, 
                IZTypeGame.SCREEN_WIDTH / 16 + 100, IZTypeGame.SCREEN_HEIGHT / 16)
            .placeImageXY(IZTypeGame.STARS1, 
                IZTypeGame.SCREEN_WIDTH / 3 + 100, IZTypeGame.SCREEN_HEIGHT / 5)
            .placeImageXY(IZTypeGame.STARS1, 
                IZTypeGame.SCREEN_WIDTH / 8 + 100, IZTypeGame.SCREEN_HEIGHT / 3)
            .placeImageXY(IZTypeGame.STARS2, 
                IZTypeGame.SCREEN_WIDTH / 4 + 100, IZTypeGame.SCREEN_HEIGHT / 7)
            .placeImageXY(IZTypeGame.STARS2, 
                IZTypeGame.SCREEN_WIDTH / 16 + 200, IZTypeGame.SCREEN_HEIGHT / 16)
            .placeImageXY(IZTypeGame.STARS1, 
                IZTypeGame.SCREEN_WIDTH / 3 + 200, IZTypeGame.SCREEN_HEIGHT / 5)
            .placeImageXY(IZTypeGame.STARS1, 
                IZTypeGame.SCREEN_WIDTH / 8 + 200, IZTypeGame.SCREEN_HEIGHT / 3)
            .placeImageXY(IZTypeGame.STARS1, 
                IZTypeGame.SCREEN_WIDTH / 4 + 200, IZTypeGame.SCREEN_HEIGHT / 7)
            .placeImageXY(IZTypeGame.STARS2, 
                IZTypeGame.SCREEN_WIDTH / 16, IZTypeGame.SCREEN_HEIGHT / 16 + 100)
            .placeImageXY(IZTypeGame.STARS2, 
                IZTypeGame.SCREEN_WIDTH / 3, IZTypeGame.SCREEN_HEIGHT / 5 + 100)
            .placeImageXY(IZTypeGame.STARS2, 
                IZTypeGame.SCREEN_WIDTH / 8, IZTypeGame.SCREEN_HEIGHT / 3 + 100)
            .placeImageXY(IZTypeGame.STARS1, 
                IZTypeGame.SCREEN_WIDTH / 4, IZTypeGame.SCREEN_HEIGHT / 7 + 100)
            .placeImageXY(IZTypeGame.STARS2, 
                IZTypeGame.SCREEN_WIDTH / 16 + 100, IZTypeGame.SCREEN_HEIGHT / 16 - 200)
            .placeImageXY(IZTypeGame.STARS1, 
                IZTypeGame.SCREEN_WIDTH / 3 + 100, IZTypeGame.SCREEN_HEIGHT / 5 + 200)
            .placeImageXY(IZTypeGame.STARS1, 
                IZTypeGame.SCREEN_WIDTH / 8 + 100, IZTypeGame.SCREEN_HEIGHT / 3 + 200)
            .placeImageXY(IZTypeGame.STARS2, 
                IZTypeGame.SCREEN_WIDTH / 4 + 100, IZTypeGame.SCREEN_HEIGHT / 7 - 200)
            .placeImageXY(IZTypeGame.STARS1, 
                IZTypeGame.SCREEN_WIDTH / 16 + 200, IZTypeGame.SCREEN_HEIGHT / 16 + 300)
            .placeImageXY(IZTypeGame.STARS1, 
                IZTypeGame.SCREEN_WIDTH / 3 + 200, IZTypeGame.SCREEN_HEIGHT / 5 + 300)
            .placeImageXY(IZTypeGame.STARS2, 
                IZTypeGame.SCREEN_WIDTH / 8 + 200, IZTypeGame.SCREEN_HEIGHT / 3 + 300)
            .placeImageXY(IZTypeGame.STARS2, 
                IZTypeGame.SCREEN_WIDTH / 4 + 200, IZTypeGame.SCREEN_HEIGHT / 7 + 300)
            .placeImageXY(IZTypeGame.STARS2, 
                IZTypeGame.SCREEN_WIDTH - 50, 
                IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 10)
            .placeImageXY(IZTypeGame.STARS1, 
                IZTypeGame.SCREEN_WIDTH - 70, 
                IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 50)
            .placeImageXY(IZTypeGame.STARS1, 
                IZTypeGame.SCREEN_WIDTH - 50, 
                IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 80)
            .placeImageXY(IZTypeGame.STARS2, 
                IZTypeGame.SCREEN_WIDTH - 40, 
                IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 100)
            .placeImageXY(IZTypeGame.STARS2, 
                IZTypeGame.SCREEN_WIDTH - 50, 
                IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 140)
            .placeImageXY(IZTypeGame.STARS2, 
                IZTypeGame.SCREEN_WIDTH - 70, 
                IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 170)
            //place image of score
            .placeImageXY(this.ztw1.drawScore(), 
                IZTypeGame.SCREEN_WIDTH - IZTypeGame.SCREEN_WIDTH + 70 ,
                IZTypeGame.SCREEN_HEIGHT - 25))
            //place image of ship
            .placeImageXY(IZTypeGame.SHIP, IZTypeGame.SCREEN_WIDTH / 2, 
                IZTypeGame.SCREEN_HEIGHT - 40)
            //place image of level
            .placeImageXY(new TextImage("Level 1", 24, FontStyle.BOLD, Color.RED),
                IZTypeGame.SCREEN_WIDTH - IZTypeGame.SCREEN_WIDTH + 420 ,
                IZTypeGame.SCREEN_HEIGHT - 25))
        &&
        //test makeScene on world with list with an empty string
        t.checkExpect(this.ztw5.makeScene(), 
            this.mtStringList.draw(new WorldScene(IZTypeGame.SCREEN_WIDTH, 
                IZTypeGame.SCREEN_HEIGHT)
                .placeImageXY(IZTypeGame.BLACK_RECT, IZTypeGame.SCREEN_WIDTH / 2, 
                    IZTypeGame.SCREEN_HEIGHT / 2)
                .placeImageXY(IZTypeGame.STARS1, IZTypeGame.SCREEN_WIDTH / 16, 
                    IZTypeGame.SCREEN_HEIGHT / 16)
                .placeImageXY(IZTypeGame.STARS2, IZTypeGame.SCREEN_WIDTH / 3, 
                    IZTypeGame.SCREEN_HEIGHT / 5)
                .placeImageXY(IZTypeGame.STARS2, IZTypeGame.SCREEN_WIDTH / 8, 
                    IZTypeGame.SCREEN_HEIGHT / 3)
                .placeImageXY(IZTypeGame.STARS2, IZTypeGame.SCREEN_WIDTH / 4, 
                    IZTypeGame.SCREEN_HEIGHT / 7)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH / 16 + 100, IZTypeGame.SCREEN_HEIGHT / 16)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH / 3 + 100, IZTypeGame.SCREEN_HEIGHT / 5)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH / 8 + 100, IZTypeGame.SCREEN_HEIGHT / 3)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH / 4 + 100, IZTypeGame.SCREEN_HEIGHT / 7)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH / 16 + 200, IZTypeGame.SCREEN_HEIGHT / 16)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH / 3 + 200, IZTypeGame.SCREEN_HEIGHT / 5)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH / 8 + 200, IZTypeGame.SCREEN_HEIGHT / 3)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH / 4 + 200, IZTypeGame.SCREEN_HEIGHT / 7)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH / 16, IZTypeGame.SCREEN_HEIGHT / 16 + 100)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH / 3, IZTypeGame.SCREEN_HEIGHT / 5 + 100)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH / 8, IZTypeGame.SCREEN_HEIGHT / 3 + 100)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH / 4, IZTypeGame.SCREEN_HEIGHT / 7 + 100)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH / 16 + 100, IZTypeGame.SCREEN_HEIGHT / 16 - 200)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH / 3 + 100, IZTypeGame.SCREEN_HEIGHT / 5 + 200)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH / 8 + 100, IZTypeGame.SCREEN_HEIGHT / 3 + 200)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH / 4 + 100, IZTypeGame.SCREEN_HEIGHT / 7 - 200)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH / 16 + 200, IZTypeGame.SCREEN_HEIGHT / 16 + 300)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH / 3 + 200, IZTypeGame.SCREEN_HEIGHT / 5 + 300)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH / 8 + 200, IZTypeGame.SCREEN_HEIGHT / 3 + 300)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH / 4 + 200, IZTypeGame.SCREEN_HEIGHT / 7 + 300)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH - 50, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 10)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH - 70, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 50)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH - 50, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 80)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH - 40, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 100)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH - 50, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 140)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH - 70, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 170)
                //place image of score
                .placeImageXY(this.ztw5.drawScore(), 
                    IZTypeGame.SCREEN_WIDTH - IZTypeGame.SCREEN_WIDTH + 70 ,
                    IZTypeGame.SCREEN_HEIGHT - 25))
                //place image of ship
                .placeImageXY(IZTypeGame.SHIP, IZTypeGame.SCREEN_WIDTH / 2, 
                    IZTypeGame.SCREEN_HEIGHT - 40)
                //place image of level
                .placeImageXY(new TextImage("Level 1", 24, FontStyle.BOLD, Color.RED),
                    IZTypeGame.SCREEN_WIDTH - IZTypeGame.SCREEN_WIDTH + 420 ,
                    IZTypeGame.SCREEN_HEIGHT - 25))
        &&
        //test makeScene on world with an empty list 
        t.checkExpect(new ZTypeWorld(this.emptyList, 0).makeScene(), 
            this.emptyList.draw(new WorldScene(IZTypeGame.SCREEN_WIDTH, 
                IZTypeGame.SCREEN_HEIGHT)
                .placeImageXY(IZTypeGame.BLACK_RECT, IZTypeGame.SCREEN_WIDTH / 2, 
                    IZTypeGame.SCREEN_HEIGHT / 2)
                .placeImageXY(IZTypeGame.STARS1, IZTypeGame.SCREEN_WIDTH / 16, 
                    IZTypeGame.SCREEN_HEIGHT / 16)
                .placeImageXY(IZTypeGame.STARS2, IZTypeGame.SCREEN_WIDTH / 3, 
                    IZTypeGame.SCREEN_HEIGHT / 5)
                .placeImageXY(IZTypeGame.STARS2, IZTypeGame.SCREEN_WIDTH / 8, 
                    IZTypeGame.SCREEN_HEIGHT / 3)
                .placeImageXY(IZTypeGame.STARS2, IZTypeGame.SCREEN_WIDTH / 4, 
                    IZTypeGame.SCREEN_HEIGHT / 7)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH / 16 + 100, IZTypeGame.SCREEN_HEIGHT / 16)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH / 3 + 100, IZTypeGame.SCREEN_HEIGHT / 5)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH / 8 + 100, IZTypeGame.SCREEN_HEIGHT / 3)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH / 4 + 100, IZTypeGame.SCREEN_HEIGHT / 7)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH / 16 + 200, IZTypeGame.SCREEN_HEIGHT / 16)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH / 3 + 200, IZTypeGame.SCREEN_HEIGHT / 5)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH / 8 + 200, IZTypeGame.SCREEN_HEIGHT / 3)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH / 4 + 200, IZTypeGame.SCREEN_HEIGHT / 7)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH / 16, IZTypeGame.SCREEN_HEIGHT / 16 + 100)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH / 3, IZTypeGame.SCREEN_HEIGHT / 5 + 100)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH / 8, IZTypeGame.SCREEN_HEIGHT / 3 + 100)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH / 4, IZTypeGame.SCREEN_HEIGHT / 7 + 100)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH / 16 + 100, IZTypeGame.SCREEN_HEIGHT / 16 - 200)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH / 3 + 100, IZTypeGame.SCREEN_HEIGHT / 5 + 200)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH / 8 + 100, IZTypeGame.SCREEN_HEIGHT / 3 + 200)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH / 4 + 100, IZTypeGame.SCREEN_HEIGHT / 7 - 200)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH / 16 + 200, IZTypeGame.SCREEN_HEIGHT / 16 + 300)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH / 3 + 200, IZTypeGame.SCREEN_HEIGHT / 5 + 300)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH / 8 + 200, IZTypeGame.SCREEN_HEIGHT / 3 + 300)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH / 4 + 200, IZTypeGame.SCREEN_HEIGHT / 7 + 300)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH - 50, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 10)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH - 70, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 50)
                .placeImageXY(IZTypeGame.STARS1, 
                    IZTypeGame.SCREEN_WIDTH - 50, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 80)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH - 40, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 100)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH - 50, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 140)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH - 70, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 170)
                //place image of score
                .placeImageXY(new ZTypeWorld(this.emptyList, 0).drawScore(), 
                    IZTypeGame.SCREEN_WIDTH - IZTypeGame.SCREEN_WIDTH + 70 ,
                    IZTypeGame.SCREEN_HEIGHT - 25))
                //place image of ship
                .placeImageXY(IZTypeGame.SHIP, IZTypeGame.SCREEN_WIDTH / 2, 
                    IZTypeGame.SCREEN_HEIGHT - 40)
                //place image of level
                .placeImageXY(new TextImage("Level 1", 24, FontStyle.BOLD, Color.RED),
                    IZTypeGame.SCREEN_WIDTH - IZTypeGame.SCREEN_WIDTH + 420 ,
                    IZTypeGame.SCREEN_HEIGHT - 25))
        &&
        //test on world in level 2 (score greater than 10)
        t.checkExpect(this.ztwLevel2.makeScene(),
            this.listLevel2Empties.draw(new WorldScene(IZTypeGame.SCREEN_WIDTH, 
                IZTypeGame.SCREEN_HEIGHT)
                .placeImageXY(IZTypeGame.BLACK_RECT, 
                    IZTypeGame.SCREEN_WIDTH / 2, IZTypeGame.SCREEN_HEIGHT / 2)
                .placeImageXY(IZTypeGame.STARS3, 
                    IZTypeGame.SCREEN_WIDTH / 16, IZTypeGame.SCREEN_HEIGHT / 16)
                .placeImageXY(IZTypeGame.STARS3, 
                    IZTypeGame.SCREEN_WIDTH / 3, IZTypeGame.SCREEN_HEIGHT / 5)
                .placeImageXY(IZTypeGame.STARS4, 
                    IZTypeGame.SCREEN_WIDTH / 8, IZTypeGame.SCREEN_HEIGHT / 3)
                .placeImageXY(IZTypeGame.STARS4, 
                    IZTypeGame.SCREEN_WIDTH / 4, IZTypeGame.SCREEN_HEIGHT / 7)
                .placeImageXY(IZTypeGame.STARS3, 
                    IZTypeGame.SCREEN_WIDTH / 16 + 100, IZTypeGame.SCREEN_HEIGHT / 16)
                .placeImageXY(IZTypeGame.STARS3, 
                    IZTypeGame.SCREEN_WIDTH / 3 + 100, IZTypeGame.SCREEN_HEIGHT / 5)
                .placeImageXY(IZTypeGame.STARS3, 
                    IZTypeGame.SCREEN_WIDTH / 8 + 100, IZTypeGame.SCREEN_HEIGHT / 3)
                .placeImageXY(IZTypeGame.STARS3, 
                    IZTypeGame.SCREEN_WIDTH / 4 + 100, IZTypeGame.SCREEN_HEIGHT / 7)
                .placeImageXY(IZTypeGame.STARS4, 
                    IZTypeGame.SCREEN_WIDTH / 16 + 200, IZTypeGame.SCREEN_HEIGHT / 16)
                .placeImageXY(IZTypeGame.STARS4, 
                    IZTypeGame.SCREEN_WIDTH / 3 + 200, IZTypeGame.SCREEN_HEIGHT / 5)
                .placeImageXY(IZTypeGame.STARS4, 
                    IZTypeGame.SCREEN_WIDTH / 8 + 200, IZTypeGame.SCREEN_HEIGHT / 3)
                .placeImageXY(IZTypeGame.STARS3, 
                    IZTypeGame.SCREEN_WIDTH / 4 + 200, IZTypeGame.SCREEN_HEIGHT / 7)
                .placeImageXY(IZTypeGame.STARS3, 
                    IZTypeGame.SCREEN_WIDTH / 16, IZTypeGame.SCREEN_HEIGHT / 16 + 100)
                .placeImageXY(IZTypeGame.STARS4, 
                    IZTypeGame.SCREEN_WIDTH / 3, IZTypeGame.SCREEN_HEIGHT / 5 + 100)
                .placeImageXY(IZTypeGame.STARS3, 
                    IZTypeGame.SCREEN_WIDTH / 8, IZTypeGame.SCREEN_HEIGHT / 3 + 100)
                .placeImageXY(IZTypeGame.STARS4, 
                    IZTypeGame.SCREEN_WIDTH / 4, IZTypeGame.SCREEN_HEIGHT / 7 + 100)
                .placeImageXY(IZTypeGame.STARS3, 
                    IZTypeGame.SCREEN_WIDTH / 16 + 100, IZTypeGame.SCREEN_HEIGHT / 16 - 200)
                .placeImageXY(IZTypeGame.STARS4, 
                    IZTypeGame.SCREEN_WIDTH / 3 + 100, IZTypeGame.SCREEN_HEIGHT / 5 + 200)
                .placeImageXY(IZTypeGame.STARS4, 
                    IZTypeGame.SCREEN_WIDTH / 8 + 100, IZTypeGame.SCREEN_HEIGHT / 3 + 200)
                .placeImageXY(IZTypeGame.STARS3, 
                    IZTypeGame.SCREEN_WIDTH / 4 + 100, IZTypeGame.SCREEN_HEIGHT / 7 - 200)
                .placeImageXY(IZTypeGame.STARS4, 
                    IZTypeGame.SCREEN_WIDTH / 16 + 200, IZTypeGame.SCREEN_HEIGHT / 16 + 300)
                .placeImageXY(IZTypeGame.STARS3, 
                    IZTypeGame.SCREEN_WIDTH / 3 + 200, IZTypeGame.SCREEN_HEIGHT / 5 + 300)
                .placeImageXY(IZTypeGame.STARS3, 
                    IZTypeGame.SCREEN_WIDTH / 8 + 200, IZTypeGame.SCREEN_HEIGHT / 3 + 300)
                .placeImageXY(IZTypeGame.STARS2, 
                    IZTypeGame.SCREEN_WIDTH / 4 + 200, IZTypeGame.SCREEN_HEIGHT / 7 + 300)
                .placeImageXY(IZTypeGame.STARS4, 
                    IZTypeGame.SCREEN_WIDTH - 50, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 10)
                .placeImageXY(IZTypeGame.STARS4, 
                    IZTypeGame.SCREEN_WIDTH - 70, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 50)
                .placeImageXY(IZTypeGame.STARS4, 
                    IZTypeGame.SCREEN_WIDTH - 50, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 80)
                .placeImageXY(IZTypeGame.STARS4, 
                    IZTypeGame.SCREEN_WIDTH - 40, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 100)
                .placeImageXY(IZTypeGame.STARS3, 
                    IZTypeGame.SCREEN_WIDTH - 50, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 140)
                .placeImageXY(IZTypeGame.STARS4, 
                    IZTypeGame.SCREEN_WIDTH - 70, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 170)
                //place image of score
                .placeImageXY(this.ztwLevel2.drawScore(), 
                    IZTypeGame.SCREEN_WIDTH 
                    - 
                    IZTypeGame.SCREEN_WIDTH + 70 , IZTypeGame.SCREEN_HEIGHT - 25))
                //place image of ship
                .placeImageXY(IZTypeGame.SHIP, IZTypeGame.SCREEN_WIDTH / 2, 
                    IZTypeGame.SCREEN_HEIGHT - 40)
                //place image of level
                .placeImageXY(new TextImage("Level 2", 24, FontStyle.BOLD, Color.RED),
                    IZTypeGame.SCREEN_WIDTH - IZTypeGame.SCREEN_WIDTH + 420 ,
                    IZTypeGame.SCREEN_HEIGHT - 25))
        &&
        //test on world in level 3 (greater than 20)
        t.checkExpect(this.ztwLevel3.makeScene(),
            this.listLevel3Empties.draw(new WorldScene(IZTypeGame.SCREEN_WIDTH, 
                IZTypeGame.SCREEN_HEIGHT)
                .placeImageXY(IZTypeGame.BLACK_RECT, IZTypeGame.SCREEN_WIDTH / 2, 
                    IZTypeGame.SCREEN_HEIGHT / 2)
                .placeImageXY(IZTypeGame.STARS5, 
                    IZTypeGame.SCREEN_WIDTH / 16, IZTypeGame.SCREEN_HEIGHT / 16)
                .placeImageXY(IZTypeGame.STARS5, 
                    IZTypeGame.SCREEN_WIDTH / 3, IZTypeGame.SCREEN_HEIGHT / 5)
                .placeImageXY(IZTypeGame.STARS6, 
                    IZTypeGame.SCREEN_WIDTH / 8, IZTypeGame.SCREEN_HEIGHT / 3)
                .placeImageXY(IZTypeGame.STARS6, 
                    IZTypeGame.SCREEN_WIDTH / 4, IZTypeGame.SCREEN_HEIGHT / 7)
                .placeImageXY(IZTypeGame.STARS6, 
                    IZTypeGame.SCREEN_WIDTH / 16 + 100, IZTypeGame.SCREEN_HEIGHT / 16)
                .placeImageXY(IZTypeGame.STARS5, 
                    IZTypeGame.SCREEN_WIDTH / 3 + 100, IZTypeGame.SCREEN_HEIGHT / 5)
                .placeImageXY(IZTypeGame.STARS5, 
                    IZTypeGame.SCREEN_WIDTH / 8 + 100, IZTypeGame.SCREEN_HEIGHT / 3)
                .placeImageXY(IZTypeGame.STARS6, 
                    IZTypeGame.SCREEN_WIDTH / 4 + 100, IZTypeGame.SCREEN_HEIGHT / 7)
                .placeImageXY(IZTypeGame.STARS6, 
                    IZTypeGame.SCREEN_WIDTH / 16 + 200, IZTypeGame.SCREEN_HEIGHT / 16)
                .placeImageXY(IZTypeGame.STARS6, 
                    IZTypeGame.SCREEN_WIDTH / 3 + 200, IZTypeGame.SCREEN_HEIGHT / 5)
                .placeImageXY(IZTypeGame.STARS6, 
                    IZTypeGame.SCREEN_WIDTH / 8 + 200, IZTypeGame.SCREEN_HEIGHT / 3)
                .placeImageXY(IZTypeGame.STARS6, 
                    IZTypeGame.SCREEN_WIDTH / 4 + 200, IZTypeGame.SCREEN_HEIGHT / 7)
                .placeImageXY(IZTypeGame.STARS6, 
                    IZTypeGame.SCREEN_WIDTH / 16, IZTypeGame.SCREEN_HEIGHT / 16 + 100)
                .placeImageXY(IZTypeGame.STARS6, 
                    IZTypeGame.SCREEN_WIDTH / 3, IZTypeGame.SCREEN_HEIGHT / 5 + 100)
                .placeImageXY(IZTypeGame.STARS5, 
                    IZTypeGame.SCREEN_WIDTH / 8, IZTypeGame.SCREEN_HEIGHT / 3 + 100)
                .placeImageXY(IZTypeGame.STARS5, 
                    IZTypeGame.SCREEN_WIDTH / 4, IZTypeGame.SCREEN_HEIGHT / 7 + 100)
                .placeImageXY(IZTypeGame.STARS5, 
                    IZTypeGame.SCREEN_WIDTH / 16 + 100, IZTypeGame.SCREEN_HEIGHT / 16 - 200)
                .placeImageXY(IZTypeGame.STARS5, 
                    IZTypeGame.SCREEN_WIDTH / 3 + 100, IZTypeGame.SCREEN_HEIGHT / 5 + 200)
                .placeImageXY(IZTypeGame.STARS5, 
                    IZTypeGame.SCREEN_WIDTH / 8 + 100, IZTypeGame.SCREEN_HEIGHT / 3 + 200)
                .placeImageXY(IZTypeGame.STARS5, 
                    IZTypeGame.SCREEN_WIDTH / 4 + 100, IZTypeGame.SCREEN_HEIGHT / 7 - 200)
                .placeImageXY(IZTypeGame.STARS5, 
                    IZTypeGame.SCREEN_WIDTH / 16 + 200, IZTypeGame.SCREEN_HEIGHT / 16 + 300)
                .placeImageXY(IZTypeGame.STARS5, 
                    IZTypeGame.SCREEN_WIDTH / 3 + 200, IZTypeGame.SCREEN_HEIGHT / 5 + 300)
                .placeImageXY(IZTypeGame.STARS6, 
                    IZTypeGame.SCREEN_WIDTH / 8 + 200, IZTypeGame.SCREEN_HEIGHT / 3 + 300)
                .placeImageXY(IZTypeGame.STARS6, 
                    IZTypeGame.SCREEN_WIDTH / 4 + 200, IZTypeGame.SCREEN_HEIGHT / 7 + 300)
                .placeImageXY(IZTypeGame.STARS6, 
                    IZTypeGame.SCREEN_WIDTH - 50,
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 10)
                .placeImageXY(IZTypeGame.STARS6, 
                    IZTypeGame.SCREEN_WIDTH - 70, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 50)
                .placeImageXY(IZTypeGame.STARS6, 
                    IZTypeGame.SCREEN_WIDTH - 50, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 80)
                .placeImageXY(IZTypeGame.STARS6, 
                    IZTypeGame.SCREEN_WIDTH - 40, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 100)
                .placeImageXY(IZTypeGame.STARS5, 
                    IZTypeGame.SCREEN_WIDTH - 50, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 140)
                .placeImageXY(IZTypeGame.STARS6, 
                    IZTypeGame.SCREEN_WIDTH - 70, 
                    IZTypeGame.SCREEN_HEIGHT - IZTypeGame.SCREEN_HEIGHT + 170)
                //place image of score
                .placeImageXY(this.ztwLevel3.drawScore(), 
                    IZTypeGame.SCREEN_WIDTH 
                    - 
                    IZTypeGame.SCREEN_WIDTH + 70 , IZTypeGame.SCREEN_HEIGHT - 25))
                //place image of ship
                .placeImageXY(IZTypeGame.SHIP, IZTypeGame.SCREEN_WIDTH / 2, 
                    IZTypeGame.SCREEN_HEIGHT - 40)
                //place image of level
                .placeImageXY(new TextImage("Level 3", 24, FontStyle.BOLD, Color.RED),
                    IZTypeGame.SCREEN_WIDTH - IZTypeGame.SCREEN_WIDTH + 420 ,
                    IZTypeGame.SCREEN_HEIGHT - 25));
  }
  
  //test the onTick method
  boolean testOnTick(Tester t) {
    //test on game world used for start of game
    return t.checkExpect(this.ztw1.onTickForTesting(), 
        new ZTypeWorld(this.listAIUtilGameStart.move(), 1))
        &&
        //test on world with empty list
        t.checkExpect(this.ztw0.onTickForTesting(), 
            new ZTypeWorld(this.emptyList.move(), 1))
        &&
        //test on world where game is over
        t.checkExpect(this.ztwGameOver.onTickForTesting(), 
            new ZTypeWorld(this.listGameOverEmpties.move(), 1))
        &&
        //test on world where it is in level 2
        t.checkExpect(this.ztwLevel2.onTickForTesting(),
            new ZTypeWorld(this.listLevel2Empties.move(), 1))
        &&
        //test on world where it is in level 3
        t.checkExpect(this.ztwLevel3.onTickForTesting(), 
            new ZTypeWorld(this.listLevel3Empties.move(), 1))
        &&
        //test on world where there are some empty string mixed in world word list
        t.checkExpect(this.ztwSomeEmpty.onTickForTesting(), 
            new ZTypeWorld(this.someMtStringsList.move(), 1))
        &&
        //test on world where there are some empty string mixed in world word list
        //and some ticks have already passed, causing a word to be added
        t.checkExpect(this.ztwDiffTickPassed.onTickForTesting(), 
            new ZTypeWorld(this.someMtStringsList.addWordForTesting().move(), 4));
        
  }
  
  //test the onKeyEvent method
  boolean testOnKeyEvent(Tester t) {
    //test on world with empty list
    return t.checkExpect(this.ztw0.onKeyEvent("t"), 
        new ZTypeWorld(this.emptyList.checkAndReduce("t"), 0))
        &&
        //test on game world used for start of game
        t.checkExpect(this.ztw1.onKeyEvent("k"), 
            new ZTypeWorld(this.listAIUtilGameStart.checkAndReduce("k"), 0))
        &&
        //test on world where game is over
        t.checkExpect(this.ztwGameOver.onKeyEvent("k"), 
            new ZTypeWorld(this.listGameOverEmpties.checkAndReduce("k"), 0))
        &&
        //test on world where it is in level 2
        t.checkExpect(this.ztwLevel2.onKeyEvent("f"), 
            new ZTypeWorld(this.listLevel2Empties.checkAndReduce("f"), 0))
        &&
        //test on world where it is in level 3
        t.checkExpect(this.ztwLevel3.onKeyEvent("l"), 
            new ZTypeWorld(this.listLevel3Empties.checkAndReduce("l"), 0))
        &&
        //test on world where there are some empty string mixed in world word list
        t.checkExpect(this.ztwSomeEmpty.onKeyEvent("m"),
            new ZTypeWorld(this.someMtStringsList.checkAndReduce("m"), 0))
        &&
        //test on world where list has multiple items starting with string
        t.checkExpect(new ZTypeWorld(new ConsLoWord(this.ice,
            new ConsLoWord(this.idea, this.emptyList)), 0).onKeyEvent("i"),
            new ZTypeWorld(new ConsLoWord(new ActiveWord("ce", 50, 289), 
                new ConsLoWord(new ActiveWord("dea", 50, 150), this.emptyList)), 0))
        &&
        //test on world where list has no characters that start with string
        t.checkExpect(new ZTypeWorld(this.listB, 0).onKeyEvent("x"),
            new ZTypeWorld(new ConsLoWord(this.orange,
                new ConsLoWord(this.apple, this.emptyList)), 0))
        &&
        //test on world where list has multiple words starting with string
        //mixed in with words that do not start with string
        t.checkExpect(new ZTypeWorld(new ConsLoWord(this.igloo, 
            new ConsLoWord(this.apple, 
                new ConsLoWord(this.ice, 
                    new ConsLoWord(this.mango, this.emptyList)))), 0).onKeyEvent("i"),
            new ZTypeWorld(new ConsLoWord((new ActiveWord("gloo", 400, 290)),
                new ConsLoWord(this.apple, 
                    new ConsLoWord((new ActiveWord("ce", 50, 289)), 
                        new ConsLoWord(this.mango, this.emptyList)))), 0));
  }
  
  //test the onMouseClicked Method
  boolean testOnMouseClicked(Tester t) {
    //test case where mouse is clicked within screen
    return t.checkExpect(this.ztw1.onMouseClickedForTesting(new Posn(400, 300)), 
        new ZTypeWorld(new ConsLoWord(new InactiveWord(new Utils(new Random(5)).randomWord(), 
            IZTypeGame.RAND_COORD_X, IZTypeGame.RAND_COORD_Y), 
            new ConsLoWord(new InactiveWord(new Utils(new Random(6)).randomWord(), 
                IZTypeGame.RAND_COORD_X, IZTypeGame.RAND_COORD_Y), 
                new ConsLoWord(new InactiveWord(new Utils(new Random(7)).randomWord(), 
                    IZTypeGame.RAND_COORD_X, IZTypeGame.RAND_COORD_Y),
                    new ConsLoWord(new InactiveWord(new Utils(new Random(8)).randomWord(), 
                        IZTypeGame.RAND_COORD_X, IZTypeGame.RAND_COORD_Y), new MtLoWord())))), 0))
        &&
        //test case where mouse is not clicked within screen
        t.checkExpect(this.ztw2.onMouseClickedForTesting(new Posn(-5, -10)), this.ztw2)
        &&
        //test case where mouse is clicked within screen's x bound but noy y bound
        t.checkExpect(this.ztw3.onMouseClickedForTesting(new Posn(45, -10)), this.ztw3)
        &&
        //test case where mouse is clicked within screen's y bound but not yxbound
        t.checkExpect(this.ztw3.onMouseClickedForTesting(new Posn(-50, -410)), this.ztw3);
  }
  
  //test the method worldEnds
  boolean testWorldEnds(Tester t) {
    //test case where no words have reached bottom and score is less than 25
    return t.checkExpect(this.ztw1.worldEnds(), new WorldEnd(false, this.ztw1.makeScene()))
        &&
        //test case where a word in game world list has reached the bottom
        //and score is less than 25
        t.checkExpect(new ZTypeWorld(new ConsLoWord(this.apple,
            new ConsLoWord(this.ice, 
                new ConsLoWord(this.mango, 
                    new ConsLoWord(new InactiveWord("door", 400, 500), 
                        new ConsLoWord(this.grape, this.emptyList))))), 0).worldEnds(), 
            new WorldEnd(true, new ZTypeWorld(new ConsLoWord(this.apple,
                new ConsLoWord(this.ice, 
                    new ConsLoWord(this.mango, 
                        new ConsLoWord(new InactiveWord("door", 400, 500), 
                            new ConsLoWord(this.grape, this.emptyList))))), 0).endScreen()))
        &&
        //test case where no words have reached the bottom and
        //the score is equal to 25
        t.checkExpect(this.ztwGameWon.worldEnds(), 
            new WorldEnd(true, new WorldScene(
                IZTypeGame.SCREEN_WIDTH, IZTypeGame.SCREEN_HEIGHT)
            .placeImageXY(IZTypeGame.BLACK_RECT,
                IZTypeGame.SCREEN_WIDTH / 2, IZTypeGame.SCREEN_HEIGHT / 2)
            .placeImageXY(IZTypeGame.FINISH_TEXT, 
                IZTypeGame.SCREEN_WIDTH / 2, IZTypeGame.SCREEN_HEIGHT / 2)));
        
  }
  
  //test the method endScreen
  boolean testEndScreen(Tester t) {
    //test on world with empty list
    return t.checkExpect(this.ztw0.endScreen(), 
        new WorldScene(IZTypeGame.SCREEN_WIDTH, IZTypeGame.SCREEN_HEIGHT)
        .placeImageXY(IZTypeGame.BLACK_RECT, 
            IZTypeGame.SCREEN_WIDTH / 2, IZTypeGame.SCREEN_HEIGHT / 2)
        .placeImageXY(IZTypeGame.END_TEXT, 
            IZTypeGame.SCREEN_WIDTH / 2, IZTypeGame.SCREEN_HEIGHT / 2))
        &&
        //test on world with normal starting list
        t.checkExpect(this.ztw1.endScreen(), 
            new WorldScene(IZTypeGame.SCREEN_WIDTH, IZTypeGame.SCREEN_HEIGHT)
            .placeImageXY(IZTypeGame.BLACK_RECT, 
                IZTypeGame.SCREEN_WIDTH / 2, IZTypeGame.SCREEN_HEIGHT / 2)
            .placeImageXY(IZTypeGame.END_TEXT, 
                IZTypeGame.SCREEN_WIDTH / 2, IZTypeGame.SCREEN_HEIGHT / 2))
        &&
        //test on world with where there are some empty string mixed in world word list
        t.checkExpect(this.ztwSomeEmpty.endScreen(), 
            new WorldScene(IZTypeGame.SCREEN_WIDTH, IZTypeGame.SCREEN_HEIGHT)
            .placeImageXY(IZTypeGame.BLACK_RECT, 
                IZTypeGame.SCREEN_WIDTH / 2, IZTypeGame.SCREEN_HEIGHT / 2)
            .placeImageXY(IZTypeGame.END_TEXT, 
                IZTypeGame.SCREEN_WIDTH / 2, IZTypeGame.SCREEN_HEIGHT / 2))
        &&
        //test on world in level 2
        t.checkExpect(this.ztwLevel2.endScreen(), 
            new WorldScene(IZTypeGame.SCREEN_WIDTH, IZTypeGame.SCREEN_HEIGHT)
            .placeImageXY(IZTypeGame.BLACK_RECT, 
                IZTypeGame.SCREEN_WIDTH / 2, IZTypeGame.SCREEN_HEIGHT / 2)
            .placeImageXY(IZTypeGame.END_TEXT, 
                IZTypeGame.SCREEN_WIDTH / 2, IZTypeGame.SCREEN_HEIGHT / 2))
        &&
        //test on world in level 3
        t.checkExpect(this.ztwLevel3.endScreen(), 
            new WorldScene(IZTypeGame.SCREEN_WIDTH, IZTypeGame.SCREEN_HEIGHT)
            .placeImageXY(IZTypeGame.BLACK_RECT, 
                IZTypeGame.SCREEN_WIDTH / 2, IZTypeGame.SCREEN_HEIGHT / 2)
            .placeImageXY(IZTypeGame.END_TEXT, 
                IZTypeGame.SCREEN_WIDTH / 2, IZTypeGame.SCREEN_HEIGHT / 2));
  }
  
  //empty WorldScene
  WorldScene eWS = new WorldScene(IZTypeGame.SCREEN_WIDTH,
      IZTypeGame.SCREEN_HEIGHT);
  WorldScene nEWS = this.apple.drawWordOnSceneHelp(eWS)
      .placeImageXY(this.orange.draw(), 120, 340)
      .placeImageXY(this.mtString.draw(), 50, 320);
  
  //test ILoWord and IWord methods
  
  //test draw method on ILoWord and IWord
  boolean testDraw(Tester t) {
    //test draw on an empty list giving an empty WorldScene
    return t.checkExpect(this.emptyList.draw(eWS), eWS)
        &&
        //test draw on an empty list giving an WorldScene with words
        t.checkExpect(this.emptyList.draw(nEWS), nEWS)
        &&
        //test draw on list of words giving an empty WorldScene
        t.checkExpect(this.listB.draw(eWS),
            this.listA.draw(this.orange.drawWordOnSceneHelp(eWS)))
        &&
        //test draw on a list of words giving a WorldScene with words
        t.checkExpect(
            new ConsLoWord(this.utilARand, 
                new ConsLoWord(this.utilIRand, this.emptyList)).draw(nEWS), 
            new ConsLoWord(this.utilIRand,this.emptyList)
            .draw(this.utilARand.drawWordOnSceneHelp(nEWS)))
        &&
        //test draw on active word that is an empty string
        t.checkExpect(this.mtString.draw(), 
            new TextImage("", 30, FontStyle.BOLD, Color.RED))
        &&
        //test draw on active word apple
        t.checkExpect(this.apple.draw(), 
            new TextImage("apple", 30, FontStyle.BOLD, Color.RED))
        &&
        //test draw on inactive word igloo
        t.checkExpect(this.igloo.draw(), 
            new TextImage("igloo", 30, FontStyle.BOLD, Color.WHITE))
        &&
        //test draw on active random word using util method
        t.checkExpect(new ActiveWord(new Utils(
            new Random(25)).randomWord()).draw(), new TextImage("" 
        + IZTypeGame.ALPHABET.charAt(randExample.nextInt(25)) 
        + IZTypeGame.ALPHABET.charAt(randExample.nextInt(25)) 
        + IZTypeGame.ALPHABET.charAt(randExample.nextInt(25)) 
        + IZTypeGame.ALPHABET.charAt(randExample.nextInt(25)) 
        + IZTypeGame.ALPHABET.charAt(randExample.nextInt(25)) 
        + IZTypeGame.ALPHABET.charAt(randExample.nextInt(25)), 
        30, FontStyle.BOLD, Color.RED))
        &&
        //test draw on Inactive random word using util method
        t.checkExpect(new InactiveWord(new Utils(
            new Random(24)).randomWord()).draw(), new TextImage("" 
        + IZTypeGame.ALPHABET.charAt(randExample2.nextInt(25)) 
        + IZTypeGame.ALPHABET.charAt(randExample2.nextInt(25)) 
        + IZTypeGame.ALPHABET.charAt(randExample2.nextInt(25)) 
        + IZTypeGame.ALPHABET.charAt(randExample2.nextInt(25)) 
        + IZTypeGame.ALPHABET.charAt(randExample2.nextInt(25)) 
        + IZTypeGame.ALPHABET.charAt(randExample2.nextInt(25)), 
        30, FontStyle.BOLD, Color.WHITE));
  }
  
  //test the drawWordOnSceneHelp on IWord
  boolean testDrawWordOnSceneHelp(Tester t) {
    //test drawWordOnSceneHelp on empty string with empty scene
    return t.checkExpect(this.mtString.drawWordOnSceneHelp(eWS), eWS)
        &&
        //test drawWordOnSceneHelp help on empty string with scene with words
        t.checkExpect(this.mtString.drawWordOnSceneHelp(nEWS), nEWS)
        &&
        //test drawWordOnSceneHelp on apple word on empty scene
        t.checkExpect(this.apple.drawWordOnSceneHelp(eWS), 
            eWS.placeImageXY(new RectangleImage(5 * 20, 50, 
            OutlineMode.SOLID, Color.DARK_GRAY), 250, 250)
            .placeImageXY(this.apple.draw(), 250, 250))
        &&
        //test drawWordOnSceneHelp on igloo word on scene with words
        t.checkExpect(this.igloo.drawWordOnSceneHelp(nEWS), 
            nEWS.placeImageXY(new RectangleImage(5 * 20, 50, 
            OutlineMode.SOLID, Color.DARK_GRAY), 400, 290)
            .placeImageXY(this.igloo.draw(), 400, 290))
        &&
        //test drawWordOnSceneHelp on random util word on scene with words
        t.checkExpect(this.utilARandT.drawWordOnSceneHelp(nEWS), 
            nEWS.placeImageXY(new RectangleImage(6 * 20, 50, 
            OutlineMode.SOLID, Color.DARK_GRAY), 300, 300)
            .placeImageXY(this.utilARandT.draw(), 300, 300));
  }
  
  
  //test the move methods
  boolean testMove(Tester t) {
    //test move on apple active word
    return t.checkExpect(this.apple.move(), new ActiveWord("apple", 250, 260))
        &&
        //test move on ice inactive word
        t.checkExpect(this.ice.move(), new InactiveWord("ice", 50, 299))
        &&
        //test move on an empty word, should not move word
        t.checkExpect(this.mtString.move(), this.mtString)
        &&
        //test move on an empty list
        t.checkExpect(this.emptyList.move(), this.emptyList)
        &&
        //test move on a cons list with random active and inactive words
        t.checkExpect(this.listAIUtilT.move(), 
            new ConsLoWord(new ActiveWord(new Utils(new Random(22)).randomWord(), 
                IZTypeGame.RAND_COORD_X, IZTypeGame.RAND_COORD_Y).move(), 
            new ConsLoWord(new InactiveWord(new Utils(new Random(19)).randomWord(), 
                IZTypeGame.RAND_COORD_X, IZTypeGame.RAND_COORD_Y).move(), 
                new ConsLoWord(new InactiveWord(new Utils(new Random(21)).randomWord(), 
                    IZTypeGame.RAND_COORD_X, IZTypeGame.RAND_COORD_Y).move(),
                    new ConsLoWord(new InactiveWord(new Utils(new Random(23)).randomWord(), 
                        IZTypeGame.RAND_COORD_X, 
                        IZTypeGame.RAND_COORD_Y).move(), new MtLoWord().move())))));
  }
  
  //test reachBottom method
  boolean testReachBottom(Tester t) {
    //test on empty list
    return t.checkExpect(this.emptyList.reachBottom(), false)
        &&
        //test on list with one word that has not reached bottom
        t.checkExpect(this.listA.reachBottom(), false)
        &&
        //test on list with one word that has reached bottom
        t.checkExpect(new ConsLoWord(
            new InactiveWord("water", 320, 500), this.emptyList).reachBottom(), true)
        &&
        //test on list with multiple words that has not reached bottom
        t.checkExpect(this.listB.reachBottom(), false)
        &&
        //test on list with multiple words where one word in middle
        //of list has reached bottom
        t.checkExpect(new ConsLoWord(this.apple, 
            new ConsLoWord(this.orange,
                new ConsLoWord(new ActiveWord("car", 400, 500), 
                    new ConsLoWord(this.ice, this.emptyList)))).reachBottom(), true);
  }
  
  //test reachBottomHelper method
  boolean testReachBottomHelper(Tester t) {
    //test for word that hasn't reached bottom
    return t.checkExpect((new ActiveWord("astrid", 10, 10)).reachBottomHelper(), false)
        &&
        //test for word that has reached bottom
        t.checkExpect((new InactiveWord("miguel", 50, 500)).reachBottomHelper(), true)
        &&
        //test for word that has reached coordinate that is 20 away from bottom
        //should return true because word border has reached bottom
        t.checkExpect((new InactiveWord("yes", 50, 480)).reachBottomHelper(), true);
  }
   
  //test scoreWord method
  boolean testScoreWord(Tester t) {
    // test for case where word is not empty, returns score 0
    return t.checkExpect(this.apple.scoreWord(), 0)
        && 
        //test for case where word is empty, returns score 1+
        t.checkExpect((new ActiveWord("", 10, 10).scoreWord()), 1);
  }
  
  //test drawScoreHelp method
  boolean testDrawScoreHelp(Tester t) {
    //test for case that has no empties, returns score 0
    return t.checkExpect(this.listA.drawScoreHelp(), 0)
        && 
        //test for case that has empties, returns score 1+ 
        t.checkExpect(this.mtStringList.drawScoreHelp(), 1)
        &&
        //test for case where list has two empties mixed in 
        //with words
        t.checkExpect(this.someMtStringsList.drawScoreHelp(), 2);
  }
  
  
  
  //test checkAndReduce method
  boolean testCheckAndReduce(Tester t) {
    //check and reduces an item in the list
    return t.checkExpect(this.listB.checkAndReduce("a"), new ConsLoWord(this.orange, 
            new ConsLoWord(new ActiveWord("pple", 250, 250), this.emptyList)))
        && 
        //check and reduces a list with no characters with that letter
        t.checkExpect(this.listB.checkAndReduce("x"), new ConsLoWord(this.orange,
            new ConsLoWord(this.apple, this.emptyList)))
        && 
        //checks and reduces a empty list
        t.checkExpect(this.emptyList.checkAndReduce("a"), this.emptyList)
        && 
        //check and reduces a list with multiple items starting with string
        t.checkExpect((new ConsLoWord(this.ice, 
            new ConsLoWord(this.idea, this.emptyList))).checkAndReduce("i"), 
            new ConsLoWord((new ActiveWord("ce", 50, 289)), 
                new ConsLoWord((new ActiveWord("dea", 50, 150)), this.emptyList)))
        &&
        //check and reduces a list with multiple items starting with string
        //mixed in with words do not start with string
        t.checkExpect((new ConsLoWord(this.igloo, 
            new ConsLoWord(this.apple, 
                new ConsLoWord(this.ice, 
                    new ConsLoWord(this.mango, this.emptyList))))).checkAndReduce("i"), 
            new ConsLoWord((new ActiveWord("gloo", 400, 290)),
                new ConsLoWord(this.apple, 
                    new ConsLoWord((new ActiveWord("ce", 50, 289)), 
                        new ConsLoWord(this.mango, this.emptyList)))));
  }
  
  //test checkAndReduceHelper method
  boolean testCheckAndReduceHelper(Tester t) {
    //check and reduce an empty word
    return t.checkExpect(this.mtStringRand.checkAndReduceHelp("a"), this.mtStringRand) 
        && 
        //check and reduce an active word
        t.checkExpect(this.apple.checkAndReduceHelp("a"), new ActiveWord("pple", 250, 250))     
        && 
        //check and reduce an inactive word
        t.checkExpect(this.ice.checkAndReduceHelp("i"), new ActiveWord("ce", 50, 289))     
        &&
        //check and reduce an active word that does not start with the given string
        t.checkExpect(this.apple.checkAndReduceHelp("i"), new ActiveWord("apple", 250, 250))
        && 
        //check and reduce an inactive word that does not start with the given string
        t.checkExpect(this.ice.checkAndReduceHelp("a"), new InactiveWord("ice", 50, 289)); 
  }
 
  //test addWord
  boolean testAddWord(Tester t) {
    //test on empty list
    return t.checkExpect(this.emptyList.addWordForTesting(), 
         new ConsLoWord(new InactiveWord(new Utils(new Random(20)).randomWord(),
             IZTypeGame.RAND_COORD_X, IZTypeGame.RAND_COORD_Y), this.emptyList))
        &&
        //test on list with one word
        t.checkExpect(this.listA.addWordForTesting(),
            new ConsLoWord(new InactiveWord(new Utils(new Random(21)).randomWord(), 
                IZTypeGame.RAND_COORD_X, IZTypeGame.RAND_COORD_Y), 
                this.listA))
        &&
        //test on list multiple words
        t.checkExpect(this.listAIUtil.addWordForTesting(), 
            new ConsLoWord(new InactiveWord(new Utils(new Random(21)).randomWord(), 
                IZTypeGame.RAND_COORD_X, IZTypeGame.RAND_COORD_Y), 
                this.listAIUtil));    
  }
 
  //test the util methods
  boolean testZTypeWorldUtils(Tester t) {
    //test random Utils method with 25 as random seed
    return t.checkExpect(new Utils(new Random(25)).randomWord(), "gdwnwx")
        &&
        //test random Utilks method with 0 as random seed
        t.checkExpect(new Utils(new Random(0)).randomWord(), "kxewpd")
       &&
         t.checkExpect(new Utils(new Random(5)).randomWord(), "mryygf");
  }
}
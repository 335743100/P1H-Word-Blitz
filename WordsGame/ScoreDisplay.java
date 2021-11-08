import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;

/**
 * Write a description of class ScoreDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreDisplay extends Actor
{
    //declaring variables for the score display's image
    private GreenfootImage image;
    public static final int SCORE_DISPLAY_WIDTH = GameWorld.WIDTH;
    public static final int SCORE_DISPLAY_HEIGHT = GameWorld.HEIGHT / 11;
    public static final Color scoreColor = new Color(255, 255, 0);
    public static final Font scoreFont = MainMenu.highscoreFont;
    
    //declaring instance variables
    private int score;
    private String display;
    private int wpm;
    private int accuracy;
    
    public ScoreDisplay(int score){
        //setting the player's score
        this.score = score;
        wpm = 0;
        display = "SCORE: " + this.score;
        display += " WPM: 0";
        display += " ACCURACY: 0%";
        //creating and setting the image for the score display
        image =  new GreenfootImage(SCORE_DISPLAY_WIDTH + 1, SCORE_DISPLAY_HEIGHT + 1);
        drawScoreDisplay();
        setImage(image);
    }
    
    //method to update the player's score
    public void update(int score, int gameTime, int correct, int incorrect) 
    {
        //updating the score and the display
        this.score = score;
        display = "SCORE: " + this.score;
        if(gameTime / 60 != 0) wpm = 60*(score/5)/(gameTime/60);
        display += " WPM: " + wpm;
        this.accuracy = (int) Math.round((1.0*correct / (correct + incorrect)) * 100);
        display += " ACCURACY: " + this.accuracy + "%";
        drawScoreDisplay();
        setImage(image);
        
        if(UserInfo.isStorageAvailable()){
            if(score >= 200 && wpm >= 50 && MainMenu.user.getInt(0) == 0) MainMenu.user.setInt(0, 1);
            if(score >= 200 && wpm >= 80 && MainMenu.user.getInt(3) == 0) MainMenu.user.setInt(3, 1);
            if(score >= 200 && wpm >= 110 && MainMenu.user.getInt(6) == 0) MainMenu.user.setInt(6, 1);
            
            if(score >= 200 && accuracy >= 90 && MainMenu.user.getInt(1) == 0) MainMenu.user.setInt(1, 1);
            if(score >= 200 && accuracy >= 95 && MainMenu.user.getInt(4) == 0) MainMenu.user.setInt(4, 1);
            if(score >= 200 && accuracy >= 100 && MainMenu.user.getInt(7) == 0) MainMenu.user.setInt(7, 1);
            
            if(score >= 1000 && Difficulty.gameDifficulty == Difficulty.EASY && MainMenu.user.getInt(2) == 0) MainMenu.user.setInt(2, 1);
            if(score >= 1000 && Difficulty.gameDifficulty == Difficulty.NORMAL && MainMenu.user.getInt(5) == 0) MainMenu.user.setInt(5, 1);
            if(score >= 1000 && Difficulty.gameDifficulty == Difficulty.HARD && MainMenu.user.getInt(8) == 0) MainMenu.user.setInt(8, 1);
            
            MainMenu.user.store();
        }
    }
    
    //method to draw the score display
    private void drawScoreDisplay(){
        //drawing the display
        image.clear();
        image.setColor(scoreColor);
        image.setFont(scoreFont);
        image.drawString(display, SCORE_DISPLAY_WIDTH / 20, (SCORE_DISPLAY_HEIGHT + scoreFont.getSize() / 2) / 2);
    }
}

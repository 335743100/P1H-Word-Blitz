import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Queue;

/**
 * Displays the correct letters the player has typed in green on top of the 
 * word bank.
 * 
 * @author Edison Lim, Vaughn Chan, Jaylen Cheung
 * @version November 9, 2021
 */
public class CorrectWordOverlay extends Actor
{
    public static final int WIDTH = GameWorld.WIDTH * 10 / 17;
    public static final int HEIGHT = GameWorld.HEIGHT * 5 / 8;
    
    public static final Color correctColor = new Color(0, 255, 0);
    public static final Font textFont = new Font("Courier New", true, false, 30);
    
    public CorrectWordOverlay() {
        setImage(new GreenfootImage(WIDTH, HEIGHT));
    }
     
    /**
     * Display correct letters in green text over the existing ones to show which 
     * letters have been typed
     * @param word Current word
     * @param numCorrect Number of correct letters
     * @param clear If true, clear the actors image
     */
    public void setCorrectOverlay(String word, int numCorrect, boolean clear) {
        GreenfootImage display = new GreenfootImage(WIDTH, HEIGHT);
        String displayString = "";
        for(int i = 0; i < numCorrect; i++) displayString += Character.toString(word.charAt(i));
        display.setColor(correctColor);
        display.setFont(textFont);
        display.drawString(displayString, 20, 20);
        if(clear) display.clear();
        setImage(display);
    }
}
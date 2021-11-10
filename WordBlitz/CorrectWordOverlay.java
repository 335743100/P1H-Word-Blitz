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
    public static Font textFont = new Font("Courier New", true, false, 30);
    public static int WIDTH = 500;
    public static int HEIGHT = 300;
    
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
        for(int i = 0; i < numCorrect; i++){
            displayString += Character.toString(word.charAt(i));
        }
        display.setColor(Color.GREEN);
        display.setFont(textFont);
        display.drawString(displayString, 20, 20);
        if(clear) display.clear();
        setImage(display);
        if(clear) display.clear();
    }
}
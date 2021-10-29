import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Queue;

/**
 * Displays the letters the player has typed in another colour on top of the 
 * word bank.
 * 
 * @author Jaylen Cheung
 * @version 1.0.0
 */
public class CorrectWordOverlay extends Actor
{
    public static Font COURIER_NEW = new Font("Courier New", 20);
    public static int WIDTH = 500;
    public static int HEIGHT = 300;
    
    public CorrectWordOverlay() {
        setImage(new GreenfootImage(WIDTH, HEIGHT));
    }
     
    /*
     * Write letters in green text over the existing ones to show which 
     * letters have been typed
     * @param word Current word
     * @param numCompleteChars Number of correct chars the player has typed
     */
    public void setWordBox(String word, int numCorrectChars) {
        GreenfootImage display = new GreenfootImage(WIDTH, HEIGHT);
        String displayString = "";
        for (int i = 0; i < numCorrectChars; i++) {
            displayString += Character.toString(word.charAt(i));
        }
        display.setColor(Color.GREEN);
        display.setFont(COURIER_NEW);
        display.drawString(displayString, 20, 20);
        setImage(display);
    }
}


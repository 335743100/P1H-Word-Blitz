import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Queue;

/**
 * Displays the wrong letters the player has typed in red on top of the 
 * word bank.
 * 
 * @author Edison Lim, Vaughn Chan, Jaylen Cheung
 * @version November 9, 2021
 */
public class WrongWordOverlay extends Actor
{
    public static Font textFont = new Font("Courier New", true, false, 30);
    public static int WIDTH = 500;
    public static int HEIGHT = 300;
    
    public WrongWordOverlay() {
        setImage(new GreenfootImage(WIDTH, HEIGHT));
    }
    
    /**
     * Display wrong letters in red text over the existing ones to show which 
     * letters have been typed
     * @param word Current word
     * @param numCorrect Number of correct letters
     * @param numWrong Number of wrong letters
     * @param clear If true, clear the display
     */
    public void setWrongOverlay(String word, int numCorrect, int numWrong, boolean clear){
        GreenfootImage display = new GreenfootImage(WIDTH, HEIGHT);
        String displayString = "";
        for(int i = numCorrect; i < (numCorrect + numWrong); i++){
            displayString += Character.toString(word.charAt(i));
        }
        display.setColor(Color.RED);
        display.setFont(textFont);
        display.drawString(displayString, 20 + (int)((numCorrect) * textFont.getSize() * 0.6), 20);
        if(clear) display.clear();
        setImage(display);
        if(clear) display.clear();
    }
}
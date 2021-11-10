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
    public static final int WIDTH = GameWorld.WIDTH * 10 / 17;
    public static final int HEIGHT = GameWorld.HEIGHT * 5 / 8;
    
    public static final Color WRONG_COLOR = new Color(255, 0, 0);
    public static final Font OVERLAY_FONT = new Font("Courier New", true, false, 30);
    
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
        for(int i = numCorrect; i < (numCorrect + numWrong); i++) displayString += Character.toString(word.charAt(i));
        
        display.setColor(WRONG_COLOR);
        display.setFont(OVERLAY_FONT);
        display.drawString(displayString, WIDTH / 25 + (int)((numCorrect) * OVERLAY_FONT.getSize() * 0.6), HEIGHT / 15);
        if(clear) display.clear();
        setImage(display);
    }
}
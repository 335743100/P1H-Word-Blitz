import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Displays all the current letters in the word queue.
 * 
 * @author Edison Lim, Vaughn Chan, Jaylen Cheung
 * @version November 9, 2021
 */
public class WordBox extends Actor
{
    public static final int WIDTH = GameWorld.WIDTH * 6 / 17;
    public static final int HEIGHT = GameWorld.HEIGHT * 5 / 6;
    
    public static final Color wordColor = new Color(255, 255, 255);
    public static final Font wordFont = new Font("Courier New", 30);
    
    public static final int[] HEIGHTS = {24, 23, 22};
    
    public WordBox(Queue<String> queue) {
        setWordBox(queue);
    }
    
    /**
     * Update the word box with new words
     * @param queue Queue of words
     */
    public void setWordBox(Queue<String> queue) {
        GreenfootImage display = new GreenfootImage(WIDTH, HEIGHT);
        
        String displayString = "";
        ArrayList<String> list = new ArrayList<String>();
        
        while(!queue.isEmpty()) list.add(queue.remove());
        Collections.reverse(list); // So that the first word is on the bottom
        list.remove(0);
        for(String i : list) displayString += "\n\n" + i + "\n";
        
        display.setColor(wordColor);
        display.setFont(wordFont);
        display.drawString(displayString, 20, 20);
        setImage(display);
    }
}

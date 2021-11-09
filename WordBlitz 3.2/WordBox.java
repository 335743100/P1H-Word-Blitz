import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Displays all the current letters in the word queue.
 * 
 * @author Jaylen Cheung
 * @version 1.0.0
 */
public class WordBox extends Actor
{
    public static final int[] HEIGHTS = {24, 23, 22};
    public static final Font COURIER_NEW = new Font("Courier New", 30);
    public static final int WIDTH = 300;
    public static final int HEIGHT = 400;
    
    public WordBox(Queue<String> queue) {
        setWordBox(queue, false);
    }
    
    /*
     * Update the word box with new words
     * @param queue Queue of words
     * @param numCompleteChars Number of correct chars the player has type
     */
    public void setWordBox(Queue<String> queue, boolean slideWord) {
        GreenfootImage display = new GreenfootImage(WIDTH, HEIGHT);
        String displayString = "";
        ArrayList<String> list = new ArrayList<String>();
        
        display.setColor(Color.WHITE);
        display.setFont(COURIER_NEW);
        while (!queue.isEmpty()) {
            list.add(queue.remove());
        }
        Collections.reverse(list);
        list.remove(0);
        for (String i : list) {
            displayString += "\n\n" + i + "\n";
        }
        display.drawString(displayString, 20, 20);
        setImage(display);
    }
    
}

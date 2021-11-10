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
    public static final int[] HEIGHTS = {24, 23, 22};
    public static final Font COURIER_NEW = new Font("Courier New", 30);
    public static final int WIDTH = 300;
    public static final int HEIGHT = 400;
    
    public WordBox(Queue<String> queue) {
        setWordBox(queue);
    }
    
    /**
     * Update the word box with new words
     * @param queue Queue of words
     */
    public void setWordBox(Queue<String> queue) {
        GreenfootImage display = new GreenfootImage(WIDTH, HEIGHT);
        ArrayList<String> list = new ArrayList<String>();
        int y = 20;
        
        display.setColor(Color.WHITE);
        display.setFont(COURIER_NEW);
        while (!queue.isEmpty()) {
            list.add(queue.remove());
        }
        Collections.reverse(list); // So that the first word is on the bottom
        list.remove(0);
        for (String i : list) {
            display.drawString("\n" + i, 20, y);
            y += 113;
        }
        setImage(display);
    }
    
}

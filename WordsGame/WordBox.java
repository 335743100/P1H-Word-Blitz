import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Queue;
import java.util.LinkedList;

/**
 * Displays all the current letters in the word queue.
 * 
 * @author Jaylen Cheung
 * @version 1.0.0
 */
public class WordBox extends Actor
{
    public static Font COURIER_NEW = new Font("Courier New", 20);
    public static int WIDTH = 500;
    public static int HEIGHT = 300;
    
    public WordBox(Queue<String> queue) {
        setWordBox(queue);
    }
    
    /*
     * Update the word box with new words
     * @param queue Queue of words
     * @param numCompleteChars Number of correct chars the player has type
     */
    public void setWordBox(Queue<String> queue) {
        GreenfootImage display = new GreenfootImage(WIDTH, HEIGHT);
        String displayString = "";
        int length = 0;
        
        display.setColor(Color.WHITE);
        display.fill();
        display.setColor(Color.BLACK);
        display.drawRect(0, 0, WIDTH, HEIGHT);
        display.setFont(COURIER_NEW);
        while (!queue.isEmpty()) {
            String word = queue.remove();
            length += word.length() + 1;
            if (length >= 37) {
                displayString += "\n" + word;
                length = word.length() + 1;
            } else {
                displayString += word;
            }
        }
        display.drawString(displayString, 20, 20);
        setImage(display);
    }
}
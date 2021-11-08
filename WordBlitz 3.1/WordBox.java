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
    public static final int[] HEIGHTS = {24, 23, 22};
    public static final Font COURIER_NEW = new Font("Courier New", 20);
    public static final int WIDTH = 500;
    public static final int HEIGHT = 300;
    private SlidingWord slidingWord2 = new SlidingWord();
    
    public WordBox(Queue<String> queue) {
        setWordBox(queue, false);
    }
    
    public void addSlidingWord() {
        getWorld().addObject(slidingWord2, 400, 250);
    }
    
    /*
     * Update the word box with new words
     * @param queue Queue of words
     * @param numCompleteChars Number of correct chars the player has type
     */
    public void setWordBox(Queue<String> queue, boolean slideWord) {
        GreenfootImage display = new GreenfootImage(WIDTH, HEIGHT);
        String displayString = "";
        int length = 0;
        int rows = 0;
        int wordsInRow = 0;
        String slidingWord = "";
        int slidingX = 0;
        int slidingY = 0;
        int slidingYEnd = 0;
        
        display.setColor(Color.WHITE);
        display.fill();
        display.setColor(Color.BLACK);
        display.drawRect(0, 0, WIDTH, HEIGHT);
        display.setFont(COURIER_NEW);
        while (!queue.isEmpty()) {
            String word = queue.remove();
            if (slideWord) {
                if (queue.peek() == null) {
                    slidingWord = "";
                    if (length+word.length() >= 37) {
                        rows++;
                        slidingWord = word;
                    } else {
                        for (int i = 0; i < length-wordsInRow-1; i++) {
                            slidingWord += " ";
                        }
                        slidingWord += word;
                    }
                    slidingX = 20;
                    slidingY = 0;
                    int counter = 0;
                    for (int i = 0; i < rows; i++) {
                        if (counter == 3) {
                            counter = 0;
                            slidingYEnd += HEIGHTS[counter];
                        } else {
                            slidingYEnd += HEIGHTS[counter];
                            counter++;
                        }
                    }
                    slidingYEnd += 20;
                    slidingWord2.setSlidingWord(slidingWord, slidingX, slidingY, slidingYEnd);
                } else {
                    length += word.length() + 1;
                    if (length >= 37) {
                        displayString += "\n" + word;
                        length = word.length() + 1;
                        rows++;
                        wordsInRow = 0;
                    } else {
                        displayString += word;
                        wordsInRow++;
                    }
                }
            } else {
                length += word.length() + 1;
                if (length >= 37) {
                    displayString += "\n" + word;
                    length = word.length() + 1;
                    rows++;
                    wordsInRow = 0;
                } else {
                    displayString += word;
                    wordsInRow++;
                }
            }
        }
        display.drawString(displayString, 20, 20);
        setImage(display);
    }
    
}

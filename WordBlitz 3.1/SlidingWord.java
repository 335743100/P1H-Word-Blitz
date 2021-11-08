import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Displays all the current letters in the word queue.
 * 
 * @author Jaylen Cheung
 * @version 1.0.0
 */
public class SlidingWord extends Actor
{
    public static Font COURIER_NEW = new Font("Courier New", 20);
    public static int WIDTH = 500;
    public static int HEIGHT = 300;
    private String slidingWord = "";
    private int slidingX = 0;
    private int slidingY = 0;
    private int slidingYEnd = 0;
    
    public void act() {
        if (slidingWord.length() > 0) {
            if (slidingY < slidingYEnd) {
                GreenfootImage display = new GreenfootImage(WordBox.WIDTH, WordBox.HEIGHT);
                display.setFont(COURIER_NEW);
                display.setColor(Color.BLACK);
                display.drawString(slidingWord, slidingX, slidingY);
                slidingY+=3;
                setImage(display);
            } else {
                slidingWord = "";
            }
        }
    }
    
    public void setSlidingWord(String word, int x, int y, int y2) {
        slidingWord = word;
        slidingX = x;
        slidingY = y;
        slidingYEnd = y2;
    }
        
}

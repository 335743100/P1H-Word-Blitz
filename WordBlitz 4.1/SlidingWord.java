import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Slides a word into the WordBox
 * 
 * @author Edison Lim, Vaughn Chan, Jaylen Cheung
 * @version November 9, 2021
 */
public class SlidingWord extends Actor
{
    public static Font COURIER_NEW = new Font("Courier New", 30);
    public static Font BOLD_COURIER_NEW = new Font("Courier New", true, false, 30);
    public static int WIDTH = 850;
    public static int HEIGHT = 480;
    private String slidingWord = "";
    private int slidingX = 0;
    private int slidingY = 0;
    private int slidingYEnd = 0;
    private boolean isBold = false;
    
    public SlidingWord() {
        setImage(new GreenfootImage(WIDTH, HEIGHT));
    }
    
    /**
     * Act method. Slides a word if there is one in queue
     */
    public void act() {
        if (slidingWord.length() > 0) {
            if (slidingY < slidingYEnd) {
                GreenfootImage display = new GreenfootImage(WIDTH, HEIGHT);
                if (isBold) {
                    display.setFont(BOLD_COURIER_NEW);
                } else {
                    display.setFont(COURIER_NEW);
                }
                display.setColor(Color.WHITE);
                display.drawString(slidingWord, slidingX, slidingY);
                slidingY+=3;
                setImage(display);
            } else {
                slidingWord = "";
            }
        }
    }
    
    /**
     * Set a new word to slide
     * @param word Word to slide
     * @param x Starting x value
     * @param y Starting y value
     * @param y2 Ending y value
     * @param bold If sliding word is bold
     */
    public void setSlidingWord(String word, int x, int y, int y2, boolean bold) {
        slidingWord = word;
        slidingX = x;
        slidingY = y;
        slidingYEnd = y2;
        isBold = bold;
    }
        
}

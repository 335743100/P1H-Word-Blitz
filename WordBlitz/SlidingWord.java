import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Slides a word into the WordBox
 * 
 * @author Edison Lim, Vaughn Chan, Jaylen Cheung
 * @version November 9, 2021
 */
public class SlidingWord extends Actor
{
    public static final int WIDTH = GameWorld.WIDTH;
    public static final int HEIGHT = GameWorld.HEIGHT;
    
    public static final Color WORD_COLOR = new Color(255, 255, 255);
    public static final Font WORD_FONT = new Font("Courier New", 30);
    
    private String slidingWord = "";
    private int slidingX = 0;
    private int slidingY = 0;
    private int slidingYEnd = 0;
    
    public SlidingWord() {
        setImage(new GreenfootImage(WIDTH, HEIGHT));
    }
    
    /**
     * Act method. Slides a word if there is one in queue
     */
    public void act() {
        if(slidingWord.length() > 0){
            if(slidingY < slidingYEnd){
                GreenfootImage display = new GreenfootImage(WIDTH, HEIGHT);
                display.setColor(WORD_COLOR);
                display.setFont(WORD_FONT);
                display.drawString(slidingWord, slidingX, slidingY);
                slidingY+=3;
                setImage(display);
            }
            else slidingWord = "";
        }
    }
    
    /**
     * Set a new word to slide
     * @param word Word to slide
     * @param x Starting x value
     * @param y Starting y value
     * @param y2 Ending y value
     */
    public void setSlidingWord(String word, int x, int y, int y2) {
        slidingWord = word;
        slidingX = x;
        slidingY = y;
        slidingYEnd = y2;
    }
}

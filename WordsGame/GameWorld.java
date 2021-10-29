import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameWorld extends World
{
    
    private GreenfootImage background;
    public static final int WORLD_WIDTH = 800;
    public static final int WORLD_HEIGHT = 500;
    public static final Color bgColor = new Color(52, 232, 235);
    /**
     * Constructor for objects of class GameWorld.
     * 
     */
    public GameWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        background = new GreenfootImage(GameWorld.WORLD_WIDTH, GameWorld.WORLD_HEIGHT);
        background.setColor(bgColor);
        background.fill();
        setBackground(background);
    }
}

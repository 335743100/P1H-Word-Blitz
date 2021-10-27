import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainMenu extends World
{
    
    private GreenfootImage background;
    public static final Color menuColor = new Color(52, 232, 235);
    public static final Color titleColor = new Color(255, 0, 0);
    public static Font titleFont = new Font("Courier New", true, false, 100);
    private String title = "Word Blitz";
    
    private Button startButton, instructionsButton;
    private GreenfootSound clickSound = new GreenfootSound("Menu Click.wav");
    private MouseInfo mouse;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MainMenu()
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(GameWorld.WORLD_WIDTH, GameWorld.WORLD_HEIGHT, 1);
        background = new GreenfootImage(GameWorld.WORLD_WIDTH, GameWorld.WORLD_HEIGHT);
        background.setColor(menuColor);
        background.fill();
        setBackground(background);
        
        startButton = new Button("Start Game");
        addObject(startButton, GameWorld.WORLD_WIDTH / 2, GameWorld.WORLD_HEIGHT * 3 / 5);
        instructionsButton = new Button("How To Play");
        addObject(instructionsButton, GameWorld.WORLD_WIDTH / 2, GameWorld.WORLD_HEIGHT * 4 / 5);
    }
    
    public void act(){
        mouse = Greenfoot.getMouseInfo();
        if(Greenfoot.mouseClicked(startButton)){
            //menuMusic.stop();
            clickSound.play();
            Greenfoot.setWorld(new GameWorld());
        }
        else if(Greenfoot.mouseClicked(instructionsButton)){
            clickSound.play();
            Greenfoot.setWorld(new InstructionsMenu());
        }
    }
}
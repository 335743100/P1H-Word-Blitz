import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndScreen extends World
{
    private GreenfootImage background;
    public static final Color bgColor = new Color(52, 232, 235);
    
    private Button returnButton;
    private GreenfootSound clickSound = new GreenfootSound("Menu Click.wav");
    /**
     * Constructor for objects of class EndScreen.
     * 
     */
    public EndScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(GameWorld.WORLD_WIDTH, GameWorld.WORLD_HEIGHT, 1);
        background = new GreenfootImage(GameWorld.WORLD_WIDTH, GameWorld.WORLD_HEIGHT);
        background.setColor(bgColor);
        background.fill();
        setBackground(background);
        
        returnButton = new Button("Return", Color.BLACK, Color.WHITE, Color.RED);
        addObject(returnButton, GameWorld.WORLD_WIDTH / 8, GameWorld.WORLD_HEIGHT * 9 /10);
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(returnButton)){
            //menuMusic.stop();
            clickSound.play();
            Greenfoot.setWorld(new MainMenu());
        }
    }
}

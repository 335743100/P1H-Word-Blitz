import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InstructionsMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InstructionsMenu extends World
{
    
    private GreenfootImage background;
    public static final Color bgColor = new Color(52, 232, 235);
    public static final Color titleColor = new Color(255, 0, 0);
    public static Font titleFont = new Font("Courier New", true, false, 100);
    private String title = "nstructions";
    
    private Button backButton;
    private GreenfootSound clickSound = new GreenfootSound("Menu Click.wav");
    
    /**
     * Constructor for objects of class InstructionsMenu.
     * 
     */
    public InstructionsMenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(GameWorld.WORLD_WIDTH, GameWorld.WORLD_HEIGHT, 1);
        background = new GreenfootImage(GameWorld.WORLD_WIDTH, GameWorld.WORLD_HEIGHT);
        background.setColor(bgColor);
        background.fill();
        setBackground(background);
        
        backButton = new Button("Back", Color.BLACK, Color.WHITE, Color.RED);
        addObject(backButton, GameWorld.WORLD_WIDTH / 8, GameWorld.WORLD_HEIGHT * 9 /10);
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(backButton)){
            //menuMusic.stop();
            clickSound.play();
            Greenfoot.setWorld(new MainMenu());
        }
    }
}

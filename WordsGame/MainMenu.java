import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainMenu extends World
{
    
    private GreenfootImage background;
    private static final int WIDTH = GameWorld.WIDTH;
    private static final int HEIGHT = GameWorld.HEIGHT;
    public static final Color bgColor = new Color(52, 232, 235);
    public static final Color titleColor = new Color(255, 0, 0);
    public static Font titleFont = new Font("Courier New", true, false, HEIGHT / 10);
    public static final Color highscoreColor = new Color(245, 236, 76);
    public static final Font highscoreFont = new Font("Courier New", true, false, HEIGHT / 20);
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
        super(WIDTH, HEIGHT, 1);
        background = new GreenfootImage(WIDTH, HEIGHT);
        background.setColor(bgColor);
        background.fill();
        background.setColor(titleColor);
        background.setFont(titleFont);
        background.drawString(title, (getWidth() - (int)(title.length() * titleFont.getSize() * 0.58)) / 2, getHeight() / 5);
        setBackground(background);
        
        startButton = new Button("Start Game", Color.BLACK, Color.WHITE, highscoreColor);
        addObject(startButton, WIDTH / 2, HEIGHT * 3 / 5);
        instructionsButton = new Button("How To Play", Color.BLACK, Color.WHITE, highscoreColor);
        addObject(instructionsButton, WIDTH / 2, HEIGHT * 4 / 5);
    }
    
    public void act(){
        mouse = Greenfoot.getMouseInfo();
        if(Greenfoot.mouseClicked(startButton)){
            //menuMusic.stop();
            clickSound.play();
            //Greenfoot.setWorld(new GameWorld());
        }
        else if(Greenfoot.mouseClicked(instructionsButton)){
            clickSound.play();
            Greenfoot.setWorld(new InstructionsMenu());
        }
    }
}

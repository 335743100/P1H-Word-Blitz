import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Instruction world
 * 
 * @author Edison Lim, Vaughn Chan, Jaylen Cheung
 * @version November 9, 2021
 */
public class InstructionsMenu extends World
{
    public static final int WIDTH = GameWorld.WIDTH;
    public static final int HEIGHT = GameWorld.HEIGHT;
    
    private GreenfootImage background;
    public static final GreenfootImage BG_IMAGE = MainMenu.BG_IMAGE;
    public static final Color TITLE_COLOR = MainMenu.TITLE_COLOR;
    public static final Font TITLE_FONT = MainMenu.TITLE_FONT;
    private String title = "Instructions";
    public static final Color INSTRUCTIONS_COLOR = new Color(255, 255, 0);
    public static final Font INSTRUCTIONS_FONT = MainMenu.HIGHSCORE_FONT;
    private String instruction1 = "Type the words as fast as you can.";
    private String instruction2 = "Speed and accuracy count for achievements!";
    private String instruction3 = "The game ends when you run out of time.";
    private String instruction4 = "Have fun!";
    
    private Button backButton;
    private GreenfootSound clickSound = new GreenfootSound("Menu Click.wav");
    
    /**
     * Constructor for objects of class InstructionsMenu.
     * 
     */
    public InstructionsMenu()
    {    
        // Create a new world with WIDTH*HEIGHT cells with a cell size of 1x1 pixels.
        super(WIDTH, HEIGHT, 1);
        
        background = new GreenfootImage(WIDTH, HEIGHT);
        background.drawImage(BG_IMAGE, 0, 0);
        background.setColor(TITLE_COLOR);
        background.setFont(TITLE_FONT);
        background.drawString(title, (getWidth() - (int)(title.length() * TITLE_FONT.getSize() * 0.6)) / 2, getHeight() / 7);
        background.setColor(INSTRUCTIONS_COLOR);
        background.setFont(INSTRUCTIONS_FONT);
        background.drawString(instruction1, (getWidth() - (int)(instruction1.length() * INSTRUCTIONS_FONT.getSize() * 0.6)) / 2, getHeight() * 4 / 13);
        background.drawString(instruction2, (getWidth() - (int)(instruction2.length() * INSTRUCTIONS_FONT.getSize() * 0.6)) / 2, getHeight() * 6 / 13);
        background.drawString(instruction3, (getWidth() - (int)(instruction3.length() * INSTRUCTIONS_FONT.getSize() * 0.6)) / 2, getHeight() * 8 / 13);
        background.drawString(instruction4, (getWidth() - (int)(instruction4.length() * INSTRUCTIONS_FONT.getSize() * 0.6)) / 2, getHeight() * 10 / 13);
        setBackground(background);
        
        backButton = new Button("Back", Color.BLACK, TITLE_COLOR, Color.WHITE, Color.YELLOW, Color.RED);
        addObject(backButton, WIDTH / 7, HEIGHT * 9 /10);
    }
    
    /*
     * Act method. Check for button presses to change the world
     */
    public void act(){
        if(Greenfoot.mouseClicked(backButton) || (backButton.isHovering() && Greenfoot.isKeyDown("space"))){
            clickSound.play();
            Greenfoot.setWorld(new MainMenu());
        }
    }
}
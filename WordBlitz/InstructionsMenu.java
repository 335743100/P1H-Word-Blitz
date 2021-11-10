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
    public static final GreenfootImage bgImage = MainMenu.bgImage;
    public static final Color titleColor = MainMenu.titleColor;
    public static final Font titleFont = MainMenu.titleFont;
    private String title = "Instructions";
    public static final Color instructionsColor = new Color(255, 255, 0);
    public static final Font instructionsFont = MainMenu.highscoreFont;
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
        background.drawImage(bgImage, 0, 0);
        background.setColor(titleColor);
        background.setFont(titleFont);
        background.drawString(title, (getWidth() - (int)(title.length() * titleFont.getSize() * 0.58)) / 2, getHeight() / 7);
        background.setColor(instructionsColor);
        background.setFont(instructionsFont);
        background.drawString(instruction1, (getWidth() - (int)(instruction1.length() * instructionsFont.getSize() * 0.58)) / 2, getHeight() * 4 / 13);
        background.drawString(instruction2, (getWidth() - (int)(instruction2.length() * instructionsFont.getSize() * 0.58)) / 2, getHeight() * 6 / 13);
        background.drawString(instruction3, (getWidth() - (int)(instruction3.length() * instructionsFont.getSize() * 0.58)) / 2, getHeight() * 8 / 13);
        background.drawString(instruction4, (getWidth() - (int)(instruction4.length() * instructionsFont.getSize() * 0.58)) / 2, getHeight() * 10 / 13);
        setBackground(background);
        
        backButton = new Button("Back", Color.BLACK, titleColor, Color.WHITE, Color.YELLOW, Color.RED);
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
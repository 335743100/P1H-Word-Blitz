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
    private static final int WIDTH = GameWorld.WIDTH;
    private static final int HEIGHT = GameWorld.HEIGHT;
    public static final Color bgColor = new Color(52, 232, 235);
    public static final Color titleColor = new Color(255, 0, 0);
    public static Font titleFont = new Font("Courier New", true, false, HEIGHT / 10);
    private String title = "Instructions";
    public static final Color instructionsColor = new Color(245, 236, 76);
    public static final Font instructionsFont = new Font("Courier New", true, false, HEIGHT / 20);
    private String instruction1 = "Type the words as fast as you can.";
    private String instruction2 = "Speed and accuracy count!";
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
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WIDTH, HEIGHT, 1);
        background = new GreenfootImage(WIDTH, HEIGHT);
        background.setColor(bgColor);
        background.fill();
        background.setColor(titleColor);
        background.setFont(titleFont);
        background.drawString(title, (getWidth() - (int)(title.length() * titleFont.getSize() * 0.58)) / 2, getHeight() / 5);
        background.setColor(instructionsColor);
        background.setFont(instructionsFont);
        background.drawString(instruction1, (getWidth() - (int)(instruction1.length() * instructionsFont.getSize() * 0.58)) / 2, getHeight() / 3);
        background.drawString(instruction2, (getWidth() - (int)(instruction2.length() * instructionsFont.getSize() * 0.58)) / 2, getHeight() / 2);
        background.drawString(instruction3, (getWidth() - (int)(instruction3.length() * instructionsFont.getSize() * 0.58)) / 2, getHeight() * 2 / 3);
        background.drawString(instruction4, (getWidth() - (int)(instruction4.length() * instructionsFont.getSize() * 0.58)) / 2, getHeight() * 5 / 6);
        setBackground(background);
        
        backButton = new Button("Back", Color.BLACK, Color.WHITE, instructionsColor);
        addObject(backButton, WIDTH / 8, HEIGHT * 9 /10);
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(backButton)){
            //menuMusic.stop();
            clickSound.play();
            Greenfoot.setWorld(new MainMenu());
        }
    }
}
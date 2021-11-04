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
    private static final int WIDTH = GameWorld.WIDTH;
    private static final int HEIGHT = GameWorld.HEIGHT;
    public static final Color bgColor = new Color(52, 232, 235);
    public static final Color titleColor = new Color(255, 0, 0);
    public static Font titleFont = new Font("Courier New", true, false, HEIGHT / 10);
    private String title = "GAME OVER";
    public static final Color scoreColor = new Color(245, 236, 76);
    public static final Font scoreFont = new Font("Courier New", true, false, HEIGHT / 20);
    private String score;
    
    private Button returnButton;
    private GreenfootSound clickSound = new GreenfootSound("Menu Click.wav");
    /**
     * Constructor for objects of class EndScreen.
     * 
     */
    public EndScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WIDTH, HEIGHT, 1);
        
        score = "SCORE: " + Integer.toString(GameWorld.score);
        
        background = new GreenfootImage(WIDTH, HEIGHT);
        background.setColor(bgColor);
        background.fill();
        background.setColor(titleColor);
        background.setFont(titleFont);
        background.drawString(title, (getWidth() - (int)(title.length() * titleFont.getSize() * 0.58)) / 2, getHeight() / 5);
        background.setColor(scoreColor);
        background.setFont(scoreFont);
        background.drawString(score, (getWidth() - (int)(score.length() * scoreFont.getSize() * 0.58)) / 2, getHeight() / 3);
        setBackground(background);
        
        returnButton = new Button("Return", Color.BLACK, Color.WHITE, scoreColor);
        addObject(returnButton, WIDTH / 8, HEIGHT * 9 /10);
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(returnButton)){
            //menuMusic.stop();
            clickSound.play();
            Greenfoot.setWorld(new MainMenu());
        }
    }
}

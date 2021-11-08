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
    private GreenfootImage bgImage = new GreenfootImage("MenuBackground.jpg");
    public static final Color titleColor = new Color(255, 0, 255);
    public static Font titleFont = new Font("Courier New", true, false, HEIGHT / 10);
    private String title = "GAME OVER";
    public static final Color scoreColor = new Color(255, 255, 0);
    public static final Font scoreFont = new Font("Courier New", true, false, HEIGHT / 20);
    private String score;
    private String highscore = "NEW HIGHSCORE!";
    
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
        background.drawImage(bgImage, 0, 0);
        background.setColor(titleColor);
        background.setFont(titleFont);
        background.drawString(title, (getWidth() - (int)(title.length() * titleFont.getSize() * 0.58)) / 2, getHeight() / 7);
        background.setColor(scoreColor);
        background.setFont(scoreFont);
        background.drawString(score, (getWidth() - (int)(score.length() * scoreFont.getSize() * 0.58)) / 2, getHeight() / 3);
        if(UserInfo.isStorageAvailable()){
            MainMenu.user = UserInfo.getMyInfo();
            if( Difficulty.gameDifficulty == Difficulty.EASY && GameWorld.score > Integer.parseInt(MainMenu.user.getString(0))){
                background.drawString(highscore, (getWidth() - (int)(highscore.length() * scoreFont.getSize() * 0.58)) / 2, getHeight() / 2);
                MainMenu.user.setString(0, Integer.toString(GameWorld.score));
            }
            else if(Difficulty.gameDifficulty == Difficulty.NORMAL && GameWorld.score > Integer.parseInt(MainMenu.user.getString(1))){
                background.drawString(highscore, (getWidth() - (int)(highscore.length() * scoreFont.getSize() * 0.58)) / 2, getHeight() / 2);
                MainMenu.user.setString(1, Integer.toString(GameWorld.score));
            }
            else if(Difficulty.gameDifficulty == Difficulty.HARD && GameWorld.score > Integer.parseInt(MainMenu.user.getString(2))){
                background.drawString(highscore, (getWidth() - (int)(highscore.length() * scoreFont.getSize() * 0.58)) / 2, getHeight() / 2);
                MainMenu.user.setString(2, Integer.toString(GameWorld.score));
            }
            MainMenu.user.setInt(9, MainMenu.user.getInt(9) + 1);
            MainMenu.user.store();
        }
        setBackground(background);
        
        returnButton = new Button("Return", Color.BLACK, titleColor, Color.WHITE, Color.YELLOW, Color.RED);
        addObject(returnButton, WIDTH / 7, HEIGHT * 9 /10);
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(returnButton) || (returnButton.isHovering() && Greenfoot.isKeyDown("space"))){
            //menuMusic.stop();
            clickSound.play();
            Greenfoot.setWorld(new MainMenu());
        }
    }
}

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
    public static final Color highscoreColor = new Color(0, 0, 255);
    public static final Font highscoreFont = new Font("Courier New", true, false, HEIGHT / 20);
    private String easyHighscore, normalHighscore, hardHighscore;

    private String title = "Word Blitz";
    
    private Button startButton, instructionsButton, difficultyButton;
    private GreenfootSound clickSound = new GreenfootSound("Menu Click.wav");
    private MouseInfo mouse;
    
    public static UserInfo user;
    
    private enum Difficulty {
        EASY (0.5f),
        NORMAL (1.5f),
        HARD (2.5f),
        NOT_SET(0.5f);
        
        public final float speed;
        
        private Difficulty(float speed) {
            this.speed = speed;
        }
    }
    private Difficulty currentDifficulty = Difficulty.NOT_SET;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MainMenu()
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(WIDTH, HEIGHT, 1);
        
        if(UserInfo.isStorageAvailable()){ //update highscore (from Greenfoot UserInfo API)
            user = UserInfo.getMyInfo();
        }
        easyHighscore = "EASY HIGHSCORE: " + user.getInt(0);
        normalHighscore = "NORMAL HIGHSCORE: " + user.getInt(1);
        hardHighscore = "HARD HIGHSCORE: " + user.getInt(2);
        
        background = new GreenfootImage(WIDTH, HEIGHT);
        background.setColor(bgColor);
        background.fill();
        background.setColor(titleColor);
        background.setFont(titleFont);
        background.drawString(title, (getWidth() - (int)(title.length() * titleFont.getSize() * 0.58)) / 2, getHeight() / 5);
        background.setColor(highscoreColor);
        background.setFont(highscoreFont);
        background.drawString(easyHighscore, (getWidth() - (int)(easyHighscore.length() * highscoreFont.getSize() * 0.58)) / 2, getHeight() * 4 / 13);
        background.drawString(normalHighscore, (getWidth() - (int)(normalHighscore.length() * highscoreFont.getSize() * 0.58)) / 2, getHeight() * 5 / 13);
        background.drawString(hardHighscore, (getWidth() - (int)(hardHighscore.length() * highscoreFont.getSize() * 0.58)) / 2, getHeight() * 6 / 13);
        setBackground(background);
        
        startButton = new Button("Start Game", Color.BLACK, Color.WHITE, Color.BLUE);
        addObject(startButton, WIDTH / 2, (int)(HEIGHT * 3.0 / 5));
        
        difficultyButton = new Button("Difficulty", Color.BLACK, Color.WHITE, Color.BLUE);
        addObject(difficultyButton, WIDTH / 2, (int)(HEIGHT * 3.7 / 5));
        
        instructionsButton = new Button("How To Play", Color.BLACK, Color.WHITE, Color.BLUE);
        addObject(instructionsButton, WIDTH / 2, (int)(HEIGHT * 4.4 / 5));
    }
    
    public void act(){
        mouse = Greenfoot.getMouseInfo();
        if(Greenfoot.mouseClicked(startButton)){
            clickSound.play();
            Greenfoot.setWorld(new GameWorld(currentDifficulty.speed));
        }
        else if (Greenfoot.mouseClicked(difficultyButton)){
            clickSound.play();
            changeDifficulty();
        }
        else if(Greenfoot.mouseClicked(instructionsButton)){
            clickSound.play();
            Greenfoot.setWorld(new InstructionsMenu());
        }
    }
    
    public void changeDifficulty() {
        switch(currentDifficulty) {
            case EASY:
                difficultyButton.update("Normal");
                difficultyButton.changeColor(Color.BLACK, Color.YELLOW);
                currentDifficulty = Difficulty.NORMAL;
                break;
            case NORMAL:
                difficultyButton.update("Hard");
                currentDifficulty = Difficulty.HARD;
                difficultyButton.changeColor(Color.BLACK, Color.RED);
                break;
            case HARD:
                difficultyButton.update("Easy");
                currentDifficulty = Difficulty.EASY;
                difficultyButton.changeColor(Color.BLACK, Color.GREEN);
                break;
            default:
                difficultyButton.update("Easy");
                currentDifficulty = Difficulty.EASY;
                difficultyButton.changeColor(Color.BLACK, Color.GREEN);
                break;
        }
    }
}

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
    private GreenfootImage bgImage = new GreenfootImage("MenuBackground.jpg");
    public static final Color bgColor = new Color(52, 232, 235);
    public static final Color titleColor = new Color(255, 0, 0);
    public static Font titleFont = new Font("Courier New", true, false, HEIGHT / 10);
    public static final Color highscoreColor = new Color(255, 255, 0);
    public static final Font highscoreFont = new Font("Courier New", true, false, HEIGHT / 20);
    private String easyHighscore, normalHighscore, hardHighscore;

    private String title = "Word Blitz";
    
    private Button startButton, instructionsButton, difficultyButton;
    private int delay = 0;
    private GreenfootSound clickSound = new GreenfootSound("Menu Click.wav");
    private GreenfootSound errorSound = new GreenfootSound("Wrong.wav");
    private MouseInfo mouse;
    
    public static UserInfo user;

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
            //user.setInt(0, 0);
            //user.setInt(1, 0);
            //user.setInt(2, 0);
            //user.store();
            //uncomment the four lines above to reset the user's highscore
        }
        easyHighscore = "EASY HIGHSCORE: " + user.getInt(0);
        normalHighscore = "NORMAL HIGHSCORE: " + user.getInt(1);
        hardHighscore = "HARD HIGHSCORE: " + user.getInt(2);
        
        background = new GreenfootImage(WIDTH, HEIGHT);
        background.drawImage(bgImage, 0, 0);
        background.setColor(titleColor);
        background.setFont(titleFont);
        background.drawString(title, (getWidth() - (int)(title.length() * titleFont.getSize() * 0.58)) / 2, getHeight() / 5);
        background.setColor(highscoreColor);
        background.setFont(highscoreFont);
        background.drawString(easyHighscore, (getWidth() - (int)(easyHighscore.length() * highscoreFont.getSize() * 0.58)) / 2, getHeight() * 4 / 13);
        background.drawString(normalHighscore, (getWidth() - (int)(normalHighscore.length() * highscoreFont.getSize() * 0.58)) / 2, getHeight() * 5 / 13);
        background.drawString(hardHighscore, (getWidth() - (int)(hardHighscore.length() * highscoreFont.getSize() * 0.58)) / 2, getHeight() * 6 / 13);
        setBackground(background);
        
        startButton = new Button("Start Game", Color.BLACK, Color.GREEN, Color.WHITE, Color.YELLOW, Color.RED);
        addObject(startButton, WIDTH / 2, (int)(HEIGHT * 3.0 / 5));
        
        difficultyButton = new Button("Difficulty", Color.BLACK, Color.GREEN, Color.WHITE, Color.BLUE, Color.RED);
        addObject(difficultyButton, WIDTH / 2, (int)(HEIGHT * 3.7 / 5));
        
        instructionsButton = new Button("How To Play", Color.BLACK, Color.GREEN, Color.WHITE, Color.YELLOW, Color.RED);
        addObject(instructionsButton, WIDTH / 2, (int)(HEIGHT * 4.4 / 5));
    }
    
    public void act(){
        mouse = Greenfoot.getMouseInfo();
        if((Greenfoot.mouseClicked(startButton) || (startButton.isHovering() && Greenfoot.isKeyDown("space"))) && currentDifficulty != Difficulty.NOT_SET){
            clickSound.play();
            Greenfoot.setWorld(new GameWorld(currentDifficulty.speed));
        }
        else if((Greenfoot.mouseClicked(startButton) || (startButton.isHovering() && Greenfoot.isKeyDown("space"))) && currentDifficulty == Difficulty.NOT_SET){
            errorSound.play();
            difficultyButton.flash(30);
        }
        else if (Greenfoot.mouseClicked(difficultyButton) || (difficultyButton.isHovering() && Greenfoot.isKeyDown("space"))){
            if(delay == 0){
                clickSound.play();
                changeDifficulty();
                delay = 10;
            }
        }
        else if(Greenfoot.mouseClicked(instructionsButton) || (instructionsButton.isHovering() && Greenfoot.isKeyDown("space"))){
            clickSound.play();
            Greenfoot.setWorld(new InstructionsMenu());
        }
        if(delay > 0) delay--;
    }
    
    public void changeDifficulty() {
        switch(currentDifficulty) {
            case EASY:
                difficultyButton.update("Normal");
                difficultyButton.changeColor(Color.BLACK, Color.YELLOW);
                currentDifficulty = Difficulty.NORMAL;
                currentDifficulty.gameDifficulty = Difficulty.NORMAL;
                break;
            case NORMAL:
                difficultyButton.update("Hard");
                difficultyButton.changeColor(Color.BLACK, Color.RED);
                currentDifficulty = Difficulty.HARD;
                currentDifficulty.gameDifficulty = Difficulty.HARD;
                break;
            case HARD:
                difficultyButton.update("Easy");
                difficultyButton.changeColor(Color.BLACK, Color.GREEN);
                currentDifficulty = Difficulty.EASY;
                currentDifficulty.gameDifficulty = Difficulty.EASY;
                break;
            default:
                difficultyButton.update("Easy");
                currentDifficulty = Difficulty.EASY;
                difficultyButton.changeColor(Color.BLACK, Color.GREEN);
                currentDifficulty.gameDifficulty = Difficulty.EASY;
                break;
        }
    }
}

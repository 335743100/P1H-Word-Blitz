import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

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
    
    private ArrayList<Integer> medals;
    
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
        
        medals = ScoreDisplay.medalsUnlocked;
        
        background = new GreenfootImage(WIDTH, HEIGHT);
        background.drawImage(bgImage, 0, 0);
        background.setColor(titleColor);
        background.setFont(titleFont);
        background.drawString(title, (getWidth() - (int)(title.length() * titleFont.getSize() * 0.58)) / 2, getHeight() / 7);
        background.setColor(scoreColor);
        background.setFont(scoreFont);
        background.drawString(score, (getWidth() - (int)(score.length() * scoreFont.getSize() * 0.58)) / 2, getHeight() / 4);
        if(UserInfo.isStorageAvailable()){
            MainMenu.user = UserInfo.getMyInfo();
            if( Difficulty.gameDifficulty == Difficulty.EASY && GameWorld.score > Integer.parseInt(MainMenu.user.getString(0))){
                background.drawString(highscore, (getWidth() - (int)(highscore.length() * scoreFont.getSize() * 0.58)) / 2, getHeight() * 5 / 14);
                MainMenu.user.setString(0, Integer.toString(GameWorld.score));
            }
            else if(Difficulty.gameDifficulty == Difficulty.NORMAL && GameWorld.score > Integer.parseInt(MainMenu.user.getString(1))){
                background.drawString(highscore, (getWidth() - (int)(highscore.length() * scoreFont.getSize() * 0.58)) / 2, getHeight() * 5 / 14);
                MainMenu.user.setString(1, Integer.toString(GameWorld.score));
            }
            else if(Difficulty.gameDifficulty == Difficulty.HARD && GameWorld.score > Integer.parseInt(MainMenu.user.getString(2))){
                background.drawString(highscore, (getWidth() - (int)(highscore.length() * scoreFont.getSize() * 0.58)) / 2, getHeight() * 5 / 14);
                MainMenu.user.setString(2, Integer.toString(GameWorld.score));
            }
            MainMenu.user.setInt(9, MainMenu.user.getInt(9) + 1);
            MainMenu.user.store();
            if(MainMenu.user.getInt(9) >= 100) medals.add(9);
        }
        if(medals.size() > 0) background.drawString("NEW ACHIEVEMENTS!", (getWidth() - (int)(17 * scoreFont.getSize() * 0.58)) / 2, getHeight() * 6 / 7);
        setBackground(background);
        
        addMedals();
        
        returnButton = new Button("Return", Color.BLACK, titleColor, Color.WHITE, Color.YELLOW, Color.RED);
        addObject(returnButton, WIDTH / 7, HEIGHT * 9 /10);
    }
    
    private void addMedals(){
        int numMedals = medals.size();
        int medal = 0;
        if(numMedals == 1){
            addObject(determineMedal(medals.get(0)), WIDTH / 2, HEIGHT * 17 / 28);
        }
        else if(numMedals == 2){
            int x = 2;
            for(int i = 0; i < 2; i++){
                addObject(determineMedal(medals.get(i)), WIDTH * x / 5, HEIGHT * 17 / 28);
                x++;
            }
        }
        else if(numMedals == 3){
            int x = 3;
            for(int i = 0; i < 3; i++){
                addObject(determineMedal(medals.get(i)), WIDTH * x / 10, HEIGHT * 17 / 28);
                x += 2;
            }
        }
        else if(numMedals == 4){
            int x = 1;
            for(int i = 0; i < 4; i++){
                addObject(determineMedal(medals.get(i)), WIDTH * x / 5, HEIGHT * 17 / 28);
                x++;
            }
        }
        else if(numMedals == 5){
            int x = 1;
            for(int i = 0; i < 5; i++){
                addObject(determineMedal(medals.get(i)), WIDTH * x / 10, HEIGHT * 17 / 28);
                x += 2;
            }
        }
        else if(numMedals == 6){
            int x = 3;
            for(int i = 0; i < 3; i++){
                addObject(determineMedal(medals.get(i)), WIDTH * x / 10, HEIGHT * 1 / 2);
                x += 2;
            }
            x = 3;
            for(int i = 3; i < 6; i++){
                addObject(determineMedal(medals.get(i)), WIDTH * x / 10, HEIGHT * 5 / 7);
                x += 2;
            }
        }
        else if(numMedals == 7){
            int x = 3;
            for(int i = 0; i < 3; i++){
                addObject(determineMedal(medals.get(i)), WIDTH * x / 10, HEIGHT * 1 / 2);
                x += 2;
            }
            x = 1;
            for(int i = 3; i < 7; i++){
                addObject(determineMedal(medals.get(i)), WIDTH * x / 5, HEIGHT * 5 / 7);
                x++;
            }
        }
        else if(numMedals == 8){
            int x = 1;
            for(int i = 0; i < 4; i++){
                addObject(determineMedal(medals.get(i)), WIDTH * x / 5, HEIGHT * 1 / 2);
                x++;
            }
            x = 1;
            for(int i = 4; i < 8; i++){
                addObject(determineMedal(medals.get(i)), WIDTH * x / 5, HEIGHT * 5 / 7);
                x++;
            }
        }
        else if(numMedals == 9){
            int x = 1;
            for(int i = 0; i < 4; i++){
                addObject(determineMedal(medals.get(i)), WIDTH * x / 5, HEIGHT * 1 / 2);
                x++;
            }
            x = 1;
            for(int i = 4; i < 9; i++){
                addObject(determineMedal(medals.get(i)), WIDTH * x / 10, HEIGHT * 5 / 7);
                x += 2;
            }
        }
        else if(numMedals == 10){
            int x = 1;
            for(int i = 0; i < 5; i++){
                addObject(determineMedal(medals.get(i)), WIDTH * x / 10, HEIGHT * 1 / 2);
                x += 2;
            }
            x = 1;
            for(int i = 5; i < 10; i++){
                addObject(determineMedal(medals.get(i)), WIDTH * x / 10, HEIGHT * 5 / 7);
                x += 2;
            }
        }
    }
    
    private Achievement determineMedal(int index){
        Achievement medal;
        if(index == 0) medal = new Achievement("name", "bronze", true);
        else if(index == 1) medal = new Achievement("name", "bronze", true);
        else if(index == 2) medal = new Achievement("name", "bronze", true);
        else if(index == 3) medal = new Achievement("name", "silver", true);
        else if(index == 4) medal = new Achievement("name", "silver", true);
        else if(index == 5) medal = new Achievement("name", "silver", true);
        else if(index == 6) medal = new Achievement("name", "gold", true);
        else if(index == 7) medal = new Achievement("name", "gold", true);
        else if(index == 8) medal = new Achievement("name", "gold", true);
        else medal = new Achievement("name", "diamond", true);
        return medal;
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(returnButton) || (returnButton.isHovering() && Greenfoot.isKeyDown("space"))){
            //menuMusic.stop();
            clickSound.play();
            Greenfoot.setWorld(new MainMenu());
        }
    }
}

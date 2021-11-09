import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AchievementsMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AchievementsMenu extends World
{
    private GreenfootImage background;
    private static final int WIDTH = GameWorld.WIDTH;
    private static final int HEIGHT = GameWorld.HEIGHT;
    private GreenfootImage bgImage = new GreenfootImage("MenuBackground.jpg");
    public static final Color titleColor = new Color(255, 0, 255);
    public static Font titleFont = new Font("Courier New", true, false, HEIGHT / 10);
    private String title = "Achievements";
    public static final Color achievementsColor = new Color(255, 255, 0);
    public static final Font achievementsFont = new Font("Courier New", true, false, HEIGHT / 20);
    
    private Button backButton;
    private GreenfootSound clickSound = new GreenfootSound("Menu Click.wav");
    
    /**
     * Constructor for objects of class AchievementsMenu.
     * 
     */
    public AchievementsMenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WIDTH, HEIGHT, 1);
        background = new GreenfootImage(WIDTH, HEIGHT);
        background.drawImage(bgImage, 0, 0);
        background.setColor(titleColor);
        background.setFont(titleFont);
        background.drawString(title, (getWidth() - (int)(title.length() * titleFont.getSize() * 0.58)) / 2, getHeight() / 7);
        background.setColor(achievementsColor);
        background.setFont(achievementsFont);
        setBackground(background);
        
        if(UserInfo.isStorageAvailable()){
            if(MainMenu.user.getInt(0) == 0){ //70 wpm at 200 points
                Achievement bronze1 = new Achievement("name", "bronze", false, false);
                addObject(bronze1, WIDTH / 5, HEIGHT * 27 / 100);
            }
            else if(MainMenu.user.getInt(0) == 1){
                Achievement bronze1 = new Achievement("name", "bronze", true, false);
                addObject(bronze1, WIDTH / 5, HEIGHT * 27 / 100);
            }
            if(MainMenu.user.getInt(1) == 0){ //95% accuracy at 200 points
                Achievement bronze2 = new Achievement("name", "bronze", false, false);
                addObject(bronze2, WIDTH / 2, HEIGHT * 27 / 100);
            }
            else if(MainMenu.user.getInt(1) == 1){
                Achievement bronze2 = new Achievement("name", "bronze", true, false);
                addObject(bronze2, WIDTH / 2, HEIGHT * 27 / 100);
            }
            if(MainMenu.user.getInt(2) == 0){ //1000 points on easy
                Achievement bronze3 = new Achievement("name", "bronze", false, false);
                addObject(bronze3, WIDTH * 4 / 5, HEIGHT * 27 / 100);
            }
            else if(MainMenu.user.getInt(2) == 1){
                Achievement bronze3 = new Achievement("name", "bronze", true, false);
                addObject(bronze3, WIDTH * 4 / 5, HEIGHT * 27 / 100);
            }
            if(MainMenu.user.getInt(3) == 0){ //100 wpm at 200 points
                Achievement silver1 = new Achievement("name", "silver", false, false);
                addObject(silver1, WIDTH / 5, HEIGHT * 283 / 600);
            }
            else if(MainMenu.user.getInt(3) == 1){
                Achievement silver1 = new Achievement("name", "silver", true, false);
                addObject(silver1, WIDTH / 5, HEIGHT * 283 / 600);
            }
            if(MainMenu.user.getInt(4) == 0){ //97% accuracy at 200 points
                Achievement silver2 = new Achievement("name", "silver", false, false);
                addObject(silver2, WIDTH / 2, HEIGHT * 283 / 600);
            }
            else if(MainMenu.user.getInt(4) == 1){
                Achievement silver2 = new Achievement("name", "silver", true, false);
                addObject(silver2, WIDTH / 2, HEIGHT * 283 / 600);
            }
            if(MainMenu.user.getInt(5) == 0){ //1000 points on normal
                Achievement silver3 = new Achievement("name", "silver", false, false);
                addObject(silver3, WIDTH * 4 / 5, HEIGHT * 283 / 600);
            }
            else if(MainMenu.user.getInt(5) == 1){
                Achievement silver3 = new Achievement("name", "silver", true, false);
                addObject(silver3, WIDTH * 4 / 5, HEIGHT * 283 / 600);
            }
            if(MainMenu.user.getInt(6) == 0){ //130 wpm at 200 points
                Achievement gold1 = new Achievement("name", "gold", false, false);
                addObject(gold1, WIDTH / 5, HEIGHT * 101 / 150);
            }
            else if(MainMenu.user.getInt(6) == 1){
                Achievement gold1 = new Achievement("name", "gold", true, false);
                addObject(gold1, WIDTH / 5, HEIGHT * 101 / 150);
            }
            if(MainMenu.user.getInt(7) == 0){ //100% accuracy at 200 points
                Achievement gold2 = new Achievement("name", "gold", false, false);
                addObject(gold2, WIDTH / 2, HEIGHT * 101 / 150);
            }
            else if(MainMenu.user.getInt(7) == 1){
                Achievement gold2 = new Achievement("name", "gold", true, false);
                addObject(gold2, WIDTH / 2, HEIGHT * 101 / 150);
            }
            if(MainMenu.user.getInt(8) == 0){ //1000 points on hard
                Achievement gold3 = new Achievement("name", "gold", false, false);
                addObject(gold3, WIDTH * 4 / 5, HEIGHT * 101 / 150);
            }
            else if(MainMenu.user.getInt(8) == 1){
                Achievement gold3 = new Achievement("name", "gold", true, false);
                addObject(gold3, WIDTH * 4 / 5, HEIGHT * 101 / 150);
            }
            if(MainMenu.user.getInt(9) < 100){ //play 100 games
                Achievement diamond = new Achievement("name", "diamond", false, false);
                addObject(diamond, WIDTH / 2, HEIGHT * 7 / 8);
            }
            else if(MainMenu.user.getInt(9) >= 100){
                Achievement diamond = new Achievement("name", "diamond", true, false);
                addObject(diamond, WIDTH / 2, HEIGHT * 7 / 8);
            }
        }
        
        backButton = new Button("Back", Color.BLACK, titleColor, Color.WHITE, Color.YELLOW, Color.RED);
        addObject(backButton, WIDTH / 7, HEIGHT * 9 /10);
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(backButton) || (backButton.isHovering() && Greenfoot.isKeyDown("space"))){
            //menuMusic.stop();
            clickSound.play();
            Greenfoot.setWorld(new MainMenu());
        }
    }
}

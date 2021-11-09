import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AchievementDescription here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AchievementDescription extends Actor
{
    private GreenfootImage image;
    public static final int DESC_WIDTH = Achievement.MEDAL_WIDTH;
    public static final int DESC_HEIGHT = Achievement.MEDAL_HEIGHT;
    
    public AchievementDescription(String text){
        image = new GreenfootImage(DESC_WIDTH + 1, DESC_HEIGHT + 1);
        image.setColor(Color.BLACK);
        image.fill();
        image.setColor(Color.YELLOW);
        image.drawString(text, 0, 0);
        image.setTransparency(255);
        setImage(image);
    }
    
    /**
     * Act - do whatever the AchievementDescription wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }
    
    public void update(boolean show){
        if(show) image.setTransparency(255);
        else image.setTransparency(0);
        setImage(image);
    }
}

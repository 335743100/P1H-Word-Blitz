import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CheckInput here.
 * USED TO TEST INPUT, DELETE WHEN GAME IS FINISHED
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CheckInput extends Actor
{
    /**
     * Act - do whatever the CheckInput wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    GreenfootImage image;
    
    public static Font inputFont = new Font("Courier New", 20);
    
    public CheckInput(){
        setImage(new GreenfootImage(200, 100));
    }
    
    public void act() 
    {
        
    }
    
    public void drawInput(String input){
        GreenfootImage image = new GreenfootImage(200, 100);
        image.setFont(inputFont);
        image.setColor(Color.BLACK);
        image.drawString(input, 25, 25);
        setImage(image);
    }
}

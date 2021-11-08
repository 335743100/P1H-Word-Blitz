import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Achievement here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Achievement extends Actor
{
    private GreenfootImage image;
    public static final int MEDAL_WIDTH = 960 / 6;
    public static final int MEDAL_HEIGHT = 540 / 8;
    private Color nameColor = new Color(255, 255, 0);
    private Font nameFont = new Font("Courier New", true, true, MEDAL_HEIGHT / 6);
    private String name;
    
    public Achievement(String name, String type, boolean achieved){
        image = new GreenfootImage(MEDAL_WIDTH + 1, MEDAL_HEIGHT + 1);
        
        if(achieved){
            image.setColor(Color.BLUE);
            image.fill();
            if(type.equals("bronze")){
                
            }
            else if(type.equals("silver")){
                
            }
            else if(type.equals("gold")){
                
            }
            else if(type.equals("diamond")){
                
            }
        }
        else{ //set medal to black
            image.setColor(Color.RED);
            image.fill();
            if(type.equals("bronze")){
                
            }
            else if(type.equals("silver")){
                
            }
            else if(type.equals("gold")){
                
            }
            else if(type.equals("diamond")){
                
            }
        }
        
        
        this.name = name;
        image.setColor(nameColor);
        image.setFont(nameFont);
        image.drawString(name, (image.getWidth() - (int)(name.length() * nameFont.getSize() * 0.58)) / 2, (image.getHeight() * 9 / 10));
        setImage(image);
    }
    
    /**
     * Act - do whatever the Achievement wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}

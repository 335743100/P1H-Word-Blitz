import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Achievement here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Achievement extends Actor
{
    private String myName = "too fast";
    private GreenfootImage image;
    public static final int MEDAL_WIDTH = 960 / 6;
    public static final int MEDAL_HEIGHT = 540 / 6;
    private Color nameColor = new Color(255, 255, 0);
    private Font nameFont = new Font("Courier New", true, true, MEDAL_HEIGHT / 6);
    private String name, text;

    private boolean popup;
    private int time;

    private GreenfootImage bronzeMedal = new GreenfootImage("BronzeMedal.png");
    private GreenfootImage noBronzeMedal = new GreenfootImage("NoBronzeMedal.png");
    private GreenfootImage silverMedal = new GreenfootImage("SilverMedal.png");
    private GreenfootImage noSilverMedal = new GreenfootImage("NoSilverMedal.png");
    private GreenfootImage goldMedal = new GreenfootImage("GoldMedal.png");
    private GreenfootImage noGoldMedal = new GreenfootImage("NoGoldMedal.png");
    private GreenfootImage diamondMedal = new GreenfootImage("DiamondMedal.png");
    private GreenfootImage noDiamondMedal = new GreenfootImage("NoDiamondMedal.png");

    

    public Achievement(String name, String text, String type, boolean achieved, boolean popup){
        image = new GreenfootImage(MEDAL_WIDTH + 1, MEDAL_HEIGHT + 1);

        if(achieved){
            if(type.equals("bronze")){
                image.drawImage(bronzeMedal, MEDAL_WIDTH / 2 - 34, 0);
            }
            else if(type.equals("silver")){
                image.drawImage(silverMedal, MEDAL_WIDTH / 2 - 34, 0);
            }
            else if(type.equals("gold")){
                image.drawImage(goldMedal, MEDAL_WIDTH / 2 - 37, 0);
            }
            else if(type.equals("diamond")){
                image.drawImage(diamondMedal, MEDAL_WIDTH / 2 - 34, 0);
            }
        }
        else{ //set medal to black
            if(type.equals("bronze")){
                image.drawImage(noBronzeMedal, MEDAL_WIDTH / 2 - 34, 0);
            }
            else if(type.equals("silver")){
                image.drawImage(noSilverMedal, MEDAL_WIDTH / 2 - 34, 0);
            }
            else if(type.equals("gold")){
                image.drawImage(noGoldMedal, MEDAL_WIDTH / 2 - 37, 0);
            }
            else if(type.equals("diamond")){
                image.drawImage(noDiamondMedal, MEDAL_WIDTH / 2 - 34, 0);
            }
        }

        this.popup = popup;
        if(popup) time = 180;

        this.name = name;
        this.text = text;
        image.setColor(nameColor);
        image.setFont(nameFont);
        image.drawString(name, (image.getWidth() - (int)(name.length() * nameFont.getSize() * 0.58)) / 2, (image.getHeight() * 9 / 10));
        setImage(image);
    }

   

    /**
     * Act - do whatever the Achievement wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean showDescription;
    public void act() 
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse == null){
            return;
        }
        
        if(mouse.getActor() == this)
        {
            showDescription = true;
        }
        else if(mouse.getActor() != null)
        {
            showDescription = false;   
        }

        if(showDescription)
        {
            GreenfootImage image = new GreenfootImage(5,5);
            setImage(image);
        }
        else
        {
            setImage(image);
        }
        
        // if(popup){
            // if(time == 0) getWorld().removeObject(this);
            // else time--;
        // }

    }
}

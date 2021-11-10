import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * General achievement class
 * 
 * @author Edison Lim, Vaughn Chan, Jaylen Cheung
 * @version November 9, 2021
 */
public class Achievement extends Actor
{
    public static final int MEDAL_WIDTH = GameWorld.WIDTH * 96 / 425;
    public static final int MEDAL_HEIGHT = GameWorld.HEIGHT * 3 / 16;
    
    private GreenfootImage image;
    private GreenfootImage bronzeMedal = new GreenfootImage("BronzeMedal.png");
    private GreenfootImage noBronzeMedal = new GreenfootImage("NoBronzeMedal.png");
    private GreenfootImage silverMedal = new GreenfootImage("SilverMedal.png");
    private GreenfootImage noSilverMedal = new GreenfootImage("NoSilverMedal.png");
    private GreenfootImage goldMedal = new GreenfootImage("GoldMedal.png");
    private GreenfootImage noGoldMedal = new GreenfootImage("NoGoldMedal.png");
    private GreenfootImage diamondMedal = new GreenfootImage("DiamondMedal.png");
    private GreenfootImage noDiamondMedal = new GreenfootImage("NoDiamondMedal.png");
    public static final Color NAME_COLOR = new Color(255, 255, 0);
    public static final Font NAME_FONT = new Font("Courier New", true, true, MEDAL_HEIGHT / 6);
    private String name;
    public static final Color DESCRIPTION_COLOR = new Color(255, 255, 0);
    public static final Color DESCRIPTION_BG_COLOR = new Color(255, 0, 100, 50);
    public static final Font DESCRIPTION_FONT = new Font("Courier New", true, true, MEDAL_HEIGHT / 6);
    private String desc;
    
    private String type;
    private boolean achieved, popup;
    private int popupDuration;
    
    private String[] names = {"Gotta Go Fast", "Almost There", "Just Getting Started", "2 Fast 2 Furious", "Spelling Counts", "Too Easy", "Speeder", 
        "Perfectionist", "TryHard", "Veteran"};
    private String[] descriptions = {"Get 70 WPM At 200 Points", "Get 95% Accuracy At 200 Points", "Get 1000 Points On Easy Difficulty", 
        "Get 100 WPM At 200 Points", "Get 97% Accuracy At 200 Points", "Get 1000 Points On Normal Difficulty", 
        "Get 130 WPM At 200 Points", "Get 100% accuracy At 200 Points", "Get 1000 Points On Hard Difficulty", "Play 100 Games"};
    private String[] types = {"bronze", "bronze", "bronze", "silver", "silver","silver", "gold", "gold", "gold", "diamond"};
    
    private MouseInfo mouse;
    
    public Achievement(int index, boolean achieved, boolean popup){
        image = new GreenfootImage(MEDAL_WIDTH + 1, MEDAL_HEIGHT + 1);
        
        name = names[index];
        desc = descriptions[index];
        type = types[index];
        this.achieved = achieved;
        this.popup = popup;
        
        if(popup) popupDuration = 150;
        
        drawMedal();
    }
    
    /**
     * Check for hovering over achievement to display the details
     */
    public void act() 
    {
        mouse = Greenfoot.getMouseInfo();
        
        if(popup){
            if(popupDuration == 0) getWorld().removeObject(this);
            else popupDuration--;
        }
        else{
            if(Greenfoot.mouseMoved(this)) drawDescription();
            if(Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) drawMedal();
        }
    }
    
    /**
     * Draw each achievement medal
     */
    private void drawMedal(){
        image.clear();
        
        if(achieved){
            if(type.equals("bronze")) image.drawImage(bronzeMedal, MEDAL_WIDTH / 2 - 34, 0);
            else if(type.equals("silver")) image.drawImage(silverMedal, MEDAL_WIDTH / 2 - 34, 0);
            else if(type.equals("gold")) image.drawImage(goldMedal, MEDAL_WIDTH / 2 - 37, 0);
            else if(type.equals("diamond")) image.drawImage(diamondMedal, MEDAL_WIDTH / 2 - 34, 0);
        }
        else{ //set medal to black
            if(type.equals("bronze")) image.drawImage(noBronzeMedal, MEDAL_WIDTH / 2 - 34, 0);
            else if(type.equals("silver")) image.drawImage(noSilverMedal, MEDAL_WIDTH / 2 - 34, 0);
            else if(type.equals("gold")) image.drawImage(noGoldMedal, MEDAL_WIDTH / 2 - 37, 0);
            else if(type.equals("diamond")) image.drawImage(noDiamondMedal, MEDAL_WIDTH / 2 - 34, 0);
        }
        
        image.setColor(NAME_COLOR);
        image.setFont(NAME_FONT);
        image.drawString(name, (image.getWidth() - (int)(name.length() * NAME_FONT.getSize() * 0.6)) / 2, (image.getHeight() * 9 / 10));
        setImage(image);
    }
    
    /**
     * Draw each achievement description
     */
    private void drawDescription(){
        image.clear();
        
        String[] words = desc.split(" ");
        int lineCounter = 1;
        int length = 0;
        int index = 0;
        String line1 = "";
        String line2 = "";
        String line3 = "";
        String line4 = "";
        String line5 = "";
        
        image.setColor(DESCRIPTION_BG_COLOR);
        image.fill();
        image.setColor(DESCRIPTION_COLOR);
        
        while(index != words.length){
            if((length += words[index].length() + 1) * DESCRIPTION_FONT.getSize() * 0.6 < image.getWidth()){
                length += words[index].length() + 1;
                if(lineCounter == 1) line1 += words[index] + " ";
                else if(lineCounter == 2) line2 += words[index] + " ";
                else if(lineCounter == 3) line3 += words[index] + " ";
                else if(lineCounter == 4) line4 += words[index] + " ";
                else if(lineCounter == 5) line5 += words[index] + " ";
                index++;
            }
            else{
                lineCounter++;
                length = 0;
            }
        }
        
        if(lineCounter == 1) image.drawString(line1, (image.getWidth() - (int)(line1.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() / 2) + DESCRIPTION_FONT.getSize() / 4);
        else if(lineCounter == 2){
            image.drawString(line1, (image.getWidth() - (int)(line1.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() * 2 / 5) + DESCRIPTION_FONT.getSize() / 4);
            image.drawString(line2, (image.getWidth() - (int)(line2.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() * 3 / 5) + DESCRIPTION_FONT.getSize() / 4);
        }
        else if(lineCounter == 3){
            image.drawString(line1, (image.getWidth() - (int)(line1.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() * 3 / 10) + DESCRIPTION_FONT.getSize() / 4);
            image.drawString(line2, (image.getWidth() - (int)(line2.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() / 2) + DESCRIPTION_FONT.getSize() / 4);
            image.drawString(line3, (image.getWidth() - (int)(line3.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() * 7 / 10) + DESCRIPTION_FONT.getSize() / 4);
        }
        else if(lineCounter == 4){
            image.drawString(line1, (image.getWidth() - (int)(line1.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() / 5) + DESCRIPTION_FONT.getSize() / 4);
            image.drawString(line2, (image.getWidth() - (int)(line2.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() * 2 / 5) + DESCRIPTION_FONT.getSize() / 4);
            image.drawString(line3, (image.getWidth() - (int)(line3.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() * 3 / 5) + DESCRIPTION_FONT.getSize() / 4);
            image.drawString(line4, (image.getWidth() - (int)(line4.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() * 4 / 5) + DESCRIPTION_FONT.getSize() / 4);
        }
        else if(lineCounter == 5){
            image.drawString(line1, (image.getWidth() - (int)(line1.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() / 10) + DESCRIPTION_FONT.getSize() / 4);
            image.drawString(line2, (image.getWidth() - (int)(line2.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() * 3 / 10) + DESCRIPTION_FONT.getSize() / 4);
            image.drawString(line3, (image.getWidth() - (int)(line3.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() / 2) + DESCRIPTION_FONT.getSize() / 4);
            image.drawString(line4, (image.getWidth() - (int)(line4.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() * 7 / 10) + DESCRIPTION_FONT.getSize() / 4);
            image.drawString(line5, (image.getWidth() - (int)(line5.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() * 9 / 10) + DESCRIPTION_FONT.getSize() / 4);
        }
        setImage(image);
    }
}

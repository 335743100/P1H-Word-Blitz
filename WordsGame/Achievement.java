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
    public static final int MEDAL_HEIGHT = 540 / 6;
    private Color nameColor = new Color(255, 255, 0);
    private Font nameFont = new Font("Courier New", true, true, MEDAL_HEIGHT / 6);
    private String name;
    private Color textColor = new Color(255, 255, 0);
    private Font textFont = new Font("Courier New", true, true, MEDAL_HEIGHT / 6);
    private String text;
    
    private String type;
    private boolean achieved, popup;
    private int time;
    
    private GreenfootImage bronzeMedal = new GreenfootImage("BronzeMedal.png");
    private GreenfootImage noBronzeMedal = new GreenfootImage("NoBronzeMedal.png");
    private GreenfootImage silverMedal = new GreenfootImage("SilverMedal.png");
    private GreenfootImage noSilverMedal = new GreenfootImage("NoSilverMedal.png");
    private GreenfootImage goldMedal = new GreenfootImage("GoldMedal.png");
    private GreenfootImage noGoldMedal = new GreenfootImage("NoGoldMedal.png");
    private GreenfootImage diamondMedal = new GreenfootImage("DiamondMedal.png");
    private GreenfootImage noDiamondMedal = new GreenfootImage("NoDiamondMedal.png");
    
    private MouseInfo mouse;
    
    public Achievement(String name, String text, String type, boolean achieved, boolean popup){
        image = new GreenfootImage(MEDAL_WIDTH + 1, MEDAL_HEIGHT + 1);
        
        this.name = name;
        this.text = text;
        this.type = type;
        this.achieved = achieved;
        this.popup = popup;
        if(popup) time = 180;
        
        drawMedal();
    }
    
    /**
     * Act - do whatever the Achievement wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        mouse = Greenfoot.getMouseInfo();
        
        if(popup){
            if(time == 0) getWorld().removeObject(this);
            else time--;
        }
        else{
            if(Greenfoot.mouseMoved(this)){
               drawDescription();
            }
            if(Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)){
                drawMedal();
            }
        }
    }
    
    private void drawMedal(){
        image.clear();
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
        image.setColor(nameColor);
        image.setFont(nameFont);
        image.drawString(name, (image.getWidth() - (int)(name.length() * nameFont.getSize() * 0.6)) / 2, (image.getHeight() * 9 / 10));
        setImage(image);
    }
    
    private void drawDescription(){
        image.clear();
        Color black = new Color(255, 0, 255, 100);
        image.setColor(black);
        image.fill();
        image.setColor(Color.YELLOW);
        String line1 = "";
        String line2 = "";
        String line3 = "";
        String line4 = "";
        String line5 = "";
        int lineCounter = 1;
        int length = 0;
        String[] words = text.split(" ");
        int index = 0;
        while(index != words.length){
            if((length += words[index].length() + 1) * textFont.getSize() * 0.6 < image.getWidth()){
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
        if(lineCounter == 1) image.drawString(line1, (image.getWidth() - (int)(line1.length() * textFont.getSize() * 0.6)) / 2, (image.getHeight() / 2));
        else if(lineCounter == 2){
            image.drawString(line1, (image.getWidth() - (int)(line1.length() * textFont.getSize() * 0.6)) / 2, (image.getHeight() * 2 / 5));
            image.drawString(line2, (image.getWidth() - (int)(line2.length() * textFont.getSize() * 0.6)) / 2, (image.getHeight() * 3 / 5));
        }
        else if(lineCounter == 3){
            image.drawString(line1, (image.getWidth() - (int)(line1.length() * textFont.getSize() * 0.6)) / 2, (image.getHeight() * 3 / 10));
            image.drawString(line2, (image.getWidth() - (int)(line2.length() * textFont.getSize() * 0.6)) / 2, (image.getHeight() / 2));
            image.drawString(line3, (image.getWidth() - (int)(line3.length() * textFont.getSize() * 0.6)) / 2, (image.getHeight() * 7 / 10));
        }
        else if(lineCounter == 4){
            image.drawString(line1, (image.getWidth() - (int)(line1.length() * textFont.getSize() * 0.6)) / 2, (image.getHeight() / 5));
            image.drawString(line2, (image.getWidth() - (int)(line2.length() * textFont.getSize() * 0.6)) / 2, (image.getHeight() * 2 / 5));
            image.drawString(line3, (image.getWidth() - (int)(line3.length() * textFont.getSize() * 0.6)) / 2, (image.getHeight() * 3 / 5));
            image.drawString(line4, (image.getWidth() - (int)(line4.length() * textFont.getSize() * 0.6)) / 2, (image.getHeight() * 4 / 5));
        }
        else if(lineCounter == 5){
            image.drawString(line1, (image.getWidth() - (int)(line1.length() * textFont.getSize() * 0.6)) / 2, (image.getHeight() / 10));
            image.drawString(line2, (image.getWidth() - (int)(line2.length() * textFont.getSize() * 0.6)) / 2, (image.getHeight() * 3 / 10));
            image.drawString(line3, (image.getWidth() - (int)(line3.length() * textFont.getSize() * 0.6)) / 2, (image.getHeight() / 2));
            image.drawString(line4, (image.getWidth() - (int)(line4.length() * textFont.getSize() * 0.6)) / 2, (image.getHeight() * 7 / 10));
            image.drawString(line5, (image.getWidth() - (int)(line5.length() * textFont.getSize() * 0.6)) / 2, (image.getHeight() * 9 / 10));
        }
        setImage(image);
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) Format by Mr. Cohen
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    //declaring variables for the button's image
    private GreenfootImage image;
    public static final int BUTTON_WIDTH = 960 / 6;
    public static final int BUTTON_HEIGHT = 540 / 11;
    private Color bgColor;
    private Color labelColor;
    private Color hoverColor;
    private Color flashColor;
    private String label;
    public static Font labelFont = new Font("Courier New", true, false, BUTTON_HEIGHT / 2);
    
    //declaring instance variables
    //sound
    private GreenfootSound moveSound = new GreenfootSound("Menu Move.wav");
    private boolean soundPlayed = false;
    //mouse tracking
    private MouseInfo mouse;
    private boolean hovering = false;
    private boolean flashing = false;
    private int duration;
    
    public Button(String str, Color bgColor, Color labelColor, Color hoverColor, Color flashColor){
        image = new GreenfootImage(BUTTON_WIDTH + 1, BUTTON_HEIGHT + 1); //creating the blank GreenfootImages used for the buttons
        label = str;
        //setting the image for the button
        this.bgColor = bgColor;
        this.labelColor = labelColor;
        this.hoverColor = hoverColor;
        this.flashColor = flashColor;
        drawButton(flashing);
        setImage(image);
    }
    
    public void act() 
    {
        mouse = Greenfoot.getMouseInfo();
        //changes button label's color when the mouse is hovering over it (from Mr. Cohen)
        if(Greenfoot.mouseMoved(this)){
            hovering = true;
            if(!soundPlayed){ //adds sound
                moveSound.play();
                soundPlayed = true;
            }
            drawButton(flashing);
            setImage(image);
        }
        if(Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)){
            hovering = false;
            soundPlayed = false;
            drawButton(flashing);
            setImage(image);
        }
        if(flashing){
            if(duration == 0){
                flashing = false;
                drawButton(flashing);
            }
            else duration--;
        }
    }
    
    public void update(String label){
        this.label = label;
        drawButton(flashing);
        this.setImage(image);
    }
    
    public void changeColor(Color labelColor, Color bgColor)
    {
        this.labelColor = labelColor;
        this.bgColor = bgColor;
        update(this.label);
    }
    
    public void flash(int duration){
        this.duration = duration;
        flashing = true;
        drawButton(flashing);
    }
    
    //method to draw the button with the given parameters
    private void drawButton(boolean flashing){
        image.clear();
        //drawing button
        image.setColor(bgColor);
        image.fillOval(0, 0, (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.fillOval(0, image.getHeight() - 1 - ((image.getWidth() - 1) / 10), (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.fillOval(image.getWidth() - 1- ((image.getWidth() - 1) / 10), 0, (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.fillOval(image.getWidth() - 1- ((image.getWidth() - 1) / 10), image.getHeight() - 1 - ((image.getWidth() - 1) / 10), (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        if(flashing) image.setColor(flashColor);
        else image.setColor(labelColor);
        image.drawOval(0, 0, (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.drawOval(0, image.getHeight() - 1 - ((image.getWidth() - 1) / 10), (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.drawOval(image.getWidth() - 1- ((image.getWidth() - 1) / 10), 0, (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.drawOval(image.getWidth() - 1- ((image.getWidth() - 1) / 10), image.getHeight() - 1 - ((image.getWidth() - 1) / 10), (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.setColor(bgColor);
        image.fillRect((image.getWidth() - 1) / 20, 0, image.getWidth() - 1 - (image.getWidth() - 1) / 10, image.getHeight() - 1);
        image.fillRect(0, (image.getWidth() - 1) / 20, image.getWidth() - 1, image.getHeight() - 1 - (image.getWidth() - 1) / 10);
        if(flashing) image.setColor(flashColor);
        else image.setColor(labelColor);
        image.drawLine((image.getWidth() - 1) / 20, 0, image.getWidth() - 1- (image.getWidth() - 1) / 20, 0);
        image.drawLine((image.getWidth() - 1) / 20, image.getHeight() - 1, image.getWidth() - 1- (image.getWidth() - 1) / 20, image.getHeight() - 1);
        image.drawLine(0, (image.getWidth() - 1) / 20, 0, image.getHeight() - 1 - (image.getWidth() - 1) / 20);
        image.drawLine(image.getWidth() - 1, (image.getWidth() - 1) / 20, image.getWidth() - 1, image.getHeight() - 1 - (image.getWidth() - 1) / 20);
        //adding label
        image.setFont(labelFont);
        if(hovering){ //change the label color if the mouse is hovering the button
            image.setColor(hoverColor);
        }
        image.drawString(label, (image.getWidth() - (int)(label.length() * labelFont.getSize() * 0.58)) / 2, (image.getHeight() + labelFont.getSize() / 2) / 2);
    }
}
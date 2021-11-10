import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * General button class
 * 
 * @author (your name) Format by Mr. Cohen
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    public static final int BUTTON_WIDTH = GameWorld.WIDTH * 96 / 425;
    public static final int BUTTON_HEIGHT = GameWorld.HEIGHT * 9 / 88;
    
    //declaring variables for the button's image
    private GreenfootImage image;
    private Color bgColor;
    private Color outlineColor;
    private Color labelColor;
    private Color hoverColor;
    private Color flashColor;
    public static final Font LABEL_FONT = new Font("Courier New", true, false, BUTTON_HEIGHT / 2);
    private String label;
    
    //declaring instance variables
    //sound
    private GreenfootSound moveSound = new GreenfootSound("Menu Move.wav");
    private boolean soundPlayed = false;
    
    //button state tracking
    private boolean hovering = false;
    private boolean flashing = false;
    private int duration;
    
    //mouse tracking
    private MouseInfo mouse;
    
    public Button(String label, Color bgColor, Color outlineColor, Color labelColor, Color hoverColor, Color flashColor){
        image = new GreenfootImage(BUTTON_WIDTH + 1, BUTTON_HEIGHT + 1); //creating the blank GreenfootImages used for the buttons
        this.label = label;
        //setting the image for the button
        this.bgColor = bgColor;
        this.outlineColor = outlineColor;
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
    
    public boolean isHovering(){
        return hovering;
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
        else image.setColor(outlineColor);
        image.drawOval(0, 0, (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.drawOval(0, image.getHeight() - 1 - ((image.getWidth() - 1) / 10), (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.drawOval(image.getWidth() - 1- ((image.getWidth() - 1) / 10), 0, (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.drawOval(image.getWidth() - 1- ((image.getWidth() - 1) / 10), image.getHeight() - 1 - ((image.getWidth() - 1) / 10), (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.setColor(bgColor);
        image.fillRect((image.getWidth() - 1) / 20, 0, image.getWidth() - 1 - (image.getWidth() - 1) / 10, image.getHeight() - 1);
        image.fillRect(0, (image.getWidth() - 1) / 20, image.getWidth() - 1, image.getHeight() - 1 - (image.getWidth() - 1) / 10);
        if(flashing) image.setColor(flashColor);
        else image.setColor(outlineColor);
        image.drawLine((image.getWidth() - 1) / 20, 0, image.getWidth() - 1- (image.getWidth() - 1) / 20, 0);
        image.drawLine((image.getWidth() - 1) / 20, image.getHeight() - 1, image.getWidth() - 1- (image.getWidth() - 1) / 20, image.getHeight() - 1);
        image.drawLine(0, (image.getWidth() - 1) / 20, 0, image.getHeight() - 1 - (image.getWidth() - 1) / 20);
        image.drawLine(image.getWidth() - 1, (image.getWidth() - 1) / 20, image.getWidth() - 1, image.getHeight() - 1 - (image.getWidth() - 1) / 20);
        //adding label
        if(flashing) image.setColor(flashColor);
        else image.setColor(labelColor);
        image.setFont(LABEL_FONT);
        if(hovering){ //change the label color if the mouse is hovering the button
            image.setColor(hoverColor);
        }
        image.drawString(label, (image.getWidth() - (int)(label.length() * LABEL_FONT.getSize() * 0.6)) / 2, (image.getHeight() + LABEL_FONT.getSize() / 2) / 2);
    }
}
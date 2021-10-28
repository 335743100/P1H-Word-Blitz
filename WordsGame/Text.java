import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Text here.
 * 
 * @author Vaughn
 * @version 0.0.1
 */
public class Text extends Actor
{   
    // X position and Y Position respectively
    private int xPos;
    private int yPos;
    
    private String text;
    private int fontSize = 48;
    private Color textColor = new Color(0, 0, 0);
    private Color textBackground = new Color(255, 255, 255, 255);   
    private Font font = new Font(false, false, fontSize);
    private GreenfootImage textImage;
    private World currentWorld;
    
    public Text(World cW, int xPos, int yPos)
    {
        // Get reference to world object and inject itself to world
        currentWorld = cW;
        currentWorld.addObject(this, xPos, yPos);
        
        // Initialize text
        text = "Text";
        textImage = new GreenfootImage(text, fontSize, 
                                    textColor, textBackground);
        setImage(textImage);
        
        // Initialize text position
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    public Text(World cW, int xPos, int yPos, String text)
    {
        // Get reference to world object and inject itself to world
        currentWorld = cW;
        currentWorld.addObject(this, xPos, yPos);
        
        // Initialize text
        this.text = text;
        textImage = new GreenfootImage(text, fontSize, 
                                    textColor, textBackground);
        setImage(textImage);
        
        // Initialize text position
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    public void setText(String text)
    {
        this.text = text;
        
        updateText();
        setImage(textImage);
    }
    
    public void setPosition(int xPos, int yPos)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        
        // The positiion of object was defined on instantiation. Therefore
        // the object needs to be removed and then added back with the
        // correct position.
        currentWorld.removeObject(this);
        currentWorld.addObject(this, xPos, yPos);
        updateText();
    }
    
    public void setColor(Color color)
    {
        this.textColor = color;
        updateText();
    }
    
    public void setFontSize(int fontSize)
    {
        this.fontSize = fontSize;
        updateText();
    }
    
    public int getX()
    {
        return xPos;
    }
    
    public int getY()
    {
        return yPos;
    }
    
    private void updateText()
    {
        textImage.clear();
        textImage = new GreenfootImage(text, fontSize, textColor, textBackground);
        setImage(textImage);
    }
}

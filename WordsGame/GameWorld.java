import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.Math;
import java.util.Arrays;

/**
 * Template for the levels to follow.
 *  
 * @author Jaylen Cheung, Vaughn Chan
 * @version 0.0.2
 */
public class GameWorld extends World {
    
    public static int WORLD_HEIGHT = 800;
    public static int WORLD_WIDTH = 600;
    
    // How fast the words drop
    private int speed;
    
    private ArrayList<String> nouns;
    private ArrayList<String> verbs;
    private ArrayList<String> adjectives;
    private ArrayList<ArrayList<String>> listOfWordTypes;
    
    // The current word and character
    private String currentWord;
    private String currentChar;
    
    // The queue of words to display
    private Queue<String> playerWordQueue;
    // The letters that the player has typed. Resets on new word
    private String playerInput;
    // Number of letters the player has typed. Resets on new word
    private int letterCount;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public GameWorld(int speed) {    
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        listOfWordTypes = ReadWordFiles.readWordFiles();
        nouns = listOfWordTypes.get(0);
        verbs = listOfWordTypes.get(1);
        adjectives = listOfWordTypes.get(2);
        
        playerWordQueue = new LinkedList<String>();
        for (String i : generateWords(10)) {
            playerWordQueue.add(i);
        }
        currentWord = playerWordQueue.remove();
        currentChar = Character.toString(currentWord.charAt(0));
        playerInput = "";
        letterCount = 1;
        System.out.println(currentWord);
        
        // Create score text
        Text scoreText = new Text(this, 110, 50, "Score:");
        
        // TODO: Implement the words dropping and use this as a speed multiplier
        this.speed = speed;
    }
    
    public void act() {
        if (Greenfoot.isKeyDown(currentChar)) {
            playerInput += currentChar;
            if (playerInput.equals(currentWord)) {
                playerWordQueue.add(generateWords(1).get(0));
                currentWord = playerWordQueue.remove();
                playerInput = "";
                letterCount = 0;
                System.out.println(Arrays.toString(playerWordQueue.toArray()));
                System.out.println(currentWord);
            }
            currentChar = Character.toString(currentWord.charAt(letterCount));
            letterCount++;
            System.out.println(playerInput);
        }
    }
    
    public ArrayList<String> generateWords(int amount) {
        ArrayList<String> list = new ArrayList<String>();
        // Choose which list randomly (nouns, verbs, adjectives)
        int random1 = (int) Math.floor(Math.random()*(2-0+1)+0);
        int length = listOfWordTypes.get(random1).size();
        for (int i = 0; i < amount; i++) {
            // Choose a random word from that list
            int random2 = (int) Math.floor(Math.random()*(length-1-0+1)+0);
            list.add(listOfWordTypes.get(random1).get(random2));
        }
        return list;
    }
    
}

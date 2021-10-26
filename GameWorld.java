import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.FileNotFoundException;
import java.util.Queue;
import java.lang.Math;

/**
 * Template for the levels to follow.
 * 
 * @author Jaylen Cheung
 * @version 0.0.1
 */
public abstract class GameWorld extends World {
    private ArrayList<String> nouns;
    private ArrayList<String> verbs;
    private ArrayList<String> adjectives;
    private ArrayList<ArrayList<String>> listOfWordTypes;
    
    private String currentWord;
    private String currentChar;
    
    private Queue<String> playerWordQueue;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public GameWorld(int speed) {    
        super(800, 500, 1);
        listOfWordTypes = ReadWordFiles.readWordFiles();
        nouns = listOfWordTypes.get(0);
        verbs = listOfWordTypes.get(1);
        adjectives = listOfWordTypes.get(2);
        
        playerWordQueue = new LinkedList<String>();
        for (String i : generateWords(10)) {
            playerWordQueue.add(i);
        }
        currentWord = playerWordQueue.peek();
        currentChar = Character.toString(currentWord.charAt(0));
    }
    
    public void act() {
        if (Greenfoot.isKeyDown(currentChar)) {
            
        }
    }
    
    public ArrayList<String> generateWords(int amount) {
        ArrayList<String> list = new ArrayList<String>();
        // Choose which list randomly (nouns, verbs, adjectives)
        int random1 = (int) Math.floor(Math.random()*(2-0+1)+0);
        int length = listOfWordTypes.get(random1).size();
        for (int i = 0; i < amount; i++) {
            int random2 = (int) Math.floor(Math.random()*(length-1-0+1)+0);
            list.add(listOfWordTypes.get(random1).get(random2));
        }
        return list;
    }
    
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.Math;

/**
 * Template for the levels to follow.
 *  
 * @author Jaylen Cheung
 * @version 0.0.3
 */
public class GameWorld extends World {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 500;
    private static Color BACKGROUND_COLOR = new Color(52, 232, 235);
    
    // Word bank
    private ArrayList<String> nouns;
    private ArrayList<String> verbs;
    private ArrayList<String> adjectives;
    private ArrayList<ArrayList<String>> listOfWordTypes;
    
    // Box of words to display
    private WordBox wordBox;
    private WordOverlay wordOverlay = new WordOverlay();
    
    //timebar and how much time variables (360 = 1 second)
    private StatBar timeBar;
    private int time = 300, maxTime = 300, timeBonus = 120, timePenalty = 30;
    
    //score variables
    public static int score = 0;
    public static final int POINTS = 10;
    private ScoreDisplay scoreDisplay;
    
    // The current word and character
    private String currentWord;
    private String currentChar;
    
    //sound effects
    private GreenfootSound wrongSound = new GreenfootSound("Wrong.wav");
    private GreenfootSound correctSound = new GreenfootSound("Correct.wav");
    
    //boolean to keep track of key release
    private boolean keyDown = false;
    
    // The queue of words to display
    private Queue<String> playerWordQueue;
    // The letters that the player has typed. Resets on new word
    private String playerInput;
    // Number of letters the player has typed. Resets on new word
    private int letterCount;
    /**
     * Constructor for objects of class MyWorld.
     */
    public GameWorld(int speed) {
        super(WIDTH, HEIGHT, 1);
        GreenfootImage background = new GreenfootImage(WIDTH, HEIGHT);
        background.setColor(BACKGROUND_COLOR);
        background.fill();
        setBackground(background);
        
        listOfWordTypes = ReadWordFiles.readWordFiles();
        nouns = listOfWordTypes.get(0);
        verbs = listOfWordTypes.get(1);
        adjectives = listOfWordTypes.get(2);
        
        playerWordQueue = new LinkedList<String>();
        for (String i : generateWords(20)) {
            playerWordQueue.add(i + " ");
        }
        // White box to display the words
        wordBox = new WordBox(new LinkedList<String>(playerWordQueue));
        addObject(wordBox, 400, 250);
        
        addObject(wordOverlay, 400, 250);
        
        score = 0;
        
        timeBar = new StatBar(time, time, WIDTH, HEIGHT / 20, 0, Color.GREEN, Color.WHITE, false, Color.BLACK, HEIGHT / 100);
        addObject(timeBar, WIDTH / 2, HEIGHT - HEIGHT / 40);
        
        scoreDisplay = new ScoreDisplay(score);
        addObject(scoreDisplay, scoreDisplay.SCORE_DISPLAY_WIDTH / 2, scoreDisplay.SCORE_DISPLAY_HEIGHT / 2);
        
        currentWord = playerWordQueue.remove();
        currentChar = Character.toString(currentWord.charAt(0));
        playerInput = "";
        letterCount = 1;
    }
    
    public void act() {
        if (keyDown != Greenfoot.isKeyDown("space")) {
            keyDown = !keyDown;
            if(!keyDown){
                if (currentChar.equals(" ")) {
                    checkWords();
                    correctSound.play();
                }
                else{
                    wrongSound.play();
                }
            }
        } else if (Greenfoot.isKeyDown(currentChar)) {
            checkWords();
        }
        time--;
        if(time == 0) Greenfoot.setWorld(new EndScreen());
        timeBar.update(time);
    }
    
    /*
     * Handle the word box
     */
    public void checkWords() {
        playerInput += currentChar;
        if (playerInput.equals(currentWord)) {
            playerWordQueue.add(generateWords(1).get(0) + " ");
            wordBox.setWordBox(new LinkedList<String>(playerWordQueue));
            currentWord = playerWordQueue.remove();
            playerInput = "";
            letterCount = 0;
            time += timeBonus;
            if(time > maxTime) time = maxTime;
            score += POINTS;
            scoreDisplay.update(score);
        }
        currentChar = Character.toString(currentWord.charAt(letterCount));
        wordOverlay.setWordBox(currentWord, letterCount);
        letterCount++;
    }
    
    /*
     * Generate a random ArrayList of words
     * @param amount Amount of words to generate
     */
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

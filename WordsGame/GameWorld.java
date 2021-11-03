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
    private CorrectWordOverlay correctWordOverlay = new CorrectWordOverlay();
    private WrongWordOverlay wrongWordOverlay = new WrongWordOverlay();
    private CheckInput checkInput = new CheckInput();
    
    //timebar and how much time variables (360 = 1 second)
    private StatBar timeBar;
    private int time, MAX_TIME = 600, TIME_BONUS = 120, TIME_PENALTY = 30;
    
    //score variables
    public static int score;
    public static final int POINTS = 10;
    private ScoreDisplay scoreDisplay;
    
    // The current word and character, and the repeated character
    private String currentWord;
    private String currentChar;
    private String repeatedChar;
    
    //String to keep track of what key the user typed
    private String key;
    private String punc = "`~!@#$%^&*()-_=+[{]}|\\;:'\",<.>/?";
    
    //sound effects
    private GreenfootSound wrongSound = new GreenfootSound("Wrong.wav");
    private GreenfootSound correctSound = new GreenfootSound("Correct.wav");
    
    //boolean to keep track of key release
    private boolean keyDown, spaceDown;
    
    // The queue of words to display
    private Queue<String> playerWordQueue;
    // The letters that the player has typed. Resets on new word
    private String playerInput;
    // Number of letters the player has typed. Resets on new word
    private int letterCount;
    private int numWrong;
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
        
        addObject(correctWordOverlay, 400, 250);
        addObject(wrongWordOverlay, 400, 250);
        addObject(checkInput, 500, 300);
        
        key = null;
        keyDown = false;
        spaceDown = false;
        
        time = MAX_TIME;
        
        score = 0;
        
        numWrong = 0;
        
        timeBar = new StatBar(time, time, WIDTH, HEIGHT / 20, 0, Color.GREEN, Color.WHITE, false, Color.BLACK, HEIGHT / 100);
        addObject(timeBar, WIDTH / 2, HEIGHT - HEIGHT / 40);
        
        scoreDisplay = new ScoreDisplay(score);
        addObject(scoreDisplay, scoreDisplay.SCORE_DISPLAY_WIDTH / 2, scoreDisplay.SCORE_DISPLAY_HEIGHT / 2);
        
        currentWord = playerWordQueue.remove();
        playerInput = "";
    }
    
    public void act() {
        if (spaceDown != Greenfoot.isKeyDown("space")) {
            spaceDown = !spaceDown;
            if(!spaceDown){
                checkWords(true, " ");
            }
        }
        else{
            if(key == null || key.equals("space")) key = Greenfoot.getKey();
            if(key != null && !key.equals("space")){
                if((key.length() == 1 && Character.isLetter(key.charAt(0))) || key.equals("backspace")){ //check other typeable characters
                    checkWords(false, key);
                }
                key = null;
            }
        }
        time--;
        if(time <= 0) Greenfoot.setWorld(new EndScreen());
        timeBar.update(time);
    }
    
    /*
     * Handle the word box
     */
    public void checkWords(boolean enter, String key) {
        numWrong = 0;
        if(playerInput.length() < currentWord.length()){
            if(key.equals("backspace") && playerInput.length() > 0) playerInput = playerInput.substring(0, playerInput.length() - 1);
            else if(playerInput.length() < currentWord.length() - 1 && !key.equals("backspace"))playerInput += key;
            else if(key.equals(" ")) playerInput += key;
        }
        for(int i = 0; i < playerInput.length(); i++){
            if(!Character.toString(playerInput.charAt(i)).equalsIgnoreCase(Character.toString(currentWord.charAt(i)))){
                numWrong = playerInput.length() - i;
                break;
            }
        }
        if(enter){
            if (playerInput.equals(currentWord)) {
                time += TIME_BONUS;
                if(time > MAX_TIME) time = MAX_TIME;
                score += POINTS;
                scoreDisplay.update(score);
                correctSound.play(); //make multiple play at same time
            }
            else{
                time -= TIME_PENALTY;
                wrongSound.play();
            }
            playerWordQueue.add(generateWords(1).get(0) + " ");
            wordBox.setWordBox(new LinkedList<String>(playerWordQueue));
            currentWord = playerWordQueue.remove();
            playerInput = "";
            correctWordOverlay.setCorrectOverlay("", 0, true);
            wrongWordOverlay.setWrongOverlay("", 0, 0, true);
        }
        else if(numWrong == 0){
            correctWordOverlay.setCorrectOverlay(currentWord, playerInput.length(), false);
            wrongWordOverlay.setWrongOverlay("", playerInput.length() - numWrong, numWrong, false);
        }
        else if(numWrong > 0){
            wrongWordOverlay.setWrongOverlay(currentWord, playerInput.length() - numWrong, numWrong, false);
        }
        //checkInput.drawInput(playerInput);
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
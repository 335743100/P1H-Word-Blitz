import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Collections;
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
    public static final int WIDTH = 850;
    public static final int HEIGHT = 480;
    private GreenfootImage bgImage = new GreenfootImage("MenuBackground.jpg");
    
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
    private float time = 300, maxTime = 300, timeBonus = 120, timePenalty = 30;
    
    //score variables
    public static int score = 0;
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
    private GreenfootSound[] correctSounds, wrongSounds, achievementSounds;
    private int correctSoundIndex, wrongSoundIndex, achievementSoundIndex;
    
    //boolean to keep track of key release
    private boolean keyDown, spaceDown;
    
    // The queue of words to display
    private Queue<String> playerWordQueue;
    // The letters that the player has typed. Resets on new word
    private String playerInput;
    // Number of letters the player has typed. Resets on new word
    private int letterCount;
    private int numWrong;
    
    // Keep track of how long the game has been running
    private int gameTime;
    
    // Keep track of the number of right and wrong characters to calculate accuracy
    private int totalWrongChars;
    private int totalRightChars;

    // How fast the timer should go down
    private float speed;
    
    int numMedals;

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public GameWorld(float speed) {
        super(WIDTH, HEIGHT, 1);
        GreenfootImage background = new GreenfootImage(WIDTH, HEIGHT);
        background.drawImage(bgImage, 0, 0);
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
        
        correctSoundIndex = 0;
        correctSounds = new GreenfootSound[5];
        for(int i = 0; i < correctSounds.length; i++) correctSounds[i] = new GreenfootSound("Correct.wav");
        wrongSoundIndex = 0;
        wrongSounds = new GreenfootSound[15];
        for(int i = 0; i < wrongSounds.length; i++) wrongSounds[i] = new GreenfootSound("Wrong.wav");
        achievementSoundIndex = 0;
        achievementSounds = new GreenfootSound[10];
        for(int i = 0; i < achievementSounds.length; i++){
            achievementSounds[i] = new GreenfootSound("Achievement.wav");
            achievementSounds[i].setVolume(70); 
        }
        
        key = null;
        keyDown = false;
        spaceDown = false;
        
        time = maxTime;
        score = 0;
        numWrong = 0;
        
        timeBar = new StatBar((int)time, (int)time, WIDTH, HEIGHT / 20, 0, Color.GREEN, Color.WHITE, false, Color.BLACK, HEIGHT / 100);

        addObject(timeBar, WIDTH / 2, HEIGHT - HEIGHT / 40);
        
        scoreDisplay = new ScoreDisplay(score);
        addObject(scoreDisplay, scoreDisplay.SCORE_DISPLAY_WIDTH / 2, scoreDisplay.SCORE_DISPLAY_HEIGHT / 2);
        
        numMedals = 0;
        
        currentWord = playerWordQueue.remove();
        playerInput = "";
        gameTime = 0;
        
        totalRightChars = 0;
        totalWrongChars = 0;
        letterCount = 1;
        
        this.speed = speed;
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
                if((key.length() == 1 && Character.isLetter(key.charAt(0))) || key.equals("backspace") || key.equals("-")){ //check other typeable characters
                    checkWords(false, key);
                }
                key = null;
            }
        }
        
        if(numMedals != ScoreDisplay.medalsUnlocked.size()){
            for(int i = numMedals; i < ScoreDisplay.medalsUnlocked.size(); i++){
                if(ScoreDisplay.medalsUnlocked.get(i) == 0) addObject(new Achievement("name", "bronze", true, true), WIDTH * 70 / 85, HEIGHT / 5);
                else if(ScoreDisplay.medalsUnlocked.get(i) == 3) addObject(new Achievement("name", "silver", true, true), WIDTH * 70 / 85, HEIGHT * 2 / 5);
                else if(ScoreDisplay.medalsUnlocked.get(i) == 6) addObject(new Achievement("name", "gold", true, true), WIDTH * 70 / 85, HEIGHT * 3 / 5);
                else if(ScoreDisplay.medalsUnlocked.get(i) == 1) addObject(new Achievement("name", "bronze", true, true), WIDTH * 79 / 85, HEIGHT / 5);
                else if(ScoreDisplay.medalsUnlocked.get(i) == 4) addObject(new Achievement("name", "silver", true, true), WIDTH * 79 / 85, HEIGHT * 2 / 5);
                else if(ScoreDisplay.medalsUnlocked.get(i) == 7) addObject(new Achievement("name", "gold", true, true), WIDTH * 79 / 85, HEIGHT * 3 / 5);
                else addObject(EndScreen.determineMedal(ScoreDisplay.medalsUnlocked.get(i), true), WIDTH * 149 / 170, HEIGHT * 4 / 5);
                
                achievementSounds[achievementSoundIndex].play();
                achievementSoundIndex++;
                if(achievementSoundIndex >= achievementSounds.length) achievementSoundIndex = 0;
            }
            numMedals = ScoreDisplay.medalsUnlocked.size();
        }
        
        time -= this.speed;
        if(time <= 0){
            Collections.sort(ScoreDisplay.medalsUnlocked);
            Greenfoot.setWorld(new EndScreen());
        }
        timeBar.update((int)time);
        
        gameTime++;
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
                time += timeBonus;
                if(time > maxTime) time = maxTime;
                score += POINTS;
                totalRightChars++;
                correctSounds[correctSoundIndex].play();
                correctSoundIndex++;
                if(correctSoundIndex >= correctSounds.length) correctSoundIndex = 0;
            }
            else{
                time -= timePenalty;
                wrongSounds[wrongSoundIndex].play();
                wrongSoundIndex++;
                if(wrongSoundIndex >= wrongSounds.length) wrongSoundIndex = 0;
                totalWrongChars++;
            }
            scoreDisplay.update(score, gameTime, totalRightChars, totalWrongChars);
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
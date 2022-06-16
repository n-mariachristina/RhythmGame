import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Write a description of class Levels here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Levels extends World
{

    private int level;    
    private String timeSignature;
    int bpm;
    
    // text file that is read in, has notes and rests
    ArrayList<String> notes = new ArrayList<String>();
    
    // each notes object goes in the array so it can change colors later
    ArrayList<sheet_music> noteObjects = new ArrayList<sheet_music>();
    
    // start time is updated later too (after the countdown), when the game actually starts
    long startTime = System.currentTimeMillis();
    
    // number of milliseconds... per beat!
    private double millisPerBeat;
    
    // which note is currently being checked
    int currNote = 0;
    
    // this is to play 4 beats first (feels terribly inefficient but...)
    boolean firstBeat = true;
    boolean secondBeat, thirdBeat, fourthBeat, start = false;
    
    // after first 4 beats are played, notes from input are checked as long as doNoteCheck is true
    boolean doNoteCheck = false;
    
    // number that displays on screen
    int beat = 1;
    
    // used for metronome timing
    long timeOfLastBeep = System.currentTimeMillis();
    
    // buffer value in milliseconds for hitting the note
    int buffer = 300;
    
    // used to store key down/up times in milliseconds
    Times t;
    ArrayList<double[]> correctTimes;     
    
    // used in scoring to check key press and key lift times
    // represents the status of the space bar (down or up) the LAST time a method checked
    boolean bDown;
    
    // user times for key press and key lift
    long timeAtPress;
    long timeAtLift;
        
    // indicates whether user has successfully passed a note/rest or not
    boolean notePassed;
    
    // whether user gets each note correct or not
    ArrayList<Boolean> scores = new ArrayList<Boolean>();
    
    // turns true in act after scores are printed once, so they don't print again
    // boolean scoresPrinted = false;
        
    // whether user has put inputs for all the notes yet
    boolean gameDone = false;
       
    double correctPercentage; 
    //to calculate the correct percentage (how many notes were correct)
    
    // buttons to go back or play again
    AnyButton back;
    AnyButton again;
    
    // light that blinks with metronome beeps
    Light light = new Light();
    
    // whether it boings instead of beeps
    boolean boing = Info.boing;
    
    // instantiated in constructor from bpm parameter
    // double[] noteLengths;
        
    public Levels(int levelNum, int beatsPerMinute) throws FileNotFoundException
    {   
        super(1000, 560, 1); 
        
        level = levelNum;
        bpm = beatsPerMinute;
                       
        millisPerBeat = 1000 * 60 / bpm; // milliseconds per beat
        
        // (notelengths managed in Times class)
        // noteLengths = new double[] {0, millisPerBeat * 4, millisPerBeat * 2, millisPerBeat, millisPerBeat / 2, millisPerBeat / 4};
        // 0 index  irrelevant           whole                half              quarter         eighth        sixteenth
                
        try {
            readFile(); // this method fills the notes array
        }
        catch (FileNotFoundException ex) {
        }
        
        //for (double i : noteLengths) {
        //     System.out.println(i);
        //}
        
        // display
        showStaffLines();
        showTimeSignature();        
        showNotes();
        
        // timing
        populateCorrectTimes();  
        // debug();    
        
        GreenfootImage backdrop = new GreenfootImage("Lev" + level + ".png");
        //img.scale(img.getWidth()/2, img.getHeight()/2);
        setBackground(backdrop);
                
    }

    
    public void act() {
        
        if (!doNoteCheck) { // before game starts
            firstFour(); // plays first 4 beats
        }
        
        if (doNoteCheck && !gameDone) { // game starts
            metronome(); // each beat represents quarter note
            
            // if current note is within size of noteObjects array (not done with level yet)
            if (currNote < noteObjects.size()) {
                
                if (noteObjects.get(currNote).isRest()) { // score rest if note is a rest
                    restCheckAndScore();
                }
                else { // score note if note is a note
                    noteCheck();
                }
            }
            else { // currNote >= array length
                gameDone();
                gameDone = true;
            }
        }
        
        if (gameDone) {
            
            // GreenfootImage backdrop = new GreenfootImage("Lev" + level + ".png");
            // backdrop.scale(backdrop.getWidth()/2, backdrop.getHeight()/2);
            if (Greenfoot.mouseClicked(again)) {
                try {Greenfoot.setWorld(new Levels(level,bpm)); // resets level
                }
                catch (FileNotFoundException ex) {
                }
            }
            
            // more than 80% of notes correct
            if (correctPercentage >= 0.8 && Greenfoot.mouseClicked(back)) {
                if (level == 1) { // level 2 unlocked                  
                    Greenfoot.setWorld(new LevelSelect(true, false));
                }
                else if (level == 2) { // level 3 unlocked                
                    Greenfoot.setWorld(new LevelSelect(true, true));
                }
                else if (level == 3) { // game complete
                    Greenfoot.setWorld(new Done());
                }
        
            }
            
        }
                               
    }  
    
    public void gameDone() {
        
        int numNotesCorrect = 0;
        
        // counts number of correct notes
        for (boolean b : scores) {
            if (b) {
                numNotesCorrect++;
            }
        }
        
        int totalNotes = noteObjects.size();
        correctPercentage = 1.0 * numNotesCorrect / totalNotes; 
        
        // if >= 80%, user can play again or go back to level select
        // if < 80%, user can only play again
        if (correctPercentage >= 0.8) {
            addObject(new Win(), 500, 280);
            back = new AnyButton();
            addObject(back, 650, 420);
            again = new AnyButton();
            addObject(again, 310, 420);
        }
        if (correctPercentage < 0.8) {
            addObject(new Lose(), 500, 280);
            again = new AnyButton();
            addObject(again, 500, 420);
        }  
        
        showText("               ", 500, 200);
        // showText("YAY YOU DID IT!", 500, 350);

        showText("YOUR SCORE IS " + numNotesCorrect + " OUT OF " + totalNotes, 500, 350);
               
        //System.out.print(correctPercentage);
        
    }
    
    public void restCheckAndScore() {
        
        double restStart = correctTimes.get(currNote)[0];
        double restEnd = correctTimes.get(currNote)[1];
        
        // elapsed time in milliseconds since the start of the game
        long elapsedTime = System.currentTimeMillis() - startTime;
        
        // if the rest time has ended, and the rest has not already turned red, then it turns green
        // UNLESS there was a note before the rest that was lifted during the rest's time
        if (elapsedTime > restEnd && !noteObjects.get(currNote).isRed()) {
            
            if (timeAtLift > restStart + buffer) { // note before rest lifted too late
                noteObjects.get(currNote).turnRed();
                scores.add(false);
            }
            else { // otherwise
                noteObjects.get(currNote).turnGreen();
                scores.add(true);
            }            
            currNote++;
        }
        
        // if user presses space, rest is incorrect
        if (!bDown && Greenfoot.isKeyDown("space") && elapsedTime < restEnd) {
            noteObjects.get(currNote).turnRed();
            bDown = true;
            timeAtPress = -1; // this is so the note check method doesn't "think" there was a legitimate press
            scores.add(false);
            currNote++;
        }
        
        // as long as space is not pressed, bDown is false
        if (!Greenfoot.isKeyDown("space")) {
            bDown = false;
        }        
        
    }

    public void noteCheck() {
        
        // bDown represents the status of space in the previous act cycle
        
        // if space wasn't down before, but it is now, key has been pressed
        if (!bDown && Greenfoot.isKeyDown("space")) {
            timeAtPress = System.currentTimeMillis() - startTime;
            bDown = true;
            //System.out.println("down " + timeAtPress); // shows elapsed time
        }
        
        // if space was down before, but isn't down now, key has been lifted
        if (bDown && !Greenfoot.isKeyDown("space")) {
            if (timeAtPress == -1) { // if space was lifted after being falsely pressed during a rest, it doesn't score a note
                bDown = false;
            }
            else { // but if there is a timeAtPress time, it scores the note
            timeAtLift = System.currentTimeMillis()- startTime;
            bDown = false;
            //System.out.println("up " + (timeAtLift));
            
            // since key was lifted, take the last up and down values and find score for note
            scoreNote();
            currNote++;
           }
        }         
        
    }
   
    public void scoreNote() {
                
        // stops when all notes have been scored              
        if (currNote < correctTimes.size()) {
           
           // System.out.println(currNote);
           
           // true if key press / key lift time is within the buffer value of where it should be 
           // (correct times is an array of time pairs with key press and key lift times for each note)
           boolean keyDownScore = Math.abs(timeAtPress - correctTimes.get(currNote)[0]) <= buffer;
           boolean keyUpScore = Math.abs(timeAtLift - correctTimes.get(currNote)[1]) <= buffer;
           
           // if both press and lift correct, note is correct
           boolean score = keyDownScore && keyUpScore;
           scores.add(score);
           
           // notes change color according to score
           if (score) {
               noteObjects.get(currNote).turnGreen();
           }
           else {
               noteObjects.get(currNote).turnRed();
           }
                       
        }
        
        else {
           // printScores();
        }
                      
        /*
        System.out.println("timeAtPress: " + timeAtPress);
        System.out.println("timeAtLift: " + keyUpScore);
        System.out.println("correctTimes.get(currNote): " + correctTimes.get(currNote));
        System.out.println("correctTimes.get(currNote+1): " + correctTimes.get(currNote));
        System.out.println("keyDownScore: " + keyDownScore);
        System.out.println("keyUpScore: " + keyUpScore);
        System.out.println("buffer: " + buffer);
        */       
             
    }
    
    /*
    public void printScores() {
        
        if (!scoresPrinted) {
            for (boolean b : scores) {
                //System.out.println(b);
            }
        }
        scoresPrinted = true;
    }
    */
    
    // very inefficient way of playing 4 beats before the game actually begins
    public void firstFour() {
        
        long currTime = System.currentTimeMillis();
        long elapsedTime = currTime - timeOfLastBeep;
        
        if (firstBeat) {
            if (elapsedTime >= millisPerBeat) { // >= logic used because elapsedTime in millis may not be exactly multiples of millisPerBeat
                showText("3", 500, 200);
                playSound(); // beep or boing
                timeOfLastBeep = currTime;
                firstBeat = false;
                secondBeat = true;
            }
        }
        else if (secondBeat) {
            if (elapsedTime >= millisPerBeat) {
                showText("2", 500, 200);
                playSound();
                timeOfLastBeep = currTime;
                secondBeat = false;
                thirdBeat = true;
            }
        }
        else if (thirdBeat) {
            if (elapsedTime >= millisPerBeat) {
                showText("1", 500, 200);
                playSound();
                timeOfLastBeep = currTime;
                thirdBeat = false;
                fourthBeat = true;
            }
        }
        else if (fourthBeat) {
            if (elapsedTime >= millisPerBeat) {
                showText("go", 500, 200);
                playSound();
                timeOfLastBeep = currTime;
                fourthBeat = false;
                start = true;
                addObject(light, 500, 450);
            }
        } 
        else if (start) { // first beat
            if (elapsedTime >= millisPerBeat) {
                // showText("1", 500, 200);
                showText("", 500, 200);
                beat++;
                playSound();
                timeOfLastBeep = currTime;
                start = false;
                doNoteCheck = true;
                startTime = System.currentTimeMillis(); // official start time
                addObject(light, 500, 450);
                light.turnOn();
            }
        }
                
    }
    
    public void metronome() {
        
        long currTime = System.currentTimeMillis();
        long millisSinceLastBeep = currTime - timeOfLastBeep;
        
        if (millisSinceLastBeep > millisPerBeat) {
            playSound();
            light.turnOn();    
            // showText("" + beat, 500, 200);
            beat++;
            timeOfLastBeep = currTime;                  
        }
        else if (millisSinceLastBeep > 200 && light.isOn) {
            light.turnOff(); // light turns off after 200 milliseconds of being on
        }
        //showText("" + (currTime - startTime), 500, 170);        
    }
    
    // to play boing easter egg
    public void playSound() {
        if (boing) {
                Greenfoot.playSound("boing.mp3");
        }
        else {
            Greenfoot.playSound("beep.mp3");
        }
    }
    
    // fills notes array
    public void readFile() throws FileNotFoundException {
        String levelFile = "level" + level + ".txt";  
        Scanner file = new Scanner(new FileReader("levels/" + levelFile));
        
        timeSignature = file.nextLine();
        
        while (file.hasNextLine()) {
            notes.add(file.nextLine());
        }
                
    }
    
    // creates correctTimes array with start/end times for each note/rest
    public void populateCorrectTimes() {
        
        t = new Times(noteObjects);
        correctTimes = t.getTimes();
        
    } 
       
    // displays notes/rests/bars on screen according to notes array
    public void showNotes() {
        int a = 35;
        for (int i = 0; i < notes.size(); i++) {
            String n = notes.get(i);
            if (n.equals("1")) {
                whole w = new whole();
                noteObjects.add(w);
                addObject(w, 70+a, 298);
            }
            else if (n.equals("2")) {
                half h = new half();
                noteObjects.add(h);                
                addObject(h, 70+a, 280);
            }
            else if (n.equals("3")) {
                quarter q = new quarter();
                noteObjects.add(q);
                addObject(q, 70+a, 280);
            }
            else if (n.equals("4")) {
                eighth e = new eighth();
                noteObjects.add(e);
                addObject(e, 70+a, 278);
            }
            else if (n.equals("5")) {
                sixteenth s = new sixteenth();
                noteObjects.add(s);
                addObject(s, 70+a, 279);
            }
            else if (n.equals("/")) {
                bar b = new bar();
                addObject(b, 70+a, 280);
            }
            else if (n.equals("//")) {
                doublebar db = new doublebar();
                addObject(db, 70+a, 280);
            }
            else if (n.equals("R1")) {
                wrest wr = new wrest();
                noteObjects.add(wr);
                addObject(wr, 70+a, 280);
            }
            else if (n.equals("R2")) {
                hrest hr = new hrest();
                noteObjects.add(hr);
                addObject(hr, 70+a, 285);
            }
            else if (n.equals("R3")) {
                qrest qr = new qrest();
                noteObjects.add(qr);
                addObject(qr, 70+a, 280);
            }
            else if (n.equals("R4")) {
                erest er = new erest();
                noteObjects.add(er);
                addObject(er, 70+a, 280);
            }
            else if (n.equals("R5")) {
                srest sr = new srest();
                noteObjects.add(sr);
                addObject(sr, 70+a, 280);
            }
            a+=40;
        }
    }
    
    // displays staff lines on screen
    public void showStaffLines() {
        addObject(new sheet_music(), 500, 280);
    }
    
    // displays time signature on screen
    // (levels are all four four so far)    
    public void showTimeSignature() {
        if (timeSignature.equals("FF")) {
            addObject(new fourfour(), 70, 281);
        }
    }
    
    // used in sheet_music subclasses to determine length
    public double getMillisPerBeat() {
        return millisPerBeat;
    }
    
    public void debug() {
        //System.out.println("time " + timeSignature);
        //System.out.println("bpm " + bpm);
        //for (double[] x : correctTimes) {
            //System.out.println("down at " + x[0]);
            //System.out.println("up at " + x[1]);
        //}
    }  
    
}

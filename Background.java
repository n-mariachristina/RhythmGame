import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Background extends World
{

    Button startButton = new Button();
    Button moreInfoButton = new Button();
    
    Conductor conductor = new Conductor();
    
    boolean playingGame = false;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Background()
    {    
        super(1000, 560, 1); 
        prepare();
        
        GreenfootImage img = new GreenfootImage("placeholder.png");
        img.scale(img.getWidth()/2, img.getHeight()/2);
        setBackground(img);
    }
    
    public void prepare() {
        // addObject(conductor, 512, 450);
        // addObject(platform, 512, 650);
        
        addObject(startButton, 300, 400);
        addObject(moreInfoButton,700, 400);
        showButtonOptions();
    }
    
    public void act() {
        checkSelection();
        if (playingGame) {
            playGame();
        }
    }
    
    public void showButtonOptions() {
        showText("START", 300, 400);
        showText("MORE INFO", 700, 400);
    }
    
    public void checkSelection() {
        if (Greenfoot.mouseClicked(startButton)) {
            begin();
            playingGame = true;
            removeObject(startButton);
            removeObject(moreInfoButton);
        }
        if (Greenfoot.mouseClicked(moreInfoButton)) {
            rulesAndCredits();
            removeObject(startButton);
            removeObject(moreInfoButton);
        }
    }
    
    public void begin() {
        setBackground(new GreenfootImage("stage.jpg"));
        addObject(conductor, 500, 300);
        playGame();
    }
    
    public void rulesAndCredits() {
        setBackground(new GreenfootImage("stage.jpg"));
    }
    
    public void playGame() {
        if (Greenfoot.isKeyDown("space")) {
            conductor.changeColor();
        }
    }
    
    /*
    public void act() {
        time++;
        showTime();
        rhythm();
        showScore();
        
        if (time > 300) {
            showSelection();
            select();
        }
    }
    
    private void showTime() {
        showText("Time: " + time, 512, 450);
    }
    
    public void gravity() {
        if (time % 10 == 0) {
            conductor.setLocation(conductor.getX(), conductor.getY()+5);
        }
    }
    
    private void rhythm() {
        if (input[0] == -1) {
            if (Greenfoot.isKeyDown("space")) {
                input[0] = time;
            }
        }
        else if (input[1] == -1) {
            if (Greenfoot.isKeyDown("space")) {
                if (time - input[0] > 10) {
                    input[1] = time;
                }
            }
        }
    }
    
    private void showScore() {
        if (input[1] != -1) {
            showText("Inputs at: " + input[0] + " and " +input[1], 512, 500);
            showText("Score is " + score(), 512, 550);
        }
    }
    
    private int score() {
        int score = 100 - Math.abs(times[0]-input[0]) - Math.abs(times[1]-input[1]);
        return score;
    }
    
    private void showSelection() {        
        GreenfootImage img = new GreenfootImage("violin.png");
        img.scale(img.getWidth()/2, img.getHeight()/2);
        violin1.setImage(img);
        violin2.setImage(img);
        addObject(violin1, 512, 100);
        addObject(violin2, 512, 700);          
    }
    
    private void select() {
        if (Greenfoot.mouseClicked(violin1)) {
            Greenfoot.playSound("boing.mp3");
        }
        if (Greenfoot.mouseClicked(violin2)) {
            Greenfoot.playSound("fanfare.wav");
        }
    }
    */
}

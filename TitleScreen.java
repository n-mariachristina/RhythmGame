import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{
    private Button startButton = new Button();
    private Button moreInfoButton = new Button();
    
    private boolean playingGame = false;
    private boolean infoScreen = false;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public TitleScreen()
    {    
        super(1000, 560, 1); 
        prepare();
        
        GreenfootImage img = new GreenfootImage("placeholder.png");
        img.scale(img.getWidth()/2, img.getHeight()/2);
        setBackground(img);
    }
    
    public void prepare() {
        addObject(startButton, 300, 400);
        addObject(moreInfoButton,700, 400);
        showButtonOptions();
    }
    
    public void act() {
        showButtonOptions();
        checkStartSelection();
       
        if (playingGame) {
            Greenfoot.setWorld(new LevelSelect());
        }
        if (infoScreen) {
            Greenfoot.setWorld(new Info());
        }
    }
    
    public void showButtonOptions() {
        showText("START", 300, 400);
        showText("MORE INFO", 700, 400);
    }
    
    public void checkStartSelection() {
        if (Greenfoot.mouseClicked(startButton)) {
            playingGame = true;
        }
        if (Greenfoot.mouseClicked(moreInfoButton)) {
            infoScreen = true;
        }
    }    
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{
    
    private AnyButton startButton = new AnyButton();
    private AnySmall moreInfoButton = new AnySmall();
    
    private boolean playingGame = false;
    private boolean infoScreen = false;
    
    // using boings instead of beeps (easter egg)
    boolean boing = false;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public TitleScreen()
    {    
        super(1000, 560, 1); 
        prepare();
                
        GreenfootImage img = new GreenfootImage("RHYTHMOS.png");
        img.scale(img.getWidth()/2, img.getHeight()/2);
        setBackground(img);        
        
    }
    
    public void prepare() {
        addObject(startButton, 500, 170);
        addObject(moreInfoButton,900, 40);
    }
    
    public void act() {
        checkStartSelection();
        if (playingGame) {
            // false false indicates levels 2 and 3 are locked
            Greenfoot.setWorld(new LevelSelect(false, false));
        }
        if (infoScreen) {
            Greenfoot.setWorld(new Info());
        }
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

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Info here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Info extends World
{

    private Button goBack = new Button();
    
    /**
     * Constructor for objects of class Info.
     * 
     */
    public Info()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 560, 1); 
        
        prepare();
    }
    
    public void act() {
        checkGoBack();
    }
    
    public void prepare() {
        
        addObject(goBack, 500, 500);
        showText("Go back", 500, 500);
        
        showText("HOW TO PLAY", 300, 100);
        showText("i dont know how to play.", 300, 200);
        showText("the game hasnt been made yet...", 300, 300);
        showText("CREDITS", 700, 100);
        showText("girlbosses", 700, 200);
    }
    
    public void checkGoBack() {
        if (Greenfoot.mouseClicked(goBack)) {
            Greenfoot.setWorld(new TitleScreen());
        }
    }
}

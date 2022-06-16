import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Info here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Info extends World
{

    private AnyButton goBack = new AnyButton();
    
    // acts as button
    Extra extra = new Extra();
    
    // whether to use boings instead of beeps
    static boolean boing = false;
    
    /**
     * Constructor for objects of class Info.
     * 
     */
    public Info()
    {    
        super(1000, 560, 1);         
        prepare();
    }    
       
    public void prepare() {
        
        addObject(goBack, 100, 318);
        
        addObject(extra, 500, 50);
                
    }
    
    public void act() {
        checkGoBack();
        checkEasterEgg();
    }
    
    public void checkEasterEgg() {
        if (Greenfoot.mouseClicked(extra)) {
            boing = true;
        }
    }
    
    public void checkGoBack() {
        if (Greenfoot.mouseClicked(goBack)) {
            Greenfoot.setWorld(new TitleScreen());
        }
    }
    
}

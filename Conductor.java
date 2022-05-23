import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Conductor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Conductor extends Actor
{
    
    /**
     * Act - do whatever the Conductor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        changeColor();        
    }
   
    public void changeColor() {
       if (Greenfoot.isKeyDown("space")) {
        setImage(new GreenfootImage("conductor2.png"));
        Greenfoot.delay(5);
        setImage(new GreenfootImage("conductor.png"));
       }
    }
    
}

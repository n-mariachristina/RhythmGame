import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lock extends Actor
{
    
    // lock image on locked levels
    
    public Lock() {
        GreenfootImage img = new GreenfootImage("lock.png");
        img.scale(img.getWidth()/4,img.getHeight()/4);
        //img.scale(1000, 600); // dimensions of the button
        setImage(img);
    }
        
}

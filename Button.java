import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
        
    // play button for unlocked levels
    
    public Button() {
        GreenfootImage startImg = new GreenfootImage("button.png");
        startImg.scale(startImg.getWidth()/2,startImg.getHeight()/2);
        setImage(startImg);
    }
}

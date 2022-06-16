import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Light here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Light extends Actor
{
    
    // light that turns on as a visual indicator for metronome beeps
    
    boolean isOn;
    
    GreenfootImage onImg = new GreenfootImage("lighton.png");
    GreenfootImage offImg = new GreenfootImage("lightoff.png");
    
    public Light() {
        onImg.scale(onImg.getWidth()/2, onImg.getHeight()/2);  
        offImg.scale(offImg.getWidth()/4, offImg.getHeight()/4);
        
        setImage(offImg);
    }
    
    // from levels class, is turned on, then turned off
    public void turnOn() {
        setImage(onImg);
        isOn = true;
    }
    
    public void turnOff() {
        setImage(offImg);
        isOn = false;
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class quarter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class quarter extends sheet_music
{
      
    public void turnRed() {
        setImage(new GreenfootImage("quarter_red.png"));
    }
    
    public void turnGreen() {
        setImage(new GreenfootImage("quarter_green.png"));
    }
    
    public double getLength() {
        return ((Levels)getWorld()).getMillisPerBeat();
    }
    
    public boolean isRest() {
        return false;
    }
}

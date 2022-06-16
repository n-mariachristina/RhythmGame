import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class eighth here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class eighth extends sheet_music
{
    
    public void turnRed() {
        setImage(new GreenfootImage("eighth_red.png"));
    }
    
    public void turnGreen() {
        setImage(new GreenfootImage("eighth_green.png"));
    }
    
    public double getLength() {
        return ((Levels)getWorld()).getMillisPerBeat() / 2;
    }
    
    public boolean isRest() {
        return false;
    }
}

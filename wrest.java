import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class wrest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class wrest extends sheet_music
{
    
    boolean red = false;
        
    public void turnRed() {
        setImage(new GreenfootImage("wrest_red.png"));
        red = true;
    }
    
    public void turnGreen() {
        setImage(new GreenfootImage("wrest_green.png"));
    }
    
    public boolean isRed() {
        return red;
    }
    
    public double getLength() {
        return ((Levels)getWorld()).getMillisPerBeat() * 4;
    }
    
    public boolean isRest() {
        return true;
    }
}

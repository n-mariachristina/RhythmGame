import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class erest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class erest extends sheet_music
{
    
    boolean red = false;

    public void turnRed() {
        setImage(new GreenfootImage("erest_red.png"));
        red = true;
    }
    
    public void turnGreen() {
        setImage(new GreenfootImage("erest_green.png"));
    }
    
    public boolean isRed() {
        return red;
    }
    
    public double getLength() {
        return ((Levels)getWorld()).getMillisPerBeat() / 2;
    }
    
    public boolean isRest() {
        return true;
    }
}

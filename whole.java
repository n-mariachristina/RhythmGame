import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class whole here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class whole extends sheet_music
{
 
    public void turnRed() {
        setImage(new GreenfootImage("whole_red.png"));
    }
    
    public void turnGreen() {
        setImage(new GreenfootImage("whole_green.png"));
    }
    
    public double getLength() {
        return ((Levels)getWorld()).getMillisPerBeat() * 4;
    }
    
    public boolean isRest() {
        return false;
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lose here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lose extends Actor
{
    
    // lose screen after failing level
    
    public Lose() {
        GreenfootImage Lose = new GreenfootImage("Lose.png");
        Lose.scale(Lose.getWidth(),Lose.getHeight());
        setImage(Lose);
    }
}

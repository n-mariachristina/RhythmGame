import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Win here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Win extends Actor
{

    // win screen after passing level

    public Win() {
        GreenfootImage Win = new GreenfootImage("Win.png");
        Win.scale(Win.getWidth(),Win.getHeight());
        setImage(Win);
    }

}


import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Extra here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Extra extends Actor
{
    
    // used as transparent button
    
    public Extra() {
        GreenfootImage buttonImg = new GreenfootImage("Extra.png");
        buttonImg.scale(buttonImg.getWidth(),buttonImg.getHeight());
        setImage(buttonImg);
    }
}

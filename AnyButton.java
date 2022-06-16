import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AnyButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AnyButton extends Actor
{
      
    // used as transparent button
    
    public AnyButton() {
        GreenfootImage buttonImg = new GreenfootImage("any.png");
        buttonImg.scale(buttonImg.getWidth()/3,buttonImg.getHeight()/6);
        setImage(buttonImg);
    }
}

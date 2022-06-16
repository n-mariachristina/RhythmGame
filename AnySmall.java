import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AnySmall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AnySmall extends Actor
{
    
    // used as transparent button
    
      public AnySmall() {
        GreenfootImage buttonImg = new GreenfootImage("anysmall.png");
        buttonImg.scale(buttonImg.getWidth()/10,buttonImg.getHeight()/15);
        setImage(buttonImg);
    }
}

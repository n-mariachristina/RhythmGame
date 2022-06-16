import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Done here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Done extends World
{
    
    // song that plays after you beat the game
    GreenfootSound song = new GreenfootSound ("rhythmos_bgm.wav");

    /**
     * Constructor for objects of class Done.
     * 
     */
    public Done()
    {    
        super(1000, 560, 1); 
        song.setVolume(80);
        song.play();
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LevelSelect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LevelSelect extends World
{

    final int NUM_LEVELS = 3;
    
    private Button[] levels = new Button[NUM_LEVELS];
    
    // not sure how to organize this lol
    // idea is to have levels locked before the previous ones are completed
    // 2d array?
    
    /**
     * Constructor for objects of class LevelSelect.
     * 
     */
    public LevelSelect()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 560, 1); 
    }   
    
}

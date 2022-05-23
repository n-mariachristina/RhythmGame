import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game extends World
{

    private Conductor conductor = new Conductor();
    
    /**
     * Constructor for objects of class Game.
     * 
     */
    public Game()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 560, 1);
        setBackground(new GreenfootImage("stage.jpg"));
        addObject(new Conductor(), 500, 300);
        
    }
    
    public void act() {
        playGame();
    }
    
    public void playGame() {
        if (Greenfoot.isKeyDown("space")) {
            conductor.changeColor();
        }
    }

}

import java.io.FileNotFoundException;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LevelSelect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LevelSelect extends World
{
    Levels level1; 
    Levels level2; 
    Levels level3; 

    // made all the levels outside, to reference variables

    Button levelOne = new Button();
    Button levelTwo = new Button();
    Button levelThree = new Button();

    // act as buttons
    Extra extra1 = new Extra();
    Extra extra2 = new Extra();
    Extra extra3 = new Extra();

    Lock levelTwoLock = new Lock();
    Lock levelThreeLock = new Lock();

    boolean lvl2;
    boolean lvl3;

     /**
     * Constructor for objects of class LevelSelect.
     * 
     */
    public LevelSelect(boolean l2, boolean l3)
    {    
        super(1000, 560, 1);

        prepare();
        act();

        // lvl2 and lvl3 indicate whether the levels are locked
        lvl2 = l2;
        lvl3 = l3;

        if (lvl2) {
            unlockLevelTwo();
        }

        if (lvl3) {
            unlockLevelThree();
        }
      

    }  

    public void act() {
        if (Greenfoot.mouseClicked(extra1)) {  
            try {
                level1 = new Levels(1, 60);
                Greenfoot.setWorld(level1);
            }
            catch (FileNotFoundException ex) {
            }        
        }
        if (Greenfoot.mouseClicked(levelTwo)) {
            try {
                level2 = new Levels(2, 90);
                Greenfoot.setWorld(level2);
            }
            catch (FileNotFoundException ex) {
            } 
        }
        if (Greenfoot.mouseClicked(levelThree)) {
            try {
                level3 = new Levels(3, 120);
                Greenfoot.setWorld(level3);
            }
            catch (FileNotFoundException ex) {
            } 

        }
    }

    public void prepare() {
        addObject(levelOne, 250, 290);
        addObject(levelTwoLock, 500, 280);
        addObject(levelThreeLock, 745, 280);
        
        addObject(extra1, 250, 290);
        addObject(extra2, 500, 280);
        addObject(extra3, 745, 280);
    }

    public void unlockLevelTwo() {
        removeObject(levelTwoLock);
        addObject(levelTwo, 500, 290);
    }

    public void unlockLevelThree() {
        removeObject(levelThreeLock);
        addObject(levelThree, 750, 290);
    }

}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Times here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Times extends Actor
{

    // takes a notes objects array, and creates array of number pairs
    // for notes: key down and key up times
    // for rests: rest start and rest end times

    private ArrayList<sheet_music> notesObjects;

    private ArrayList<double[]> times;

    public Times(ArrayList<sheet_music> n) {
        notesObjects = n;
        createTimes();
    }

    public void createTimes() {

        times = new ArrayList<double[]>();

        double runningTime = 0; // keeps track of the time we're at as we add notes

        for (int i = 0; i < notesObjects.size(); i++) {

            // current note/rest
            sheet_music n = notesObjects.get(i);

            // first value (key down or rest start time) time is the time we're at
            // second value that time + the length of the note/rest
            double[] t = {runningTime, runningTime + n.getLength()};
            times.add(t);

            // add to the time we're at 
            runningTime += n.getLength();

        }

        // for debugging
        for (int i = 0; i < times.size(); i++) {
            // System.out.println("down at " + times.get(i)[0]);
            // System.out.println("up at " + times.get(i)[1]);
        }

    }

    public ArrayList<double[]> getTimes() {
        return times;
    }
}
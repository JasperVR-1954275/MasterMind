package Mastermind;


import java.util.Vector;

/**
 * This interface defines the method for the different computer strategies
 *
 *
 */
public interface MasterStrategy {
    /* Make a single move as maker or continues moves as breaker */

    /**
     * Generate a random new row which can be used as computer maker row
     * or a new guess from the computer breaker
     * @return A row of length $codeLength where every element is a random color from Mastermind.COLOR
     * @pre There is atleast one free row available to make a new random row
     */
    public Vector<Mastermind.COLOR> generateCode();
}

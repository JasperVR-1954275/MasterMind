package Mastermind;


import java.util.Vector;

/**
 * This interface defines the method for the different computer strategies
 * @author Group 2
 */
public interface MasterStrategy {
    /**
     * Generate a random new code which can be used as computer maker code
     * or a new guess from the computer breaker
     * @return A code of length $codeLength where every element is a random color from Mastermind.COLOR
     * @pre There is atleast one free code available to make a new random code
     */
    Vector<Mastermind.COLOR> generateCode();
}

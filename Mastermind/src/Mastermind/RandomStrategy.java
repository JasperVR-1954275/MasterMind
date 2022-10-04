package Mastermind;

import java.util.Vector;

/**
 * RandomStrategy chooses random colors
 * @author Group 2
 */
class RandomStrategy implements MasterStrategy {

    /**
     * Generate a random new code which can be used as computer maker code
     * or a new guess from the computer breaker
     * @return A code of length $codeLength where every element is a random color from Mastermind.COLOR
     * @pre There is atleast one free code available to make a new random code
     */
    public Vector<Mastermind.COLOR> generateCode() {
        Vector<Mastermind.COLOR> generatedCode = new Vector<>();
        int colorValue;
        for (int i=0; i < Mastermind.$codeLength; i++){
            colorValue = (int)(10 * Math.random()) % Mastermind.$colorAmount;
            generatedCode.add(Mastermind.getColor(colorValue));
        }
        return generatedCode;
    }

}


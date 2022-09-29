package Mastermind;

import java.util.Vector;

class RandomStrategy implements MasterStrategy {
    // Return 1 row of length codeLength and every item is a random color

    public Vector<Mastermind.COLOR> generateCode() {
        Vector<Mastermind.COLOR> generatedCode = null;
        int colorValue;
        for (int i =0;i< Mastermind.$codeLength;i++){
            colorValue = (int)(10 * Math.random()) % Mastermind.$colorAmount;
            generatedCode.add(Mastermind.getColor(colorValue));
        }
        return generatedCode;
    }





}

/*
public void doMove(VorMatrix vMatrix) {
        int colorNumber;
        while (true) {
            colorNumber = (int) (10 * Math.random()) % Mastermind.$colorAmount;
            Mastermind.COLOR selectedColor = Mastermind.getColor(colorNumber);
            break;
        }

    }
*/

package Mastermind;

import java.util.Vector;

class RandomStrategy implements MasterStrategy {
    /**
     *
     * @param vMatrix
     */
    // Return 1 row of length codeLength and every item is a random color
    private Vector<Mastermind.COLOR> generatedCode;

    public Vector<Mastermind.COLOR> generateCode() {
        int colorValue;
        for (int i =0;i< Mastermind.$codeLength;i++){
            colorValue = (int)(10 * Math.random()) % Mastermind.$colorAmount;
            generatedCode.add(Mastermind.getColor(colorValue));
        }
        return generatedCode;
    }
    public Vector<String> checkGivenCode(Vector<Mastermind.COLOR> givenCode){
        Vector<Integer>  
        for (int i =0; i < generatedCode.size(); i++){
            if(generatedCode.get(i) == givenCode.get(i)){

            }
            for (int j = 0; j < givenCode.size();j++){

            }

        }
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

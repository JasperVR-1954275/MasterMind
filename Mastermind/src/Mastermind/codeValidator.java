package Mastermind;

import java.util.Vector;

/**
 * Goes over code for pins based on
 * color and position of code elements
 * @author Group 2
 */
public class CodeValidator {
    /**
     * Check given code with keycode for red en white pins
     * @param inputCode code to be checked given by codebreaker
     * @pre non-empty vector inputCode
     * @return vector with amount of red and white pins
     */
    public Vector<Integer> checkGivenCode(Vector<MasterMindModel.COLOR> inputCode) {
        Vector<Integer> keyPins = new Vector<>();
        Vector<Integer> redPos = calculateRedPins(inputCode);
        int redPins = redPos.size();
        int whitePins = calculateWhitePins(inputCode, redPos);
        keyPins.add(redPins);// red pins
        keyPins.add(whitePins);// white pins
        return keyPins;
    }

    /**
     * Check given code for occurrence of colors in keycode
     * @param inputCode code to be checked given by codebreaker
     * @param redPos positions where red pin was matched
     * @pre non-empty vector inputCode
     * @return the amount of white pins
     */
    public Integer calculateWhitePins(Vector<MasterMindModel.COLOR> inputCode, Vector<Integer> redPos) {
        int whitePins = 0;
        for (int x = 0; x < MasterMindModel.getKeyCode().size(); x++) {
            if (!redPos.contains(x)) {
                for (int y = 0; y < MasterMindModel.getKeyCode().size(); y++) {
                    if (!redPos.contains(y) && MasterMindModel.getKeyCode().get(y) == inputCode.get(x)) {
                        whitePins++;
                        break;
                    }
                }
            }
        }
        return whitePins;
    }


    /**
     * Check given code for match with color and position in keycode
     * @param inputCode code to be checked given by codebreaker
     * @pre non-empty vector inputCode
     * @return the positions of the pins in inputcode and keycode that have the same color and position
     */
    public Vector<Integer> calculateRedPins(Vector<MasterMindModel.COLOR> inputCode) {
        Vector<Integer> redPos = new Vector<>();
        for (int i = 0; i < MasterMindModel.getKeyCode().size(); i++) {
            if (MasterMindModel.getKeyCode().get(i) == inputCode.get(i)) {
                redPos.add(i);
            }
        }
        return redPos;
    }
}

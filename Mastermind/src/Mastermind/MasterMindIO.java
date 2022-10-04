package Mastermind;

import java.util.Scanner;
import java.util.Vector;

/**
 * Handles all input/output
 * @author Group 2
 */
public class MasterMindIO {

    private final Scanner s = new Scanner(System.in);

    /**
     * Reads input for the setup of the game.
     * @return vector of the input the user gave.
     * [0]: Amount of colors.
     * [1]: Amount of tries the codebreaker gets.
     * [2]: The length of the code.
     * [3]: 0 if the player wants to be the codemaker, and 1 if the player wants to be the codebreaker.
     * @post all the setup input is read
     */
    public Vector<Integer> readGameSetupInput() {
        Vector<Integer> input = new Vector<>();
        settingInput("Amount of colors? (Max: 9)", input);
        while (input.get(0) > 9 || input.get(0) <= 0) {
            input = new Vector<>();
            settingInput("Amount of colors? (Max: 9)", input);
        }
        settingInput("Amount of tries?", input);
        settingInput("Length of code?", input);
        System.out.println("Would you like to play as 'codebreaker' or 'codemaker'?");
        while (true) {
            String decision = s.nextLine();
            if (decision.equalsIgnoreCase("codemaker")) {
                input.add(0);
                break;
            } else if (decision.equalsIgnoreCase("codebreaker")) {
                input.add(1);
                break;
            } else {
                System.out.println("Wrong input. 'codebreaker' or 'codemaker'?");
            }
        }
        return input;
    }

    /**
     * Helper function for readGameSetupInput()
     * @param text question that we ask the user
     * @param input vector input where we store the users answer
     */
    private void settingInput(String text, Vector<Integer> input) {
        while (true) {
            try {
                System.out.println(text);
                input.add(Integer.parseInt(s.nextLine()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong input");
            }
        }
    }

    /**
     * Get input from user to make a new guess
     * @return input code of length $colorAmount
     */
    public Vector<MasterMindModel.COLOR> readAttemptInput() {
        Vector<MasterMindModel.COLOR> attempt = new Vector<>();
        int amountOfColorsGiven = 0;
        while (amountOfColorsGiven < MasterMindModel.$codeLength) {
            System.out.print("Give me a color for column: " + amountOfColorsGiven + "\n");
            String userInput = s.nextLine();
            if (userInput instanceof String) {
                for (int colorIndex = 0; colorIndex < MasterMindModel.$colorAmount; colorIndex++) {
                    if (userInput.equalsIgnoreCase(MasterMindModel.getColor(colorIndex).toString())) {
                        attempt.add(MasterMindModel.COLOR.valueOf(userInput.toUpperCase()));
                        amountOfColorsGiven++;
                        break;
                    }
                }
            }
        }
        return attempt;
    }

     /**
     * Prints out the given amount of red and white pins
     * @param pins vector with red and white pin count
     */
    public void writePinAmount(Vector<Integer> pins) {
        System.out.print("Number of red pins: " + pins.get(0) + ", number of white pins: " + pins.get(1) + "\n");
    }

    /**
     * Prints out which player won and the attempts that have been used
     * @param text the text to be printed
     * @param attemptsUsed the number of attempts to be printed
     */
    public void writeVictory(String text, Integer attemptsUsed) {
        System.out.print(text + " Attempts: " + attemptsUsed);
    }

    /**
     *Prints out the current attempt
     * @param attempt a vector of colors from the last attempt
     */
    public void writeCurrentAttempt(Vector<MasterMindModel.COLOR> attempt) {
        System.out.print("Attempt: ");
        for (MasterMindModel.COLOR color : attempt) {
            System.out.print(color + " ");
        }
        System.out.print("\n");
    }
}
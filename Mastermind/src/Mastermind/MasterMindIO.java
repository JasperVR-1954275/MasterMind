package Mastermind;

import java.util.Scanner;
import java.util.Vector;


public class MasterMindIO {
    private final Scanner s = new Scanner(System.in);

    public Vector<Integer> readGameSetupInput() {
        Vector<Integer> input = new Vector<Integer>();
        settingInput("Amount of colors? (Max: 9)", input);
        while (input.get(0) > 9) {
            input = null;
            settingInput("Amount of colors? (Max: 9)", input);
        }
        settingInput("Amount of tries?", input);
        settingInput("Length of code?", input);
        System.out.println("Would you like to play as 'codebreaker' or 'codemaker'?");
        while (true) {
            String decision = s.nextLine();
            if (decision.toLowerCase().equals("codemaker")) {
                input.add(0);
                break;
            } else if (decision.toLowerCase().equals("codebreaker")) {
                input.add(1);
                break;
            } else {
                System.out.println("Bad input. 'Codebreaker' or 'codemaker'?");
            }
        }
        return input;
    }

    private void settingInput(String text, Vector<Integer> input) {
        while (true) {
            try {
                System.out.println(text);
                input.add(Integer.parseInt(s.nextLine()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Bad input");
            }
        }
    }

    /**
     * @return
     */
    public Vector<Mastermind.COLOR> readAttemptInput() {
        Vector<Mastermind.COLOR> attempt = new Vector<>();
        int amountOfColorsGiven = 0;
        while (amountOfColorsGiven < Mastermind.$codeLength) {
            System.out.print("Give me a color for column: " + amountOfColorsGiven + "\n");
            String userInput = s.nextLine();
            for (int colorIndex = 0; colorIndex < Mastermind.$colorAmount; colorIndex++) {
                if (Mastermind.COLOR.valueOf(userInput.toUpperCase()) == Mastermind.getColor(colorIndex)) {
                    attempt.add(Mastermind.COLOR.valueOf(userInput.toUpperCase()));
                    amountOfColorsGiven++;
                    break;
                }
            }
        }
        return attempt;
    }

    public void writePinsOutput(Vector<Integer> pins) {
        System.out.print("Number of red pins: " + pins.get(0) + ", number of white pins: " + pins.get(1) + "\n");
    }

    public void writeUserVictoryOutput() {
        System.out.print("Codebreaker has won!");
    }

    public void writeComputerVictoryOutput() {
        System.out.print("Codemaker has won. You ran out of attempts!");
    }

    public void writeAttemptOutput(Vector<Mastermind.COLOR> attempt) {
        System.out.print("Attempt: ");
        for (int i = 0; i < attempt.size(); i++) {
            System.out.print(attempt.get(i) + " ");
        }
    }
}
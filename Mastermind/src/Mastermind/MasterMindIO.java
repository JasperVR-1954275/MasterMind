package Mastermind;

import java.util.Locale;
import java.util.Scanner;
import java.util.Vector;


public class MasterMindIO {
    private final Scanner s = new Scanner(System.in);

    public Vector<Integer> readGameSetupInput(){
        Vector<Integer> input = new Vector<Integer>();

        try {
            System.out.println("Amount of colors?");
            input.add(Integer.parseInt(s.nextLine()));
        } catch (NumberFormatException ex) {
            System.out.println("Bad input");
        }

        try {
            System.out.println("Amount of tries?");
            input.add(Integer.parseInt(s.nextLine()));
        } catch (NumberFormatException ex) {
            System.out.println("Bad input");
        }

        try {
            System.out.println("Length of code?");
            input.add(Integer.parseInt(s.nextLine()));
        } catch (NumberFormatException ex) {
            System.out.println("Bad input");
        }

        System.out.println("Would you like to play as codebreaker or codemaker?");
        while (true) {
            String decision = s.nextLine();
            if (decision.toLowerCase(Locale.ROOT).equals("codemaker")) {
                input.add(0);
                break;
            } else if (decision.toLowerCase(Locale.ROOT).equals("codebreaker")) {
                input.add(1);
                break;
            } else {
                System.out.println("Bad input. Codebreaker or codemaker?");
            }
        }
        return input;
    }

}
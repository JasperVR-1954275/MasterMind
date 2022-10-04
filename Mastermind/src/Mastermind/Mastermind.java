package Mastermind;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

/**
 * Main user interface for the application. Makes sure the model, views
 * and controllers are connected to each other.
 * @author Group 2
 */
public class Mastermind {
    // MasterMind data (model)
    private MasterMindModel mModel;

    // MasterMind view
    private MasterMindView mView;

    // A toolbar for buttons
    private MasterMindTools mTools;






    // move this all somewhere else

    public static int $codeLength = 4;
    public static int $colorAmount = 9;
    private static Mastermind $main;
    private int $attemptsLimit = 12;
    private Vector<Mastermind.COLOR> $keyCode;
    public enum COLOR { RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE, PINK, WHITE, BLACK }
    private final MasterStrategy $MasterStrategy;
    private final MasterMindIO $MasterIO;
    private final CodeValidator $CodeValidator;


    public void createGUI() {
        JFrame frame = new JFrame("MasterMind");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        frame.getContentPane().add(mView);
        frame.getContentPane().add(mTools);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.pack();
        frame.setVisible(true);

    }
    private static void createAndShowGUI() {
        // singleton principle?
        Mastermind mastermind = new Mastermind();
        mastermind.createGUI();
    }


    /**
     * The main method -- the program starts here
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });



//        try {
//            Mastermind.getInstance().start();
//        } catch (IllegalArgumentException e) {
//            System.out.print("Wrong inputs");
//        } catch (Exception e) {
//            System.out.print("Program failed, try again");
//        }
    }

    /**
     * Returns the value of the COLOR enum at a location
     * @param location the wanted location
     * @pre location is in range of the COLOR enum
     * @return the COLOR enum value
     */
    public static COLOR getColor(int location) {
        return COLOR.values()[location];
    }

    /**
     * getter for keycode
     * @return keycode
     */
    public Vector<Mastermind.COLOR> getKeyCode() {
        return $keyCode;
    }

    /**
     * Initialized the Mastermind object.
     * It initializes a new RandomStrategy, a new MasterMindIO, and a new CodeValidator
     */
    private Mastermind() {
        // Create the data model
        mModel = new MasterMindModel();

        // Create the view
        mView = new MasterMindView();
        mModel.addObserver(mView);

        // Create the tools view
        mTools = new MasterMindTools();
        mModel.addObserver(mTools);





        // move elsewhere:
        $MasterStrategy = new RandomStrategy();
        $MasterIO = new MasterMindIO();
        $CodeValidator = new CodeValidator();
    }

    /**
     * Part of the Singelton pattern to have only one Mastermind object at all time and
     * that makes it universally accessible
     * @return the singleton Mastermind object
     */
    public static Mastermind getInstance() {
        if ($main == null)
            $main = new Mastermind();
        return $main;
    }

    /**
     * Asks the game settings (number of colors, number of attempts, length of code)
     * and starts the game loop.
     * @post $colorAmount, $attemptsLimit, and $colorLength are set to desired values
     */
    private void start() {
        Vector<Integer> setupInput;
        setupInput = $MasterIO.readGameSetupInput();
        $colorAmount = setupInput.get(0);
        $attemptsLimit = setupInput.get(1);
        $codeLength = setupInput.get(2);
        if ($attemptsLimit <= 0 || $codeLength <= 0) {
            throw new IllegalArgumentException();
        }
        Mastermind.getInstance().playGame(setupInput.get(3) == 0);
    }

    /**
     * The game loop. If codeMaker, the user creates a key and the computer guesses randomly.
     * If codeBreaker, a random key will be generated and the user guesses.
     * The game keeps looping until the key is guessed or the number of attempts has been exceeded.
     * @param codeMaker boolean: true if codeMaker, false if codeBreaker
     * @pre the user has correctly chosen the game settings
     */
    private void playGame(Boolean codeMaker) {
        $keyCode = (codeMaker) ? $MasterIO.readAttemptInput() : $MasterStrategy.generateCode();
        int $attemptsUsed = 0;
        while ($attemptsUsed < $attemptsLimit) {
            Vector<Mastermind.COLOR> attempt;
            attempt = (codeMaker) ? $MasterStrategy.generateCode() : $MasterIO.readAttemptInput();
            $MasterIO.writeCurrentAttempt(attempt);
            Vector<Integer> pins = $CodeValidator.checkGivenCode(attempt);
            $MasterIO.writePinAmount(pins);
            $attemptsUsed++;
            if (checkEndGame(pins)) {
                $MasterIO.writeVictory("Codebreaker has won!", $attemptsUsed);
                return;
            }
        }
        $MasterIO.writeVictory("Codemaker has won. You ran out of attempts!", $attemptsUsed);
    }

    /**
     * Checks if the key has been found by checking the number of red pins
     * @param pins the vector containing the number of red and white pins
     * @return true if the key has been found, false if not
     */
    private boolean checkEndGame(Vector<Integer> pins) {
        return pins.get(0) == $codeLength;
    }
}
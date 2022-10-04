package Mastermind;

import java.util.Observable;
import java.util.Vector;

/**
 * Main model class of MasterMind
 *
 */
public class MasterMindModel extends Observable {

    public static int $codeLength = 4;
    public static int $colorAmount = 9;
    private int $attemptsLimit = 12;
    private boolean $codemaker;
    private static Vector<MasterMindModel.COLOR> $keyCode;
    public enum COLOR { RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE, PINK, WHITE, BLACK }
    private final MasterStrategy $MasterStrategy;
    private final MasterMindIO $MasterIO;
    private final CodeValidator $CodeValidator;

    public MasterMindModel() {
        // constructor
        $MasterStrategy = new RandomStrategy();
        $MasterIO = new MasterMindIO();
        $CodeValidator = new CodeValidator();
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
    public static Vector<MasterMindModel.COLOR> getKeyCode() {
        return $keyCode;
    }

    /**
     * Asks the game settings (number of colors, number of attempts, length of code)
     * and starts the game loop.
     * @post $colorAmount, $attemptsLimit, and $colorLength are set to desired values
     */
    public void start() {
        Vector<Integer> setupInput;
        setupInput = $MasterIO.readGameSetupInput();
        $colorAmount = setupInput.get(0);
        $attemptsLimit = setupInput.get(1);
        $codeLength = setupInput.get(2);
        if ($attemptsLimit <= 0 || $codeLength <= 0) {
            throw new IllegalArgumentException();
        }
        this.playGame(setupInput.get(3) == 0);
    }

    public void verifyInput(Vector<String> strings){
        Vector<MasterMindModel.COLOR> attempt = new Vector<>();
        int amountOfColorsGiven = 0;
        for (String color: strings) {
            if (color instanceof String) {
                for (int colorIndex = 0; colorIndex < MasterMindModel.$colorAmount; colorIndex++) {
                    if (color.equalsIgnoreCase(MasterMindModel.getColor(colorIndex).toString())) {
                        attempt.add(MasterMindModel.COLOR.valueOf(color.toUpperCase()));
                        break;
                    }
                }
            }
        }
        



    }
    /**
     * The game loop. If codeMaker, the user creates a key and the computer guesses randomly.
     * If codeBreaker, a random key will be generated and the user guesses.
     * The game keeps looping until the key is guessed or the number of attempts has been exceeded.
     * @param codeMaker boolean: true if codeMaker, false if codeBreaker
     * @pre the user has correctly chosen the game settings
     */
    private void playGame(Boolean codeMaker) {
        $codemaker=codeMaker;
        setChanged();
        notifyObservers($codeLength);
        if (!$codemaker) {
            $keyCode = $MasterStrategy.generateCode();
        }

        int $attemptsUsed = 0;
        while ($attemptsUsed < $attemptsLimit) {
            Vector<MasterMindModel.COLOR> attempt;
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

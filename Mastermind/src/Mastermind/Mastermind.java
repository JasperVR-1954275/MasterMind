package Mastermind;


import java.util.Vector;

/**
 * Group 2
 * This class presents the game logic.
 */
public class Mastermind {

    private static Mastermind $main;
    private static int $attemptsLimit = 12;
    public static int $codeLength = 4;
    public static int $colorAmount = 9;
    private Vector<Mastermind.COLOR> $keyCode;

    public static enum COLOR {RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE, PINK, WHITE, BLACK};

    private final MasterStrategy $MasterStrategy;
    private final MasterMindIO $MasterIO;

    /**
     * The main method -- the program starts here
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Mastermind.getInstance().start();
        } catch (Exception e) {
            //code om alle onverwachte exceptions op te vangen en elegant af te handelen
        }
    }

    /**
     * @param location
     * @return
     */
    public static COLOR getColor(int location) {
        return COLOR.values()[location];
    }

    /**
     *
     */
    private Mastermind() {
        // get IO for amount of codes, amount of colors,
        // IO to get strategy
        $MasterStrategy = new RandomStrategy();
        $MasterIO = new MasterMindIO();
        //$attemptsLimit = $MasterIO.getAttempts();
    }

    public static Mastermind getInstance() {
        if ($main == null)
            $main = new Mastermind();
        return $main;
    }

    public void start() {
        Vector<Integer> setupInput = new Vector<>();
        setupInput = $MasterIO.readGameSetupInput();
        $attemptsLimit = setupInput.get(0);
        $codeLength = setupInput.get(1);
        $colorAmount = setupInput.get(2);
        Mastermind.getInstance().playGame(setupInput.get(3) == 0);
    }

    /**
     *
     */
    public void playGame(Boolean codeMaker) {
        $keyCode = (codeMaker) ? $MasterIO.readAttemptInput()
                               : $MasterStrategy.generateCode();
        int $attemptsUsed = 0;
        while ($attemptsUsed < $attemptsLimit) {
            Vector<Mastermind.COLOR> attempt = null;
            attempt = (codeMaker) ? $MasterStrategy.generateCode()
                                  : $MasterIO.readAttemptInput();
            Vector<Integer> pins = checkGivenCode(attempt);
            $MasterIO.writePinsOutput(pins);
            boolean victory = checkEndGame(pins);
            if (victory) {
                $MasterIO.writeUserVictoryOutput();
                return;
            }
            $attemptsUsed++;
        }
        $MasterIO.writeComputerVictoryOutput();
    }

    /**
     * Check a row to see how it matches with the key row
     *
     * @param inputCode
     * @return the amount of red and white pins
     */
    public Vector<Integer> checkGivenCode(Vector<Mastermind.COLOR> inputCode) {
        Vector<Integer> keyPins = new Vector<>();
        Vector<Integer> redPos = calculateRedPins(inputCode);
        int redPins = redPos.size();
        int whitePins = calculateWhitePins(inputCode, redPos);
        keyPins.add(redPins);// red pins
        keyPins.add(whitePins);// white pins
        return keyPins;
    }
    /**
     * Check a row to see how it matches with the key row
     *
     * @param inputCode,  redPos
     * @return the amount of white pins
     */
    public Integer calculateWhitePins(Vector<Mastermind.COLOR> inputCode, Vector<Integer> redPos) {
        int whitePins = 0;
        for (int x = 0; x < inputCode.size(); x++) {
            if (!redPos.contains(x)) {
                for (int y = x; y < $keyCode.size(); y++) {
                    if (!redPos.contains(y)) {
                        if ($keyCode.get(y) == inputCode.get(x)) {
                            whitePins += 1;
                        }
                    }
                }

            }
        }
        return whitePins;
    }
    /**
     * Check a row to see how it matches with the key row
     *
     * @param inputCode
     * @return the positions of the pins in inputcode and keycode that have the same color
     */
    public Vector<Integer> calculateRedPins(Vector<Mastermind.COLOR> inputCode) {
        Vector<Integer> redPos = new Vector<>();
        for (int i = 0; i < $keyCode.size(); i++) {
            if ($keyCode.get(i) == inputCode.get(i)) {
                redPos.add(i);
            }
        }
        return redPos;
    }

    public boolean checkEndGame(Vector<Integer> pins) {
        return pins.get(0) == 4;
    }


}
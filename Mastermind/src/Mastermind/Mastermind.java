package Mastermind;


import java.util.Vector;

/**
 * Group 2
 * This class presents the game logic.
 */
public class Mastermind {

    private static Mastermind $main;
    private static int $attemptsLimit = 0;
    private static int $attemptsUsed = 0;
    public static int $codeLength = 4;
    public static int $colorAmount = 8;

    public static enum COLOR {RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE, PINK, WHITE, BLACK};

    private final MasterStrategy $MasterStrategy;
    //private RandomStrategy $RandomStrategy;
    private final MasterMindIO $MasterIO;

    /**
     * The main method -- the program starts here
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Mastermind.getInstance().start();
        } catch (Exception e) {
            //code om alle onverwachte exceptions op te vangen en elegant af te handelen
        }
    }

    public static COLOR getColor(int location) {
        return COLOR.values()[location];
    }

    private Mastermind() {
        // get IO for amount of codes, amount of colors,
        // IO to get strategy
        $MasterStrategy = new RandomStrategy();
        $MasterIO = new MasterMindIO();
        //$attemptsLimit = $MasterIO.getAttempts();
    }

    public void doMove(Vector<Mastermind.COLOR> row) throws InputException {

    }

    public static Mastermind getInstance() {
        if ($main == null)
            $main = new Mastermind();
        return $main;
    }

    public void start() {
        Vector<Integer> setupInput = new Vector<Integer>();
        setupInput = $MasterIO.readGameSetupInput();
        $attemptsLimit = setupInput.get(0);
        $codeLength = setupInput.get(1);
        $colorAmount = setupInput.get(2);
        if (setupInput.get(3) == 0) {
            Mastermind.getInstance().playCodeMaker();
        } else {
            Mastermind.getInstance().playCodeBreaker();
        }
    }

}
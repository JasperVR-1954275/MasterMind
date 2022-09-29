package Mastermind;

public class InputException extends Exception {

    public InputException() {
    }


    @Override
    public String toString() {
        return "Input exception, either too many inputs or wrong color";
    }
}

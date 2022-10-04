package Mastermind;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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



    public void createGUI() {
        JFrame frame = new JFrame("MasterMind");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        frame.getContentPane().add(mView.getUI());
        frame.getContentPane().add(mTools.getUI());

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
     * Initialized the Mastermind object.
     * It initializes a new RandomStrategy, a new MasterMindIO, and a new CodeValidator
     */
    private Mastermind() {
        // Create the data model
        mModel = new MasterMindModel();

        // Create the view
        mView = new MasterMindView(mModel, null);
        mModel.addObserver(mView);

        // Create the tools view
        mTools = new MasterMindTools(mModel, null);
        mModel.addObserver(mTools);
        mModel.start();
    }

    /**
     * Part of the Singelton pattern to have only one Mastermind object at all time and
     * that makes it universally accessible
     * @return the singleton Mastermind object
     */
//    public static Mastermind getInstance() {
//        if ($main == null)
//            $main = new Mastermind();
//        return $main;
//    }
}
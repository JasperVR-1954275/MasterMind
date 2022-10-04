package Mastermind;

import Abstracts.AbstractView;
import Abstracts.Controller;

import javax.swing.*;
import java.util.Observable;
import java.util.Vector;

public class MasterMindView extends AbstractView {

    private JTextField mView;
    public MasterMindView(Observable model, Controller controller) {
        super(model, controller);
        init();
    }

    private void init() {
        mView = new JTextField();
    }

    @Override
    public void update(Observable o, Object info) {
        //Vector<Vector<JTextField>>;
        /**
         * <<RED, RED>, <GREEN, RED>, <BlUE, RED>
         *
         *
         *
         */
    }

    public JComponent getUI() {
        return mView;
    }
}

package Mastermind;

import Abstracts.AbstractView;

import java.util.Vector;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class MasterMindTools extends AbstractView {
    private JPanel mTools;
    private Vector<JTextField> CodeInputFields;
    private JButton SubmitButton;

    private void init(int CodeLength) {
        mTools = new JPanel();
        mTools.setLayout(new GridLayout(1, 3));
        SubmitButton = new JButton("Start");
        SubmitButton.setEnabled(false);


        // handle events: pass to controller
        SubmitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ((ClockController)getController()).onStart();
            }
        });



        mTools.add(SubmitButton);
    }
}

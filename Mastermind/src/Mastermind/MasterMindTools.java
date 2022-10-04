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

    public MasterMindTools(Observable model, MasterMindController controller) {
        super(model, controller);
    }

    private void init(int codeLength) {
        mTools = new JPanel();
        mTools.setLayout(new GridLayout(1, 3));
        SubmitButton = new JButton("Submit code");
        //SubmitButton.setEnabled(false);
        CodeInputFields = new Vector<>();
        for (int i = 0; i < codeLength; i++){
            CodeInputFields.add(new JTextField());
        }

        // handle events: pass to controller
        SubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<String> allTexts = new Vector<>();
                for (JTextField item : CodeInputFields) {
                    allTexts.add(item.getText());
                }
                ((MasterMindController)getController()).OnSubmit(allTexts);
            }
        });


        for (int j = 0; j < CodeInputFields.size(); j++){
            mTools.add(CodeInputFields.get(j));
        }
        mTools.add(SubmitButton);
    }

    public JComponent getUI() {
        return mTools;
    }

    @Override
    public void update(Observable o, Object info) {
        // Cast info to ClockUpdate type.
        int codelength = (int) info;
        init(codelength);
    }
}

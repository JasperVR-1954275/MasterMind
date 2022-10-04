package Mastermind;

import Abstracts.AbstractView;
import Abstracts.Controller;

public class MasterMindView extends AbstractView {

    public MasterMindView(Observable model, Controller controller) {
        super(model, controller);
        init();
    }

    private void init() {
    }

    @Override
    public void update(Observable o, Object info) {
    }
}

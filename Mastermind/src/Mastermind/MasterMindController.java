package Mastermind;
import Abstracts.AbstractController;

import java.util.Observable;
import java.util.Vector;

public class MasterMindController extends AbstractController{
    public MasterMindController(Observable model){
        super(model);
    }

    public void OnSubmit(Vector<String> inputCode) {
        ((MasterMindModel)getModel()).verifyInput(inputCode);
    }
}

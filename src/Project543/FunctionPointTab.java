package Project543;

import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class FunctionPointTab extends Tab {
    //Member Variables
    //
    //Member Classes and Enums
    //

    //Static Variables
    //

    //Non-Static Variables
    FunctionPoint functionPoint;
    GridPane gridPane;
    TextField[] inputArray, outputArray;
    TextField totalCountOutput, functionPointOutput, valueAdjustmentOutput, codeSizeOutput, languageOutput;
    //TODO: Radio buttons

    //Member Methods
    //
    //Constructor(s)
    //
    public FunctionPointTab(){
        functionPoint = new FunctionPoint();
    }

    public FunctionPointTab(FunctionPoint functionPoint){

    }

    //Getters
    //

    //Setters
    //

    //Misc. Member Methods
    //
    //THE BASIS FOR GOING FORWARD
    public Button computeButton(){
        //Returns the button labelled "Compute FP"
        Button computeFunctionPoint = new Button("Compute FP");
        computeFunctionPoint.setOnAction(e -> computeFunctionPointClick());
        return computeFunctionPoint;
    }

    public void computeFunctionPointClick(){
        //does all the stuff Calculate FP button does
        //take inputsArray and move data into functionPoint,
        setOutputs();
    }

    public void setOutputs(){

    }
}

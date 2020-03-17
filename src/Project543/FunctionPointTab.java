package Project543;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;

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
    TextField[] IDVInputArray, IDVOutputArray;
    TextField totalCountOutput, functionPointOutput, valueAdjustmentOutput, codeSizeOutput, languageOutput;
    //TODO: Radio buttons

    //Member Methods
    //
    //Constructor(s)
    //
    public FunctionPointTab(String title){
        //Default Constructor
        super(title);
        functionPoint = new FunctionPoint();
        this.setGridPane(); //Set all?
    }

    public FunctionPointTab(String title, FunctionPoint functionPoint){
        //FunctionPoint constructor
        super(title);
        this.functionPoint = functionPoint;
        this.setGridPane(); //Set all?
    }

    public FunctionPointTab(String title, String saveDataString){
        super(title);
        functionPoint = new FunctionPoint(saveDataString);
        this.setGridPane(); //Set all?
    }

    //Getters
    //

    //Setters
    //
    public void setGridPane(){
        //Sets gridPane
        this.gridPane = new GridPane();
        this.gridPane.setAlignment(Pos.CENTER); //centers gridPane within tab
        this.gridPane.setHgap(10); //sets the horizontal gap between cells
        this.gridPane.setVgap(10);

        //TODO: Is this correct approach?
        //Adding the unchanging cells of gridPane
        //Weighting Factors Text
        Text weightingFactorsText = new Text("Weighting Factors");
        weightingFactorsText.setStyle("-fx-font: 24 arial;"); //makes text bigger
        this.gridPane.add(weightingFactorsText, 2, 0);

        //Simple Average Complex Labels (within HBox)
        HBox simpleAverageComplexLabelBox = new HBox(20, new Label("Simple"), new Label("Average"), new Label("Complex"));
        this.gridPane.add(simpleAverageComplexLabelBox, 2, 1);

        //IDV Labels
        for (int i = 2; i < 7; i++)
            gridPane.add(new Label(InformationDomainValue.InformationDomain.values()[i-2].toString()), 0, i);

        //Buttons
        gridPane.add(totalCountButton(), 0, 8);
        gridPane.add(computeFPButton(), 0, 10);
        gridPane.add(valueAdjustmentsButton(), 0, 11);
        gridPane.add(computeCodeSizeButton(), 0, 12);
        gridPane.add(changeLanguageButton(), 0, 13);

        this.setContent(gridPane);
    }

    public void setIDVInputArray(){
        //TODO
        //Sets IDVInputArray
    }

    public void setIDVOutputArray(){
        //TODO
        //Sets IDVOutputArray
    }

    public void setTotalCountOutput(){
        //TODO
        TextField totalCountOutput = new TextField();
        totalCountOutput.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        totalCountOutput.setEditable(false);
        //update text
//        totalCount.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
//        totalCount.setEditable(false);
//        totalCount.setText(Integer.toString(project.functionPointMetric.getTotalCount()));
    }

    public void setFunctionPointOutput(){
        //TODO
    }

    //Misc. Member Methods
    //
    //THE BASIS FOR GOING FORWARD
    public Button totalCountButton(){
        //Returns the button labelled "Total Count" with triggered actions as defined in totalCountClick()
        Button totalCountButton = new Button("Total Count");
        totalCountButton.setOnAction(e -> totalCountClick());
        return totalCountButton;
    }

    public void totalCountClick(){
        //TODO
    }

    public Button computeFPButton(){
        //Returns the button labelled "Compute FP" with triggered actions as defined in computeFPClick()
        Button computeFP = new Button("Compute FP");
        computeFP.setOnAction(e -> computeFPClick());
        return computeFP;
    }

    public void computeFPClick(){
        //TODO
        //does all the stuff Calculate FP button should do
        //take IDVInputsArray and move data into functionPoint,
        setIDVOutputArray();
        //sets totalCountOutput, functionPointOutput
    }

    public Button valueAdjustmentsButton(){
        //Returns the button labelled "Value Adjustments" with triggered actions as defined in valueAdjustmentsClick()
        Button valueAdjustments = new Button("Value Adjustments");
        valueAdjustments.setOnAction(e -> valueAdjustmentsClick());
        return valueAdjustments;
    }

    public void valueAdjustmentsClick(){
        //TODO
        //opens VAF window
        //gets user inputs
        //moves user inputs into functionPoint
        //sets valueAdjustmentOutput
    }

    public Button computeCodeSizeButton(){
        //Returns the button labelled "Compute Code Size" with triggered actions as defined in computeCodeSizeClick()
        Button computeCodeSize = new Button("Compute Code Size");
        computeCodeSize.setOnAction(e -> computeCodeSizeClick());
        return computeCodeSize;
    }

    public void computeCodeSizeClick(){
        //TODO
        //updates codeSizeOutput
    }

    public Button changeLanguageButton(){
        //Returns the button labelled "Change Language" with triggered actions as defined in changeLanguageClick()
        Button changeLanguage = new Button("Change Language");
        changeLanguage.setOnAction(e -> changeLanguageClick());
        return changeLanguage;
    }

    public void changeLanguageClick(){
        //TODO
        //changes language in functionPoint
        //updates languageOutput
    }
}

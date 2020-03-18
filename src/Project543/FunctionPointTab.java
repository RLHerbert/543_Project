package Project543;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;

import java.util.ArrayList;
import java.util.Optional;

public class FunctionPointTab extends MetricsTab {
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
    //TODO: Radio buttons; probably 2d array

    //Member Methods
    //
    //Constructor(s)
    //
    public FunctionPointTab(String title){
        //Default Constructor
        super(title);
        functionPoint = new FunctionPoint();
        initializeMembers();
        this.setGridPane(); //Set all?
    }

    public FunctionPointTab(String title, FunctionPoint functionPoint){
        //FunctionPoint constructor
        super(title);
        this.functionPoint = functionPoint;
        this.setGridPane(); //Set all? //TODO: remove; I don't think you need to repeat this in other constructors if you do super(this)
    }

    public FunctionPointTab(String title, String saveDataString){
        super(title);
        functionPoint = new FunctionPoint(saveDataString);
        this.setGridPane(); //Set all? //TODO: remove; I don't think you need to repeat this in other constructors if you do super(this)
    }

    //Initializer(s)
    private void initializeMembers(){
        //Initializes Member Variables
        initializeIDVInputArray();
        initializeIDVOutputArray();
        initializeTotalCountOutput();
        initializeFunctionPointOutput();
        initializeValueAdjustmentOutput();
        initializeCodeSizeOutput();
        initializeLanguageOutput();
    }

    private void initializeIDVInputArray(){
        this.IDVInputArray = new TextField[5];
        for (int i = 0; i < 5; i++) { //initializes elements of input array
            this.IDVInputArray[i] = new TextField();
            IDVInputArray[i].setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
            IDVInputArray[i].setMaxWidth(100); //TODO: make sizing based on constants/screen size
        }
    }
    private void initializeIDVOutputArray(){
        this.IDVOutputArray = new TextField[5];
        for (int i = 0; i < 5; i++){ //initializes elements of output array
            this.IDVOutputArray[i] = new TextField();
            this.IDVOutputArray[i].setTextFormatter(new TextFormatter<>(new NumberStringConverter())); //makes it numeric format
            this.IDVOutputArray[i].setEditable(false);
            this.IDVOutputArray[i].setStyle("-fx-control-inner-background: #D3D3D3"); //gray out box to show you can't type in it
            this.IDVOutputArray[i].setMaxWidth(100); //TODO: make sizing based on constants/screen size
        }
    }
    private void initializeTotalCountOutput(){
        this.totalCountOutput = new TextField();
        this.totalCountOutput.setTextFormatter(new TextFormatter<>(new NumberStringConverter())); //makes it numeric format
        this.totalCountOutput.setEditable(false);
        this.totalCountOutput.setStyle("-fx-control-inner-background: #D3D3D3"); //gray out box to show you can't type in it
        this.totalCountOutput.setMaxWidth(100); //TODO: make sizing based on constants/screen size
    }
    private void initializeFunctionPointOutput(){
        this.functionPointOutput = new TextField();
        this.functionPointOutput.setTextFormatter(new TextFormatter<>(new NumberStringConverter())); //makes it numeric format
        this.functionPointOutput.setEditable(false);
        this.functionPointOutput.setStyle("-fx-control-inner-background: #D3D3D3"); //gray out box to show you can't type in it
        this.functionPointOutput.setMaxWidth(150); //TODO: make sizing based on constants/screen size
    }
    private void initializeValueAdjustmentOutput(){
        this.valueAdjustmentOutput = new TextField();
        this.valueAdjustmentOutput.setTextFormatter(new TextFormatter<>(new NumberStringConverter())); //makes it numeric format
        this.valueAdjustmentOutput.setEditable(false);
        this.valueAdjustmentOutput.setStyle("-fx-control-inner-background: #D3D3D3"); //gray out box to show you can't type in it
        this.valueAdjustmentOutput.setMaxWidth(100); //TODO: make sizing based on constants/screen size
    }
    private void initializeCodeSizeOutput(){
        this.codeSizeOutput = new TextField();
        this.codeSizeOutput.setTextFormatter(new TextFormatter<>(new NumberStringConverter())); //makes it numeric format
        this.codeSizeOutput.setEditable(false);
        this.codeSizeOutput.setStyle("-fx-control-inner-background: #D3D3D3"); //gray out box to show you can't type in it
        this.codeSizeOutput.setMaxWidth(150); //TODO: make sizing based on constants/screen size
    }
    private void initializeLanguageOutput(){
        this.languageOutput = new TextField();
        this.languageOutput.setEditable(false);
        this.languageOutput.setStyle("-fx-control-inner-background: #D3D3D3"); //gray out box to show you can't type in it
        this.languageOutput.setMaxWidth(100); //TODO: make sizing based on constants/screen size
    }

    //Getters
    //
    public int getEIInput(){
        //Returns the number of external inputs entered by the user
        return Integer.parseInt(this.IDVInputArray[0].getText());
    }
    public int getEOInput(){
        //Returns the number of external outputs entered by the user
        return Integer.parseInt(this.IDVInputArray[1].getText());
    }
    public int getEQInput(){
        //Returns the number of external inquiries entered by the user
        return Integer.parseInt(this.IDVInputArray[2].getText());
    }
    public int getILFInput(){
        //Returns the number of internal logical files entered by the user
        return Integer.parseInt(this.IDVInputArray[3].getText());
    }
    public int getEIFInput(){
        //Returns the number of external interface files entered by the user
        return Integer.parseInt(this.IDVInputArray[4].getText());
    }

    //Setters
    //
    public void setGridPane(){
        //Sets gridPane
        this.gridPane = new GridPane();
        this.gridPane.setAlignment(Pos.CENTER); //centers gridPane within tab
        this.gridPane.setHgap(10); //sets the horizontal gap between cells
        this.gridPane.setVgap(10);

        //Adding the unchanging cells of gridPane
        //Weighting Factors Text
        Text weightingFactorsText = new Text("Weighting Factors");
        weightingFactorsText.setStyle("-fx-font: 24 arial;"); //makes text bigger
        this.gridPane.add(weightingFactorsText, 2, 0);

        //Simple Average Complex Labels (within HBox)
        HBox simpleAverageComplexLabelBox = new HBox(20, new Label("Simple"), new Label("Average"), new Label("Complex"));
        simpleAverageComplexLabelBox.setAlignment(Pos.CENTER);
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

        //Adding the "changing" cells of gridPane
        //User Input Cells
        //IDV Input Fields
        setIDVInputArray();
        for (int i = 0; i < 5; i++)
            gridPane.add(IDVInputArray[i], 1, i+2);

        //Complexity Radio Button Stuff
        //TODO: this^^

        //Non-User Input Cells
        //IDV Output Fields
        setIDVOutputArray();
        for (int i = 0; i < 5; i++) {
            gridPane.add(IDVOutputArray[i], 3, i + 2);
            gridPane.setHalignment(IDVOutputArray[i], HPos.RIGHT);
        }

        //Output Fields
        setTotalCountOutput();
        gridPane.add(totalCountOutput, 3, 8);
        gridPane.setHalignment(totalCountOutput, HPos.RIGHT);

        setFunctionPointOutput();
        gridPane.add(functionPointOutput, 3, 10);

        setValueAdjustmentOutput();
        gridPane.add(valueAdjustmentOutput, 3, 11);
        gridPane.setHalignment(valueAdjustmentOutput, HPos.RIGHT);

        setCodeSizeOutput();
        gridPane.add(codeSizeOutput, 3, 12);

        //Language Label and Output Field
        setLanguageOutput();
        HBox currentLanguageBox = new HBox(10);
        currentLanguageBox.getChildren().addAll(new Label("Current Language"), languageOutput);
        currentLanguageBox.setAlignment(Pos.CENTER);
        gridPane.add(currentLanguageBox, 2, 12);

        this.setContent(gridPane);
    }

    public void setIDVInputArray(){
        //Sets IDVInputArray from functionPoint data
        for (int i = 0; i < 5; i++){
            IDVInputArray[i].setText(Integer.toString(functionPoint.getNumberOfInputs()[i]));
        }
    }
    public void setIDVOutputArray(){
        //Sets IDVOutputArray from functionPoint data
        for (int i = 0; i < 5; i++) {
            IDVOutputArray[i].setText(Integer.toString(functionPoint.getSumsOfInputs()[i]));
        }
    }
    public void setTotalCountOutput(){
        //Sets totalCountOutput from functionPoint data
        totalCountOutput.setText(Integer.toString(functionPoint.getTotalCount()));
    }
    public void setFunctionPointOutput(){
        //Sets functionPointOutput from functionPoint data
        functionPointOutput.setText(Integer.toString(functionPoint.getFunctionPoints()));
    }
    public void setValueAdjustmentOutput(){
        //Sets valueAdjustmentOutput from functionPoint data
        valueAdjustmentOutput.setText(Integer.toString(functionPoint.getSumOfValAdjFac()));
    }
    public void setCodeSizeOutput(){
        //Sets codeSizeOutput from functionPoint data
        codeSizeOutput.setText(Integer.toString(functionPoint.getCodeSize()));
    }
    public void setLanguageOutput(){
        //Sets languageOutput from functionPoint data
        languageOutput.setText(functionPoint.getFunctionPointLanguage().toString());
    }

    public void setLanguage(){
        //opens language selection window, which returns selected language
        //then sets the language of the tab/metric/functionPoint object using the selected language
        functionPoint.setFunctionPointLanguage(Language.openLangSelectWindow());
    }

    public void setMetric(){
        this.metric = this.functionPoint;
    }

    //Misc. Member Methods
    //
    public void updateFunctionPointObj(){ //TODO: rename?
        functionPoint.setNumberOfExternalInputs(getEIInput());
        functionPoint.setNumberOfExternalOutputs(getEOInput());
        functionPoint.setNumberOfExternalInquiries(getEQInput());
        functionPoint.setNumberOfInternalLogicFiles(getILFInput());
        functionPoint.setNumberOfExternalInterfaceFiles(getEIFInput());

        //TODO: update complexities based on radio button selections
    }

    //THE BASIS FOR GOING FORWARD
    public Button totalCountButton(){
        //Returns the button labelled "Total Count" with triggered actions as defined in totalCountClick()
        Button totalCountButton = new Button("Total Count");
        totalCountButton.setOnAction(e -> totalCountClick());
        return totalCountButton;
    }

    public void totalCountClick(){
        //updates functionPoint with user-entered IDVs then updates output fields accordingly
        updateFunctionPointObj();
        setIDVOutputArray();
        setTotalCountOutput();
    }

    public Button computeFPButton(){
        //Returns the button labelled "Compute FP" with triggered actions as defined in computeFPClick()
        Button computeFP = new Button("Compute FP");
        computeFP.setOnAction(e -> computeFPClick());
        return computeFP;
    }

    public void computeFPClick(){
        updateFunctionPointObj();
        setIDVOutputArray();
        setTotalCountOutput();
        setFunctionPointOutput();
    }

    public Button valueAdjustmentsButton(){
        //Returns the button labelled "Value Adjustments" with triggered actions as defined in valueAdjustmentsClick()
        Button valueAdjustments = new Button("Value Adjustments");
        valueAdjustments.setOnAction(e -> valueAdjustmentsClick());
        return valueAdjustments;
    }

    public void valueAdjustmentsClick(){
        //opens VAF window, gets user inputs, moves user inputs into functionPoint
        this.functionPoint.setValAdjFacs(openVAFWindow()); //TODO: MAKE SURE VAF SUM UPDATES TOOOOOO???
        //sets valueAdjustmentOutput
        setValueAdjustmentOutput();
        setFunctionPointOutput();
        setCodeSizeOutput();
    }

    public Button computeCodeSizeButton(){
        //Returns the button labelled "Compute Code Size" with triggered actions as defined in computeCodeSizeClick()
        Button computeCodeSize = new Button("Compute Code Size");
        computeCodeSize.setOnAction(e -> computeCodeSizeClick());
        return computeCodeSize;
    }

    public void computeCodeSizeClick(){
        //updates codeSizeOutput
        if (this.languageOutput.getText().equals(Language.NONE.toString())) {
            setLanguage();
            setLanguageOutput();
        }

        updateFunctionPointObj();
        //update output fields too
        //TODO: output field updates are very similar to totalCountClick and computeFPClick...refactor somehow?
        setIDVOutputArray();
        setTotalCountOutput();
        setFunctionPointOutput();
        setCodeSizeOutput();
    }

    public Button changeLanguageButton(){
        //Returns the button labelled "Change Language" with triggered actions as defined in changeLanguageClick()
        Button changeLanguage = new Button("Change Language");
        changeLanguage.setOnAction(e -> changeLanguageClick());
        return changeLanguage;
    }

    public void changeLanguageClick(){
        //changes language in functionPoint
        setLanguage();
        //updates languageOutput
        setLanguageOutput();
    }

    public int[] openVAFWindow(){
        //TODO: refactor
        Dialog dialog = new Dialog();
        dialog.setTitle("Value Adjustments Factors");
        dialog.setHeaderText("Pick values from 0 to 5");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        for (int i = 0; i < ValueAdjustmentFactor.descriptionText.length; i++) {
            ChoiceBox vafChoices = new ChoiceBox();
            vafChoices.getItems().addAll(new String[] {"0", "1", "2", "3", "4", "5"});
            grid.add(new Label(ValueAdjustmentFactor.descriptionText[i]), 0, i);
            grid.add(vafChoices, 1, i);
            vafChoices.setValue(Integer.toString(functionPoint.getValAdjFac(i)));
        }

        dialog.getDialogPane().setContent(grid);
        dialog.showAndWait();

//        ArrayList<Integer> dialogInfoEntered = new ArrayList<Integer>(ValueAdjustmentFactor.NUM_VAF);
//        for (int i = 0; i < ValueAdjustmentFactor.NUM_VAF; i++)
//        {
//            dialogInfoEntered.add(Integer.parseInt((String) ((ChoiceBox) getNodeFromGridPane(grid, 1, i)).getValue()));
//        }
//        return dialogInfoEntered;

        int[] VAFValuesEntered = new int[ValueAdjustmentFactor.NUM_VAF];
        for (int i = 0; i < ValueAdjustmentFactor.NUM_VAF; i++) {
            VAFValuesEntered[i] = Integer.parseInt((String) ((ChoiceBox) getNodeFromGridPane(grid, 1, i)).getValue());
        }
        return VAFValuesEntered;


//        //Converts OK button result into ArrayList of VAF values (as integers)
//        dialog.setResultConverter(dialogButton -> {
//            if (dialogButton == ButtonType.OK) {
//                ArrayList<Integer> dialogInfoEntered = new ArrayList<Integer>(ValueAdjustmentFactor.NUM_VAF);
//                for (int i = 0; i < ValueAdjustmentFactor.NUM_VAF; i++)
//                {
//                    dialogInfoEntered.add(Integer.parseInt((String) ((ChoiceBox) getNodeFromGridPane(grid, 1, i)).getValue()));
//                }
//                return dialogInfoEntered;
//            }
//            return null;
//        });
//
//        //Updates project meta data
//        Optional<ArrayList<Integer>> result = dialog.showAndWait();
//        result.ifPresent(dialogInfoEntered ->
//                {
//                    for (int i = 0; i < ValueAdjustmentFactor.NUM_VAF; i++)
//                        return dialogInfoEntered;
//                        functionPoint.setValAdjFac(i, dialogInfoEntered.get(i));
//                }
//        );
    }

    public static Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
}

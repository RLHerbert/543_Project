package Project543;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;

public class FunctionPointTab extends MetricsTab {
    //Member Fields
    //
    //Member Enums and Classes
    //

    //Static Member Fields
    //
    //Constant Static Fields
    //
    public static final String TAB_TITLE = "Function Points";

    //Non-Constant Static Fields
    //

    //Non-Static Member Fields
    //
    //Constant Member Fields
    //

    //Non-Constant Member Fields
    //
    FunctionPoint functionPoint;
    GridPane gridPane;
    TextField[] inputsArray, outputsArray; //Input and output array for IDV Inputs respectively
    TextField totalCountOutput, functionPointOutput, valueAdjustmentOutput, codeSizeOutput, languageOutput;
    RadioButton[][] complexityRadios; //row# = IDV type, column# = complexity level (simple, avg, or complex, respectively)

    //Member Methods
    //
    //Constructor(s)
    //
    //TODO: Remove title
    public FunctionPointTab(String tabTitle){
        //Default constructor
        //Call super constructor
        super(TAB_TITLE);

        //Initialize member fields
        this.functionPoint = new FunctionPoint();
        initializeMembers();
        setTabTitle();
        this.setGridPane();
    }

    public FunctionPointTab(String tabTitle, FunctionPoint functionPoint){
        //FunctionPoint constructor
        //Call super constructor
        super(tabTitle);

        //Initialize member fields
        this.functionPoint = functionPoint;
        initializeMembers();
        setTabTitle();
        this.setGridPane();
    }

    public FunctionPointTab(String tabTitle, String saveDataString){
        //Save data constructor
        //call super constructor
        super(tabTitle);

        //Initialize member fields
        this.functionPoint = new FunctionPoint(saveDataString); //Initialize functionPoint from save data
        initializeMembers();
        setTabTitle();
        this.setGridPane();
    }

    //Getters
    //
    //IDV Inputs from user inputs getters
    //
    public int getExternalInputsFromInput(){
        //Returns the number of external inputs entered by the user
        return Integer.parseInt(this.inputsArray[0].getText().replaceAll("[,.]", ""));
    }

    public int getExternalOutputsFromInput(){
        //Returns the number of external outputs entered by the user
        return Integer.parseInt(this.inputsArray[1].getText().replaceAll("[,.]", ""));
    }

    public int getExternalInquiriesFromInput(){
        //Returns the number of external inquiries entered by the user
        return Integer.parseInt(this.inputsArray[2].getText().replaceAll("[,.]", ""));
    }

    public int getInternalLogicFilesFromInput(){
        //Returns the number of internal logical files entered by the user
        return Integer.parseInt(this.inputsArray[3].getText().replaceAll("[,.]", ""));
    }

    public int getExternalInterfaceFilesFromInput(){
        //Returns the number of external interface files entered by the user
        return Integer.parseInt(this.inputsArray[4].getText().replaceAll("[,.]", ""));
    }

    //IDV Complexity from user inputs getters
    //
    //TODO: possibly refactor to: public InformationDomainValue.Complexity getComplexityFromInputs(IDVComplexityToGet){}
    public InformationDomainValue.Complexity getExternalInputsComplexityFromInput(){
        for (int i = 0; i < 3; i++){
            if (complexityRadios[0][i].isSelected())
                return InformationDomainValue.Complexity.values()[i];
        }
        return InformationDomainValue.Complexity.AVERAGE; //default
    }

    public InformationDomainValue.Complexity getExternalOutputsComplexityFromInput(){
        for (int i = 0; i < 3; i++){
            if (complexityRadios[1][i].isSelected())
                return InformationDomainValue.Complexity.values()[i];
        }
        return InformationDomainValue.Complexity.AVERAGE; //default
    }

    public InformationDomainValue.Complexity getExternalInquiriesComplexityFromInput(){
        for (int i = 0; i < 3; i++){
            if (complexityRadios[2][i].isSelected())
                return InformationDomainValue.Complexity.values()[i];
        }
        return InformationDomainValue.Complexity.AVERAGE; //default
    }

    public InformationDomainValue.Complexity getInternalLogicFilesComplexityFromInput(){
        for (int i = 0; i < 3; i++){
            if (complexityRadios[3][i].isSelected())
                return InformationDomainValue.Complexity.values()[i];
        }
        return InformationDomainValue.Complexity.AVERAGE; //default
    }

    public InformationDomainValue.Complexity getExternalInterfaceFilesComplexityFromInput(){
        for (int i = 0; i < 3; i++){
            if (complexityRadios[4][i].isSelected())
                return InformationDomainValue.Complexity.values()[i];
        }
        return InformationDomainValue.Complexity.AVERAGE; //default
    }

    //Setters
    //
    //Member Field Setters
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
        weightingFactorsText.setStyle("-fx-font: 24 arial; -fx-alignment: center"); //makes text bigger
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
        setInputsArray();
        for (int i = 0; i < 5; i++)
            gridPane.add(inputsArray[i], 1, i+2);

        //Complexity Radio Button Stuff
        setComplexityRadios();
        for (int i = 0; i < 5; i++){
            HBox complexitiesBox = new HBox(40); //TODO: spacing based on constants/screen size?
            for (int j = 0; j < 3; j++){
                complexitiesBox.getChildren().add(this.complexityRadios[i][j]);
            }
            complexitiesBox.setAlignment(Pos.CENTER);
            gridPane.add(complexitiesBox, 2, i+2);
        }


        //Non-User Input Cells
        //IDV Output Fields
        setOutputsArray();
        for (int i = 0; i < 5; i++) {
            gridPane.add(outputsArray[i], 3, i + 2);
            gridPane.setHalignment(outputsArray[i], HPos.RIGHT);
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

    public void setInputsArray(){
        //Sets IDVInputArray from functionPoint data
        for (int i = 0; i < 5; i++){
            inputsArray[i].setText(Integer.toString(functionPoint.getNumberOfInputs()[i]));
        }
    }

    public void setOutputsArray(){
        //Sets IDVOutputArray from functionPoint data
        for (int i = 0; i < 5; i++) {
            outputsArray[i].setText(Integer.toString(functionPoint.getSumsOfInputs()[i]));
        }
    }

    public void setTotalCountOutput(){
        //Sets totalCountOutput from functionPoint data
        totalCountOutput.setText(Integer.toString(functionPoint.getTotalCount()));
    }

    public void setFunctionPointOutput(){
        //Sets functionPointOutput from functionPoint data
        functionPointOutput.setText(Integer.toString(functionPoint.getTotalFunctionPoints()));
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

    public void setComplexityRadios(){
        for (int i = 0; i < 5; i++){
            complexityRadios[i][functionPoint.getComplexityOfInputs()[i].ordinal()].setSelected(true);
        }
    }

    //Related Variable Setters
    //
    public void setFunctionPointLanguage(){
        //opens language selection window, which returns selected language
        //then sets the language of the tab/metric/functionPoint object using the selected language
        functionPoint.setFunctionPointLanguage(Language.openLanguageSelectWindow());
    }

    //Setters from Parent Class (Defining Virtual Methods)
    //
    public void setMetric(){
        this.metric = this.functionPoint;
    }

    public void setTabTitle(){
        if (functionPoint.getFunctionPointLanguage() != Language.NONE) {
            setLanguageOutput();
            this.setText(TAB_TITLE + " - " + functionPoint.getFunctionPointLanguage());
        }
        else {
            this.setText(TAB_TITLE);
        }
    }

    //Misc. Member Methods
    //
    //Initializer Method(s)
    //
    private void initializeMembers(){
        //Initializes Member Variables
        initializeInputsArray();
        initializeOutputsArray();
        initializeTotalCountOutput();
        initializeFunctionPointOutput();
        initializeValueAdjustmentOutput();
        initializeCodeSizeOutput();
        initializeLanguageOutput();
        initializeComplexities();
    }

    private void initializeInputsArray(){
        this.inputsArray = new TextField[5];
        for (int i = 0; i < 5; i++) { //initializes elements of input array
            this.inputsArray[i] = new TextField();
            inputsArray[i].setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
            inputsArray[i].setMaxWidth(100); //TODO: make sizing based on constants/screen size
        }
    }

    private void initializeOutputsArray(){
        this.outputsArray = new TextField[5];
        for (int i = 0; i < 5; i++){ //initializes elements of output array
            this.outputsArray[i] = new TextField();
            this.outputsArray[i].setTextFormatter(new TextFormatter<>(new NumberStringConverter())); //makes it numeric format
            this.outputsArray[i].setEditable(false);
            this.outputsArray[i].setStyle("-fx-control-inner-background: #D3D3D3"); //gray out box to show you can't type in it
            this.outputsArray[i].setMaxWidth(100); //TODO: make sizing based on constants/screen size
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

    private void initializeComplexities(){
        //Initializes each radio button, (initializes and) sets a toggle group for each radio button so that only one per group can be selected
        this.complexityRadios = new RadioButton[5][3];
        for (int i = 0; i < 5; i++) {
            ToggleGroup IDVComplexities = new ToggleGroup();
            for (int j = 0; j < 3; j++) {
                this.complexityRadios[i][j] = new RadioButton(Integer.toString(InformationDomainValue.weightFactors[i][j]));
                this.complexityRadios[i][j].setToggleGroup(IDVComplexities);
            }
        }
    }

    //Update Methods
    //
    public void updateFunctionPoint(){
        functionPoint.setNumberOfExternalInputs(getExternalInputsFromInput());
        functionPoint.setNumberOfExternalOutputs(getExternalOutputsFromInput());
        functionPoint.setNumberOfExternalInquiries(getExternalInquiriesFromInput());
        functionPoint.setNumberOfInternalLogicFiles(getInternalLogicFilesFromInput());
        functionPoint.setNumberOfExternalInterfaceFiles(getExternalInterfaceFilesFromInput());

        functionPoint.setComplexityOfExternalInputs(getExternalInputsComplexityFromInput());
        functionPoint.setComplexityOfExternalOutputs(getExternalOutputsComplexityFromInput());
        functionPoint.setComplexityOfExternalInquiries(getExternalInquiriesComplexityFromInput());
        functionPoint.setComplexityOfInternalLogicFiles(getInternalLogicFilesComplexityFromInput());
        functionPoint.setComplexityOfExternalInterfaceFiles(getExternalInterfaceFilesComplexityFromInput());
    }

    //Button and corresponding onclick methods
    public Button totalCountButton(){
        //Returns the button labelled "Total Count" with triggered actions as defined in totalCountClick()
        Button totalCountButton = new Button("Total Count");
        totalCountButton.setOnAction(e -> totalCountClick());
        return totalCountButton;
    }

    public void totalCountClick(){
        //updates functionPoint with user-entered IDVs then updates output fields accordingly
        updateFunctionPoint();
        setOutputsArray();
        setTotalCountOutput();
    }

    public Button computeFPButton(){
        //Returns the button labelled "Compute FP" with triggered actions as defined in computeFPClick()
        Button computeFP = new Button("Compute FP");
        computeFP.setOnAction(e -> computeFPClick());
        return computeFP;
    }
    public void computeFPClick(){
        updateFunctionPoint();
        setOutputsArray();
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
        this.functionPoint.setValueAdjustmentFactorsFromArray(openValueAdjustmentsWindow()); //TODO: update IDV outputs and total count
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
            changeLanguage();
        }

        updateFunctionPoint();
        //update output fields too
        //TODO: output field updates are very similar to totalCountClick and computeFPClick...refactor somehow?
        setOutputsArray();
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
        //Only one line in here...do I need this fxn?
        changeLanguage();
    }

    //Helper Methods
    public void changeLanguage(){
        //changes language in functionPoint
        setFunctionPointLanguage();
        //updates languageOutput
        setLanguageOutput();
        //updates tab title
        setTabTitle();
    }

    public int[] openValueAdjustmentsWindow(){
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
            vafChoices.setValue(Integer.toString(functionPoint.getValueAdjustmentFactor(i)));
        }

        dialog.getDialogPane().setContent(grid);
        dialog.showAndWait();

        int[] VAFValuesEntered = new int[ValueAdjustmentFactor.NUM_VAF];
        for (int i = 0; i < ValueAdjustmentFactor.NUM_VAF; i++) {
            VAFValuesEntered[i] = Integer.parseInt((String) ((ChoiceBox) getNodeFromGridPane(grid, 1, i)).getValue());
        }

        return VAFValuesEntered;
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

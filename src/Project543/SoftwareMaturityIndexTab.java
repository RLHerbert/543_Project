package Project543;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;


public class SoftwareMaturityIndexTab extends MetricsTab implements SaveInterface {
    //Member Fields
    //
    //Member Enums and Classes
    //

    //Static Member Fields
    //
    //Constant Static Fields
    //
    public static final String TAB_TITLE = "SMI";

    //Non-Constant Static Fields
    //

    //Non-Static Member Fields
    //
    //Constant Member Fields
    //

    //Non-Constant Member Fields
    //
    SoftwareMaturityIndex softwareMaturityIndex;
    TableView<SoftwareMaturityIndex.MetricValuesRow> table;
    TableColumn<SoftwareMaturityIndex.MetricValuesRow, Integer> modulesAddedColumn;
    TableColumn<SoftwareMaturityIndex.MetricValuesRow, Integer> modulesChangedColumn;
    TableColumn<SoftwareMaturityIndex.MetricValuesRow, Integer> modulesDeletedColumn;


    //Member Methods
    //
    //Constructor(s)
    //
    SoftwareMaturityIndexTab(String tabTitle){
        super(TAB_TITLE);
        this.softwareMaturityIndex = new SoftwareMaturityIndex();
        startTab();
    }

    SoftwareMaturityIndexTab(String tabTitle, SoftwareMaturityIndex softwareMaturityIndex){
        this(tabTitle);
        this.softwareMaturityIndex = softwareMaturityIndex;
    }

    SoftwareMaturityIndexTab(String tabTitle, String saveData){
        this(tabTitle);
        this.softwareMaturityIndex = new SoftwareMaturityIndex(saveData);
    }

    //Initializers
    //
    public void startTab(){
        initializeTable(); //initializes and sets up table and columns
        setTabLayout(); //makes layout pretty and adds the buttons
        setTable(); //updates row values based on softwareMaturityIndex data
        changeModulesAdded();
        changeModulesChanged();
        changeModulesDeleted();
    }

    public void initializeTable(){
        this.table = new TableView<SoftwareMaturityIndex.MetricValuesRow>();

        //Create columns
        //Uneditable SMI column
        TableColumn<SoftwareMaturityIndex.MetricValuesRow, Double> SMIColumn = new TableColumn<>("SMI");
        SMIColumn.setCellValueFactory(new PropertyValueFactory<>("SMI"));

        //Editable Module columns (x3)
        this.modulesAddedColumn = new TableColumn<>("Modules Added");
        this.modulesAddedColumn.setCellValueFactory(new PropertyValueFactory<>("modulesAdded"));

        this.modulesChangedColumn = new TableColumn<>("Modules Changed");
        this.modulesChangedColumn.setCellValueFactory(new PropertyValueFactory<>("modulesChanged"));

        this.modulesDeletedColumn = new TableColumn<>("Modules Deleted");
        this.modulesDeletedColumn.setCellValueFactory(new PropertyValueFactory<>("modulesDeleted"));

        //Uneditable Total Modules column
        TableColumn<SoftwareMaturityIndex.MetricValuesRow, Integer> totalModulesColumn = new TableColumn<>("Total Modules");
        totalModulesColumn.setCellValueFactory(new PropertyValueFactory<>("totalModules"));

        //Add columns to table and do some basic table setup
        this.table.getColumns().addAll(SMIColumn, this.modulesAddedColumn, this.modulesChangedColumn, this.modulesDeletedColumn, totalModulesColumn);
        this.table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); //sets all columns to same width
        this.table.setPlaceholder(new Label("No rows to display"));
    }


    //Getters
    //
    public int getModulesAddedFromInput(){
        //TODO
        return 0;
    }

    public int getModulesChangedFromInput(){
        //TODO
        return 0;
    }

    public int getModulesDeletedFromInput(){
        //TODO
        return 0;
    }

    public ObservableList<SoftwareMaturityIndex.MetricValuesRow> getAllRows() { //TODO: note, from data NOT from input
        ObservableList<SoftwareMaturityIndex.MetricValuesRow> allRows = FXCollections.observableArrayList();
        allRows.addAll(softwareMaturityIndex.getAllRows());
        return allRows;
    }

    //Setters
    //
    public void setTable(){ //TODO: make sure this is called every time a row/cell is added or edited
        table.setItems(this.getAllRows());
//        //TODO: make last row editable
    }

    //Setters from Parent Class (Defining Virtual Methods)
    //
    public void setMetric(){
        this.metric = this.softwareMaturityIndex;
    }

    public void setTabTitle(){ //TODO: do we need for this metric?
        this.setText(TAB_TITLE);
    } //TODO: use

    public void setTabLayout(){
        //Buttons
        HBox buttonsBox = new HBox(50, addRowButton(), computeIndexButton());
        buttonsBox.setAlignment(Pos.CENTER);

        //Everything Together
        VBox tabLayout = new VBox(50, this.table, buttonsBox);
        tabLayout.setAlignment(Pos.CENTER);

        this.setContent(tabLayout);
    }

    //Misc. Member Methods
    //
    public void changeModulesAdded(){
        //TODO: describe
        table.setEditable(true);
        modulesAddedColumn.setCellFactory(TextFieldTableCell.forTableColumn(stringToIntConverter()));
        modulesAddedColumn.setOnEditCommit(this::changeModulesAddedCellEvent);
    }

    public void changeModulesAddedCellEvent(TableColumn.CellEditEvent editedCell){
        //TODO: describe
        SoftwareMaturityIndex.MetricValuesRow metricRowSelected = table.getSelectionModel().getSelectedItem();
        metricRowSelected.setModulesAdded(Integer.parseInt(editedCell.getNewValue().toString()));
    }

    public void changeModulesChanged(){
        //TODO: describe
        table.setEditable(true);
        modulesChangedColumn.setCellFactory(TextFieldTableCell.forTableColumn(stringToIntConverter()));
        modulesChangedColumn.setOnEditCommit(this::changeModulesChangedCellEvent);
    }

    public void changeModulesChangedCellEvent(TableColumn.CellEditEvent editedCell){
        //TODO: describe
        SoftwareMaturityIndex.MetricValuesRow metricRowSelected = table.getSelectionModel().getSelectedItem();
        metricRowSelected.setModulesChanged(Integer.parseInt(editedCell.getNewValue().toString()));
    }

    public void changeModulesDeleted(){
        //TODO: describe
        table.setEditable(true);
        modulesDeletedColumn.setCellFactory(TextFieldTableCell.forTableColumn(stringToIntConverter()));
        modulesDeletedColumn.setOnEditCommit(this::changeModulesDeletedCellEvent);
    }

    public void changeModulesDeletedCellEvent(TableColumn.CellEditEvent editedCell){
        //TODO: describe
        SoftwareMaturityIndex.MetricValuesRow metricRowSelected = table.getSelectionModel().getSelectedItem();
        metricRowSelected.setModulesDeleted(Integer.parseInt(editedCell.getNewValue().toString()));
    }

    //Buttons
    public Button addRowButton() {
        //Returns the button labelled "Add Row" with triggered actions as defined in addRowClick()
        Button addRow = new Button("Add Row");
        addRow.setOnAction(e -> addRowClick());
        return addRow;
    }

    public void addRowClick(){
        //TODO: describe
        this.softwareMaturityIndex.addRow();
//        table.edit(softwareMaturityIndex.getAllRows().size()-1, modulesAddedColumn);
//        table.edit(softwareMaturityIndex.getAllRows().size()-1, modulesChangedColumn);
//        table.edit(softwareMaturityIndex.getAllRows().size()-1, modulesDeletedColumn);
        setTable();
    }

    public Button computeIndexButton() {
        //Returns the button labelled "Compute Index" with triggered actions as defined in computeIndexClick()
        Button computeIndex = new Button("Compute Index");
        computeIndex.setOnAction(e -> computeIndexClick());
        return computeIndex;
    }

    public void computeIndexClick(){
        //TODO: describe
        this.softwareMaturityIndex.setMetrics(softwareMaturityIndex.getLatestRow());
    }

    //Helper Methods
    public StringConverter<Integer> stringToIntConverter(){
        //Returns a StringConverter object that converts between strings and integers
        //Needed for TextFieldTableCell.forTableColumn(stringToIntConverter()) in making-cells-editable functions
        StringConverter<Integer> stringToIntConverter = new StringConverter<Integer>() {
            @Override
            public String toString(Integer integer) {
                return integer.toString();
            }

            @Override
            public Integer fromString(String s) {
                return Integer.parseInt(s);
            }
        };
        return stringToIntConverter;
    }

    public boolean hasChanged(){
        return this.softwareMaturityIndex.hasChanged();
    }
}

package Project543;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.security.Key;

//TODO: make empty rows white or clear
//TODO: first addRow() call should make only modules added field editable; textfield should appear immediately after addRow() is called
//TODO: Total Modules should update after any module cell is changed (in that row)
//TODO: only last row should be editable, even after opening from save data
//TODO: doesn't update previous smi/tot modules if addRow() is called before computeIndex()
//TODO: edits should be committed after focus change or w/e (like if the user clicks out of the text field) as well as pressing enter
//TODO: SMI should change only when compute index button is pressed
//TODO: Sorting should be disabled maybe?

//TODO: tell Rowan that total modules adds the amount added every time setMetrics is called (it should only add it once, and subtract the previous value
// if that value is changed to something else, which should also only be added once)
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
        this(TAB_TITLE);
        this.softwareMaturityIndex = softwareMaturityIndex;
        startTab();
    }

    SoftwareMaturityIndexTab(String tabTitle, String saveData){
        this(TAB_TITLE);
        this.softwareMaturityIndex = new SoftwareMaturityIndex(saveData);
        startTab();
    }

    //Initializers
    //
    public void startTab(){
        initializeTable(); //initializes and sets up table and columns
        setTabLayout(); //makes layout pretty and adds the buttons
        setTableFromData(); //updates row values based on softwareMaturityIndex data
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
//        this.modulesAddedColumn.setCellFactory(column -> EditCell.createStringEditCell());

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
    public int getModulesAddedFromLastRow(){
        //TODO: use?
        System.out.println("Modules Added in last row: " + table.getItems().get(table.getItems().size()-1).modulesAdded);
        return table.getItems().get(table.getItems().size()-1).modulesAdded;
    }

    public int getModulesChangedFromLastRow(){
        //TODO: use?
        System.out.println("Modules Changed in last row: " + table.getItems().get(table.getItems().size()-1).modulesChanged);
        return table.getItems().get(table.getItems().size()-1).modulesChanged;
    }

    public int getModulesDeletedFromLastRow(){
        //TODO: use?
        System.out.println("Modules Deleted in last row: " + table.getItems().get(table.getItems().size()-1).modulesAdded);
        return table.getItems().get(table.getItems().size()-1).modulesDeleted;
    }

    public SoftwareMaturityIndex.MetricValuesRow getLastRow(){ //TODO: note - from input not data
        return table.getItems().get(table.getItems().size()-1);
    }

    public SoftwareMaturityIndex.MetricValuesRow getLastRowFromData() {
        return softwareMaturityIndex.getAllRows().get(softwareMaturityIndex.softwareMaturityIndexRows.size()-1);
    }

    public ObservableList<SoftwareMaturityIndex.MetricValuesRow> getAllRows(){
        return table.getItems();
    }

    public ObservableList<SoftwareMaturityIndex.MetricValuesRow> getAllRowsFromData() {
        ObservableList<SoftwareMaturityIndex.MetricValuesRow> allRows = FXCollections.observableArrayList();
        allRows.addAll(softwareMaturityIndex.getAllRows());
        return allRows;
    }

    //Setters
    //
    public void setTableFromData(){ //TODO: make sure this is called every time a row/cell is added or edited?
        table.setItems(this.getAllRowsFromData());
    }

    //Setters from Parent Class (Defining Virtual Methods)
    //
    public void setMetric(){
        this.metric = this.softwareMaturityIndex;
    }

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
    public void updateLastRowInData(){ //TODO: make sure this is called every time a cell is added or edited
        table.getItems().set(getAllRows().size()-1, getLastRow());
    }

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
        setTableFromData();
        if (getAllRows().size() == 1) {
            changeModulesAdded();
//            table.edit(getAllRows().size()-1, modulesAddedColumn);
            //TODO: commit edit?

        } else {
            changeModulesAdded();
            changeModulesDeleted();
            changeModulesChanged();
//            table.edit(getAllRows().size()-1, modulesAddedColumn);
//            table.edit(getAllRows().size()-1, modulesChangedColumn);
//            table.edit(getAllRows().size()-1, modulesDeletedColumn);
        }
        setTableFromData();
    }

    public Button computeIndexButton() {
        //Returns the button labelled "Compute Index" with triggered actions as defined in computeIndexClick()
        Button computeIndex = new Button("Compute Index");
        computeIndex.setOnAction(e -> computeIndexClick());
        return computeIndex;
    }

    public void computeIndexClick(){
        //TODO: describe
        getModulesAddedFromLastRow();
        //TODO: update backend modules stuff first
        this.softwareMaturityIndex.setModulesAdded(getLastRowFromData(), this.getLastRow().getModulesAdded());
        this.softwareMaturityIndex.setModulesChanged(getLastRowFromData(), this.getLastRow().getModulesChanged());
        this.softwareMaturityIndex.setModulesDeleted(getLastRowFromData(), this.getLastRow().getModulesDeleted());
        this.softwareMaturityIndex.setMetrics(this.getLastRowFromData());
        System.out.println("New Total Modules in last row (of data object): " + getLastRowFromData().totalModules);
        System.out.println("New Total Modules in last row (of data object): " + getAllRowsFromData().get(getAllRowsFromData().size() - 1).totalModules);

        startTab();
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

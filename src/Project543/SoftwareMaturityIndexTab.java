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

//TODO
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

    //Member Methods
    //
    //Constructor(s)
    //
    SoftwareMaturityIndexTab(String tabTitle){
        super(TAB_TITLE);
        this.softwareMaturityIndex = new SoftwareMaturityIndex();
        initializeTable();
        setTable();
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
    public void initializeTable(){
        this.table = new TableView<SoftwareMaturityIndex.MetricValuesRow>();

        TableColumn<SoftwareMaturityIndex.MetricValuesRow, Double> SMIColumn = new TableColumn<>("SMI");
        SMIColumn.setCellValueFactory(new PropertyValueFactory<>("SMI"));

        TableColumn<SoftwareMaturityIndex.MetricValuesRow, Integer> modulesAddedColumn = new TableColumn<>("Modules Added");
        modulesAddedColumn.setCellValueFactory(new PropertyValueFactory<>("modulesAdded"));

        TableColumn<SoftwareMaturityIndex.MetricValuesRow, Integer> modulesChangedColumn = new TableColumn<>("Modules Changed");
        modulesChangedColumn.setCellValueFactory(new PropertyValueFactory<>("modulesChanged"));

        TableColumn<SoftwareMaturityIndex.MetricValuesRow, Integer> modulesDeletedColumn = new TableColumn<>("Modules Deleted");
        modulesDeletedColumn.setCellValueFactory(new PropertyValueFactory<>("modulesDeleted"));

        TableColumn<SoftwareMaturityIndex.MetricValuesRow, Integer> totalModulesColumn = new TableColumn<>("Total Modules");
        totalModulesColumn.setCellValueFactory(new PropertyValueFactory<>("totalModules"));


        table.getColumns().addAll(SMIColumn, modulesAddedColumn, modulesChangedColumn, modulesDeletedColumn, totalModulesColumn);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); //sets all columns to same width
        table.setPlaceholder(new Label("No rows to display"));

        HBox buttonsBox = new HBox(20, addRowButton(), computeIndexButton());
        buttonsBox.setAlignment(Pos.CENTER);
        VBox tabLayout = new VBox(table, buttonsBox); //TODO: add buttons to layout??
        tabLayout.setAlignment(Pos.CENTER);
        this.setContent(tabLayout);
    }


    //Getters
    //
    public int getModulesAddedFromInput(){
        return 0;
    }

    public int getModulesChangedFromInput(){
        return 0;
    }

    public int getModulesDeletedFromInput(){
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

    //Misc. Member Methods
    //
    public Button addRowButton() {
        //Returns the button labelled "Add Row" with triggered actions as defined in addRowClick()
        Button addRow = new Button("Add Row");
        addRow.setOnAction(e -> addRowClick());
        return addRow;
    }

    public void addRowClick(){
        //opens VAF window, gets user inputs, moves user inputs into functionPoint
        this.softwareMaturityIndex.addRow();
        setTable();
    }

    public Button computeIndexButton() {
        //Returns the button labelled "Compute Index" with triggered actions as defined in computeIndexClick()
        Button computeIndex = new Button("Compute Index");
        computeIndex.setOnAction(e -> computeIndexClick());
        return computeIndex;
    }

    public void computeIndexClick(){
        //opens VAF window, gets user inputs, moves user inputs into functionPoint
        this.softwareMaturityIndex.setMetrics(softwareMaturityIndex.getLatestRow());
    }

    public boolean hasChanged(){
        return this.softwareMaturityIndex.hasChanged();
    }
}

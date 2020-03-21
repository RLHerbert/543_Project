package Project543;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

//TODO
public class SoftwareMaturityIndexTab extends MetricsTab {
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
    TableView table;

    //Member Methods
    //
    //Constructor(s)
    //
    SoftwareMaturityIndexTab(String tabTitle){
        super(TAB_TITLE);
        this.softwareMaturityIndex = new SoftwareMaturityIndex();
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

    //Setters
    //
    public void setTable(){
        this.table = new TableView();

        TableColumn<Double, SoftwareMaturityIndex> SMI = new TableColumn<Double, SoftwareMaturityIndex>("SMI");
        TableColumn<Integer, SoftwareMaturityIndex> modulesAdded = new TableColumn<Integer, SoftwareMaturityIndex>("Modules Added");
        TableColumn<Integer, SoftwareMaturityIndex> modulesChanged = new TableColumn<Integer, SoftwareMaturityIndex>("Modules Changed");
        TableColumn<Integer, SoftwareMaturityIndex> modulesDeleted = new TableColumn<Integer, SoftwareMaturityIndex>("Modules Deleted");
        TableColumn<Integer, SoftwareMaturityIndex> totalModules = new TableColumn<Integer, SoftwareMaturityIndex>("Total Modules");

        SMI.setCellValueFactory(new PropertyValueFactory<Double, SoftwareMaturityIndex>("SMI"));
        modulesAdded.setCellValueFactory(new PropertyValueFactory<Integer, SoftwareMaturityIndex>("modulesAdded"));
        modulesChanged.setCellValueFactory(new PropertyValueFactory<Integer, SoftwareMaturityIndex>("modulesChanged"));
        modulesDeleted.setCellValueFactory(new PropertyValueFactory<Integer, SoftwareMaturityIndex>("modulesDeleted"));
        totalModules.setCellValueFactory(new PropertyValueFactory<Integer, SoftwareMaturityIndex>("totalModules"));

        table.getColumns().addAll(SMI, modulesAdded, modulesChanged, modulesDeleted, totalModules);
        table.setPlaceholder(new Label("No rows to display"));
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        softwareMaturityIndex.addRow();
        table.getItems().add(softwareMaturityIndex.softwareMaturityIndexRows.get(0));

        table.setEditable(true);
        Callback<TableColumn, TableCell> cellFactory =
                new Callback<TableColumn, TableCell>() {
                    public TableCell call(TableColumn p) {
                        return new EditingCell();
                    }
                };

        VBox tableBox = new VBox(table);
        this.setContent(table);
    }

    

    //Setters from Parent Class (Defining Virtual Methods)
    //
    public void setMetric(){
        this.metric = this.softwareMaturityIndex;
    }

    public void setTabTitle(){ //TODO: do we need for this metric?
        this.setText(TAB_TITLE);
    }

    //Misc. Member Methods
    //
}

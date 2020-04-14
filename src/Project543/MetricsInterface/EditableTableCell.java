package Project543.MetricsInterface;

import javafx.event.Event;
import javafx.scene.control.*;
import javafx.util.StringConverter;

/* Heavily Referenced: https://gist.github.com/james-d/be5bbd6255a4640a5357 */

public class EditableTableCell<S, T> extends TableCell<S, T> {
    //**MEMBER FIELDS**//
    //
    //MEMBER ENUMS AND CLASSES
    //

    //STATIC MEMBER FIELDS
    //
    //Constant Static Fields
    //
    private final TextField textField;
    private final StringConverter<T> stringConverter;

    //Non-Constant Static Fields
    //

    //NON-STATIC MEMBER FIELDS
    //
    //Constant Member Fields
    //

    //Non-Constant Member Fields
    //

    //**MEMBER METHODS**//
    //
    //CONSTRUCTOR(S)
    //
    EditableTableCell(StringConverter<T> stringConverter) {
        this.textField = new TextField();
        this.stringConverter = stringConverter;

        itemProperty().addListener((obj, oldVal, newVal) -> {
            if (newVal == null)
                setText(null);
            else
                setText(this.stringConverter.toString(newVal));
        });

        setGraphic(this.textField);
        setContentDisplay(ContentDisplay.TEXT_ONLY);

        this.textField.setOnAction(e -> {
            commitEdit(this.stringConverter.fromString(this.textField.getText()));
        });

        this.textField.focusedProperty().addListener((obj, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                commitEdit(this.stringConverter.fromString(this.textField.getText()));
            }
        });
    }

    //GETTERS
    //

    //SETTERS
    //

    //MISC. MEMBER METHODS
    //
    @Override
    public void startEdit() {
        super.startEdit();
        this.textField.setText(this.stringConverter.toString(getItem()));
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        this.textField.requestFocus();

        //Hopefully cancels editable-ness of previous cells
        TablePosition<S, T> cellPos = this.getTableView().getFocusModel().getFocusedCell();
        if (cellPos.getRow() < this.getTableView().getItems().size()-1 || (cellPos.getRow() == 0 && cellPos.getColumn() > 1))
            cancelEdit();
    }


    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }


    @Override
    public void commitEdit(T item) {
        if (!isEditing() && !item.equals(getItem())) {
            TableView<S> table = getTableView();
            if (table != null) {
                TableColumn<S, T> column = getTableColumn();
                TableColumn.CellEditEvent<S, T> event = new TableColumn.CellEditEvent<>(table,
                        new TablePosition<S,T>(table, getIndex(), column),
                        TableColumn.editCommitEvent(), item);
                Event.fireEvent(column, event);
            }
        }

        super.commitEdit(item);
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }
}

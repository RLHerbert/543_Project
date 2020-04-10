package Project543.Interface;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class Dialogs {
    public static String createFunctionPointDialog(){
        String tabName = "Function Point";

        TextInputDialog functionPointDialog = new TextInputDialog(tabName);
        functionPointDialog.setTitle("New Function Point");
        functionPointDialog.setHeaderText(null);
        functionPointDialog.setContentText("Enter a name for the function point:");

        Optional<String> result = functionPointDialog.showAndWait();

        return result.orElse(null);
    }
}

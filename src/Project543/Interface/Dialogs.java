package Project543.Interface;

import Project543.UserInterface_3;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class Dialogs {
    public static String createFunctionPointDialog(){
        String tabName = "Function Point";

        TextInputDialog functionPointDialog = new TextInputDialog(tabName);
        functionPointDialog.setTitle("New Function Point");
        functionPointDialog.setHeaderText(null);
        functionPointDialog.setGraphic(null);
        functionPointDialog.setContentText("Enter a name for the function point:");

        Optional<String> result = functionPointDialog.showAndWait();

        return result.orElse(null);
    }

    public static List<File> createAddCodeDialog(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Java Files", "*.java"));

        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(stage);

        return selectedFiles;
    }
}

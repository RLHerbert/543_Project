package Project543;

import javafx.event.Event;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import java.util.*;

//TODO: make menu buttons work

public class UI {
    public static ArrayList<ProjectStage> openProjectStages = new ArrayList<ProjectStage>(); //TODO: idk if this will work

    public static ProjectStage openNewWindow() //used on open app and file>new project
    {
        ProjectStage stage = new ProjectStage();
        openProjectStages.add(stage);
//        openNewProjectDialog(new ProjectData());
//        openFunctionPane(stage, new ProjectData());
        return stage;
    }

    public static ProjectStage openNewWindow(String title)
    {
        ProjectStage stage = new ProjectStage(title);
        openProjectStages.add(stage);
        return stage;
    }

    public static void openNewProjectDialog(ProjectData project)
            //Preconditions: A ProjectData object must exist and be passed as a parameter.
            //Post-conditions: ProjectData object's meta data is updated.
    {
        Dialog<ArrayList<String>> dialog = new Dialog<ArrayList<String>>();
        dialog.setTitle("New Project");
        dialog.setHeaderText("Enter project information.");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        //TODO: make sizing based on constants
        grid.setHgap(10);
        grid.setVgap(10);

        TextField projectTitle = new TextField(project.projectName);
        projectTitle.setPromptText("Project Title");
        TextField productName = new TextField(project.productName);
        productName.setPromptText("Product Name");
        TextField author = new TextField(project.creatorName);
        author.setPromptText("Author");
        TextArea comments = new TextArea(project.projectComments);
        comments.setPromptText("Comments");

        grid.add(new Label("Project Title:"), 0, 0);
        grid.add(projectTitle, 1, 0);
        grid.add(new Label("Product Name:"), 0, 1);
        grid.add(productName, 1, 1);
        grid.add(new Label("Author:"), 0, 2);
        grid.add(author, 1, 2);
        grid.add(new Label("Comments:"), 0, 3);
        grid.add(comments, 1, 3);

        dialog.getDialogPane().setContent(grid);

        //Converts OK button result into ArrayList of meta data text
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                ArrayList<String> dialogInfoEntered = new ArrayList<String>(4);
                dialogInfoEntered.add(projectTitle.getText());
                dialogInfoEntered.add(productName.getText());
                dialogInfoEntered.add(author.getText());
                dialogInfoEntered.add(comments.getText());
                return dialogInfoEntered;
            }
            return null;
        });

        //Updates project meta data
        Optional<ArrayList<String>> result = dialog.showAndWait();
        result.ifPresent(dialogInfoEntered ->
                {
                    project.projectName = dialogInfoEntered.get(0);
                    project.productName = dialogInfoEntered.get(1);
                    project.creatorName = dialogInfoEntered.get(2);
                    project.projectComments = dialogInfoEntered.get(3);
                }
        );

    }

    public static ArrayList<Button> openFunctionPane(ProjectStage stage, ProjectData project) //TODO: refactor!! too long
            //Preconditions: A ProjectStage object must exist and be passed as a parameter.
            //Post-conditions: A FunctionPoint tab is added to the project stage.
    {
        //TODO: add tab pane to project stage class so don't have to pass it
        TabPane tabs = new TabPane();
        Tab FPtab = new Tab("Function Points");

        //TODO: adjust sizes to be (at least based on) constants
        HBox complexityBox = new HBox(20, new Label("Simple"), new Label("Average"), new Label("Complex"));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        //TODO: format grid stuff
        grid.add(new Text("Weighting Factors"), 2, 0);
        grid.add(complexityBox, 2, 1);


        for (int i = 2; i < 7; i++) { //row
            grid.add(new Label(InformationDomainValue.InformationDomain.values()[i-2].toString()), 0, i);

            //TODO: don't allow decimals
            TextField numberField = new TextField();
            numberField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
            grid.add(numberField, 1, i);

            ToggleGroup tempRadioSet = new ToggleGroup();
            HBox hBox = new HBox(40);
            for (int j = 0; j < 3; j++) {
                RadioButton tempRadio = new RadioButton(Integer.toString(InformationDomainValue.weightFactors[i-2][j]));
                tempRadio.setToggleGroup(tempRadioSet);
                hBox.getChildren().add(tempRadio);
            }
            grid.add(hBox, 2, i);

            TextField outputField = new TextField();
            outputField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
            outputField.setEditable(false);
            //TODO: update outputs any time an input value is changed
            outputField.setText(Integer.toString(project.functionPointMetric.getSumsOfInputs()[i-2]));
            grid.add(outputField, 3, i);
        }

        //TODO: make a numeric output field that extends TextField
        TextField totalCount = new TextField();
        totalCount.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        totalCount.setEditable(false);
        totalCount.setText(Integer.toString(project.functionPointMetric.getTotalCount()));
        grid.add(new Label("Total Count"),0,7);
        grid.add(totalCount, 3, 7);

        Button computeFPButton = new Button("Compute FP");
        Button VAFButton = new Button("Value Adjustments");
        Button computeCodeSizeButton = new Button("Compute Code Size");
        Button changeLanguageButton = new Button("Change Language");

        grid.add(computeFPButton, 0, 8);
        TextField FPOutput = new TextField();
        FPOutput.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        FPOutput.setEditable(false);
        FPOutput.setText(Integer.toString(project.functionPointMetric.getFunctionPoints()));
        grid.add(FPOutput,3,8);

        grid.add(VAFButton, 0, 9);
        TextField VAFOutput = new TextField();
        VAFOutput.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        VAFOutput.setEditable(false);
        VAFOutput.setText(Integer.toString(project.functionPointMetric.getSumOfValAdjFac()));
        grid.add(VAFOutput,3,9);

        grid.add(computeCodeSizeButton, 0, 10);
        //TODO: add an hbox to hold current lang label and current language output box
        //TODO: also create current lang label and current lang output box
        TextField codeSizeOutput = new TextField();
        codeSizeOutput.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        codeSizeOutput.setEditable(false);
        codeSizeOutput.setText(Integer.toString(project.getCodeSize()));
        grid.add(codeSizeOutput,3,10);

        grid.add(changeLanguageButton, 0, 11);

        FPtab.setContent(grid);
        tabs.getTabs().add(FPtab);
        stage.projectStageLayout.getChildren().add(tabs);
        stage.show();


        //button event stuff
        ArrayList<Button> FPButtons = new ArrayList<Button>();
        FPButtons.add(computeFPButton);
        FPButtons.add(VAFButton);
        FPButtons.add(computeCodeSizeButton);
        FPButtons.add(changeLanguageButton);
        return FPButtons;
    }

    public static ProjectStage updateWindowTitle(ProjectStage stage, ProjectData data)
    {
        if (stage.getTitle().equals(stage.DEFAULT_WINDOW_TITLE))
            stage.setTitle("CECS 543 Metrics Suite - " + data.projectName);
        else {
            stage = openNewWindow("CECS 543 Metrics Suite - " + data.projectName);
        }
        return stage;
    }

    public static Language openLangSelectWindow()
    {
        /* Language Selection Dialog Window initialization */
        Dialog<Language> langSelectWindow = new Dialog<Language>();
        langSelectWindow.setTitle("Language Selection");
        langSelectWindow.setHeaderText("Select one language.");
        langSelectWindow.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        /* Language Radio Buttons initialization*/
        RadioButton languageRadios[] = new RadioButton[13];
        ToggleGroup languageRadiosGroup = new ToggleGroup();
        VBox vbox = new VBox(10);
        int i =0;
        for (Language lang: Language.values())
        {
            RadioButton radio = new RadioButton(lang.toString());
            languageRadios[i] = radio;
            i++;
            radio.setToggleGroup(languageRadiosGroup);
            vbox.getChildren().add(radio);
        }

        langSelectWindow.getDialogPane().setContent(vbox);
        langSelectWindow.showAndWait();

        RadioButton selected = (RadioButton) languageRadiosGroup.getSelectedToggle();
        if (selected == null) {
            System.out.println(Language.NONE);
            return Language.NONE;
        }
        else {
            String lang = selected.getText().toUpperCase().replaceAll("\\s+", "");;
            Language selectedLang = Language.valueOf(lang);
            System.out.println(selectedLang);
            return selectedLang;
        }
    }
}


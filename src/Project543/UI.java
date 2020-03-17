package Project543;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

    public static ProjectStage openNewWindow(ProjectData project)
    {
        ProjectStage stage = new ProjectStage(project.getProjectName());
        openProjectStages.add(stage);
        if (project.functionPointMetric.getFunctionPoints() != 0) {

            openFunctionPane(stage, project);
        }
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
                    project.setFileName();
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
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        //TODO: format grid stuff
        Text fpPaneTitle = new Text("Weighting Factors");
        fpPaneTitle.setStyle("-fx-font: 24 arial;");
        grid.add(fpPaneTitle, 2, 0);
        grid.add(complexityBox, 2, 1);


        for (int i = 2; i < 7; i++) { //row
            grid.add(new Label(InformationDomainValue.InformationDomain.values()[i-2].toString()), 0, i);

            //TODO: don't allow decimals
            TextField numberField = new TextField();
            numberField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
            grid.add(numberField, 1, i);

            ToggleGroup tempRadioSet = new ToggleGroup();
            HBox hBox = new HBox(40);
            String [] complexities = {"SIMPLE", "AVERAGE", "COMPLEX"};
            for (int j = 0; j < 3; j++) {
                RadioButton tempRadio = new RadioButton(Integer.toString(InformationDomainValue.weightFactors[i-2][j]));
                String IDVcomplexity = project.functionPointMetric.getComplexityOfInputs()[i-2].toString().substring(12);
                System.out.println(tempRadio.getText());
                if (complexities[j].equals(IDVcomplexity))
                {
                    tempRadio.setSelected(true);
                }
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
        grid.add(new Label("Total Count"),0,8);
        grid.add(totalCount, 3, 8);

        Button computeFPButton = new Button("Compute FP");
        Button VAFButton = new Button("Value Adjustments");
        Button computeCodeSizeButton = new Button("Compute Code Size");
        Button changeLanguageButton = new Button("Change Language");

        grid.add(computeFPButton, 0, 10);
        TextField FPOutput = new TextField();
        FPOutput.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        FPOutput.setEditable(false);
        FPOutput.setText(Integer.toString(project.functionPointMetric.getFunctionPoints()));
        grid.add(FPOutput,3,10);

        grid.add(VAFButton, 0, 12);
        TextField VAFOutput = new TextField();
        VAFOutput.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        VAFOutput.setEditable(false);
        VAFOutput.setText(Integer.toString(project.functionPointMetric.getSumOfValAdjFac()));
        grid.add(VAFOutput,3,12);

        grid.add(computeCodeSizeButton, 0, 14);
        //TODO: add an hbox to hold current lang label and current language output box
        HBox languageBox = new HBox();
        TextField languageOutput = new TextField();
        languageOutput.setEditable(false);
        languageOutput.setText(project.getDefaultProjectLanguage().toString());
        languageBox.getChildren().add(new Label("Current Language"));
        languageBox.getChildren().add(languageOutput);
        grid.add(languageBox, 2, 14);
        //TODO: also create current lang label and current lang output box
        TextField codeSizeOutput = new TextField();
        codeSizeOutput.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        codeSizeOutput.setEditable(false);
        codeSizeOutput.setText(Integer.toString(project.getCodeSize()));
        grid.add(codeSizeOutput,3,14);

        grid.add(changeLanguageButton, 0, 16);

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

        computeFPButton.setOnAction(e -> {
            //Update project data
//            project.functionPointMetric.setNumberOfExternalInputs(Integer.parseInt(((TextField) getNodeFromGridPane(grid, 1, 2)).getText()));
//            project.functionPointMetric.setNumberOfExternalOutputs(Integer.parseInt(((TextField) getNodeFromGridPane(grid, 1, 3)).getText()));
//            project.functionPointMetric.setNumberOfExternalInquiries(Integer.parseInt(((TextField) getNodeFromGridPane(grid, 1, 4)).getText()));
//            project.functionPointMetric.setNumberOfInternalLogicFiles(Integer.parseInt(((TextField) getNodeFromGridPane(grid, 1, 5)).getText()));
//            project.functionPointMetric.setNumberOfExternalInterfaceFiles(Integer.parseInt(((TextField) getNodeFromGridPane(grid, 1, 6)).getText()));

            //Update FP pane
            int outputVals [] = project.functionPointMetric.getSumsOfInputs();
            for (int i = 2; i < 7; i++) {
//                ((TextField) getNodeFromGridPane(grid, 3, i)).setText(Integer.toString(outputVals[i-2]));
            }

            totalCount.setText(Integer.toString(project.functionPointMetric.getTotalCount()));
            FPOutput.setText(Integer.toString(project.functionPointMetric.getFunctionPoints()));
        });

//        VAFButton.setOnAction(e -> {
//            openVAFWindow(project);
//            VAFOutput.setText(Integer.toString(project.functionPointMetric.getSumOfValAdjFac()));
//        });

        computeCodeSizeButton.setOnAction(e -> {
            if (project.getDefaultProjectLanguage() == Language.NONE)
            {
//                Language lang = openLangSelectWindow();
//                project.setDefaultProjectLanguage(lang);
                languageOutput.setText(project.getDefaultProjectLanguage().toString());
            }
            codeSizeOutput.setText(Integer.toString(project.getCodeSize()));
        });

        changeLanguageButton.setOnAction(e -> {
//            Language lang = openLangSelectWindow();
//            project.setDefaultProjectLanguage(lang);
            languageOutput.setText(project.getDefaultProjectLanguage().toString());
        });

        return FPButtons;
    }

//    public static void openVAFWindow(ProjectData project)
//    {
//        Dialog<ArrayList<Integer>> dialog = new Dialog<ArrayList<Integer>>();
//        dialog.setTitle("Value Adjustments Factors");
//        dialog.setHeaderText("Pick values from 0 to 5");
//        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
//
//        GridPane grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        int j = 0;
//        for (String vaf: ValueAdjustmentFactor.descriptionText) {
//            ChoiceBox vafChoices = new ChoiceBox();
//            vafChoices.getItems().addAll(new String[] {"0", "1", "2", "3", "4", "5"});
//            grid.add(new Label(vaf), 0, j);
//            grid.add(vafChoices, 1, j);
//            vafChoices.setValue(Integer.toString(project.functionPointMetric.getValAdjFac(j)));
//            j++;
//        }
//
//        dialog.getDialogPane().setContent(grid);
//
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
//            {
//                for (int i = 0; i < ValueAdjustmentFactor.NUM_VAF; i++)
//                    project.functionPointMetric.setValAdjFac(i, dialogInfoEntered.get(i));
//            }
//        );
//    }
//
//    public static Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
//        for (Node node : gridPane.getChildren()) {
//            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
//                return node;
//            }
//        }
//        return null;
//    }

    public static ProjectStage updateWindowTitle(ProjectStage stage, ProjectData data)
    {
        if (stage.getTitle().equals(stage.DEFAULT_WINDOW_TITLE))
            stage.setTitle("CECS 543 Metrics Suite - " + data.projectName);
        else {
            stage = openNewWindow("CECS 543 Metrics Suite - " + data.projectName);
        }
        return stage;
    }

//    public static Language openLangSelectWindow()
//    {
//        /* Language Selection Dialog Window initialization */
//        Dialog<Language> langSelectWindow = new Dialog<Language>();
//        langSelectWindow.setTitle("Language Selection");
//        langSelectWindow.setHeaderText("Select one language.");
//        langSelectWindow.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
//
//        /* Language Radio Buttons initialization*/
//        RadioButton languageRadios[] = new RadioButton[13];
//        ToggleGroup languageRadiosGroup = new ToggleGroup();
//        VBox vbox = new VBox(10);
//        int i =0;
//        for (Language lang: Language.values())
//        {
//            RadioButton radio = new RadioButton(lang.toString());
//            languageRadios[i] = radio;
//            i++;
//            radio.setToggleGroup(languageRadiosGroup);
//            vbox.getChildren().add(radio);
//        }
//
//        langSelectWindow.getDialogPane().setContent(vbox);
//        langSelectWindow.showAndWait();
//
//        RadioButton selected = (RadioButton) languageRadiosGroup.getSelectedToggle();
//        if (selected == null) {
//            System.out.println(Language.NONE);
//            return Language.NONE;
//        }
//        else {
//            String lang = selected.getText().toUpperCase().replaceAll("\\s+", "");;
//            Language selectedLang = Language.valueOf(lang);
//            System.out.println(selectedLang);
//            return selectedLang;
//        }
//    }
}


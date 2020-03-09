package Project543;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.util.*;

//TODO: make menu buttons work

public class UI {
    public static void openNewWindow() //used on open app and file>new project
    {
        new ProjectStage();
    }

    public static void createNewProject()
    {
        openNewWindow();
//        newProjectDialog();
    }

    public static void newProjectDialog(ProjectData project)
            //Preconditions: A ProjectData object must exist and be passed as a parameter.
            //Post-conditions: Returns an ArrayList containing project meta data as strings
    {
        Dialog<ArrayList<String>> dialog = new Dialog<ArrayList<String>>();
        dialog.setTitle("New Project");
        dialog.setHeaderText("Enter project information.");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField projectTitle = new TextField(project.projectName);
        projectTitle.setPromptText("Project Title");
        TextField productName = new TextField(project.productName);
        productName.setPromptText("Product Name");
        TextField author = new TextField(project.creatorName);
        author.setPromptText("Author");
        TextField comments = new TextField(project.projectComments);
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

        Optional<ArrayList<String>> result = dialog.showAndWait();
        result.ifPresent(dialogInfoEntered -> System.out.println("Your name: " + dialogInfoEntered.get(2)));

    }

    UI()
    {}
}

//Member Variables
//    //TODO: variables for number of windows, current window, number of panels, current panel
//    ProjectStage currentWindow;
//    int numPanes;
//    TabPane currentPane;
//
//    //Member Methods
//    //Constructors
//    UI()
//    {
//        this.setCurrentWindow(new ProjectStage());
//    }
//
//    //Setters
//    void setCurrentWindow(ProjectStage stage)
//    {
//        this.currentWindow = stage;
//    }
//
//    //Other Methods


//    //TODO: Cleanup and rethink UI
//    //Member Variables
//    private ProjectStage mainWindow, langSelectWindow, valAdjFactWindow;
//    private ProjectStage window;
//    private Scene windowScene;
//
//    //Member Methods
//    //Constructors
//    UI() {
//        //TODO: open a new window (stage)
//        mainWindow = new ProjectStage();
//        mainWindow.show();
//    }
//
//
//
//
//        /* Main Window initialization */
//        mainWindow = new ProjectStage();
//        mainWindow.setTitle("CECS 543 Project543.Project");
//        //mainWindow.setStageSizePos(775, 800, 0, 0);
//        mainWindow.show();
//
//        /* Menu Bar initialization */
////        MenuBar menuBar = new MenuBar();
////        Menu File = new Menu("File");
////        Menu Edit = new Menu("Edit");
////        Menu Preferences = new Menu("Preferences");
////        Menu Metrics = new Menu("Metrics");
////        Menu Help = new Menu("Help");
////
////        menuBar.getMenus().addAll(File, Edit, Preferences, Metrics, Help);
////        VBox vBox = new VBox(menuBar);
////
////        MenuItem New = new MenuItem("New");
////        MenuItem Open = new MenuItem("Open");
////        MenuItem Save = new MenuItem("Save");
////        MenuItem Exit = new MenuItem("Exit");
////        File.getItems().addAll(New, Open, Save, Exit);
////
////        MenuItem Language = new MenuItem("Language");
////        Preferences.getItems().add(Language);
////
////        Menu FP_menu = new Menu("Function Points");
////        MenuItem EnterFPData_menu = new MenuItem("Enter FP Data");
////        FP_menu.getItems().add(EnterFPData_menu);
////        Metrics.getItems().add(FP_menu);
//
//
////        Scene scene = new Scene(vBox, 960, 600);
////        mainWindow.setScene(scene);
//        mainWindow.show();
//
////        Language.setOnAction(event -> {openLangSelectWindow();}); //uncomment for testing
//    }
//
//    //Getters
//
//
//    //Setters
//    public void setMainWindow(ProjectStage stage){
//        this.mainWindow = stage;
//    }
//
//    //Misc. Methods
//    public void openLangSelectWindow()
//    {
//        /* Language Selection Window initialization */
//        langSelectWindow = new ProjectStage();
//        langSelectWindow.setTitle("Language Selection");
//        /* after creating set size function, use instead of code below */
//        /*
//        langSelectWindow.setHeight(775/2);
//        langSelectWindow.setWidth(800/4);
//        langSelectWindow.setX(800/2-100);
//        langSelectWindow.setY(0); //set window sizing
//         */
//
//        langSelectWindow.setStageSizePos(775/2, 800/4, 800/2-100, 100);
//
//        /* Language Radio Buttons initialization*/ //should it be checkboxes like the example in the vision doc?
//        RadioButton languageRadios[] = new RadioButton[13];
//        ToggleGroup languageRadiosGroup = new ToggleGroup();
//        VBox vbox = new VBox(10, new Label("Select one language"));
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
//        Scene scene1 = new Scene(vbox, 775/2, 800/4);
//        langSelectWindow.setScene(scene1);
//        langSelectWindow.show();
//    }
//
//    public void showMainWindow(){
//        mainWindow.show();
//    }


package Project543;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class UI {
    //Member Variables
    private ProjectStage mainWindow, langSelectWindow, valAdjFactWindow;
    private ProjectStage window;
    private Scene windowScene;

    //Member Methods
    //Constructors
    UI() {
        /* Main Window initialization */
        mainWindow = new ProjectStage();
//        mainWindow = new ProjectStage("CECS 543 Metrics Suite");
//        mainWindow.setTitle("CECS 543 Project");
//        mainWindow.setStageSizePos(775, 800, 0, 0);
        mainWindow.show();

        /* Menu Bar initialization */
//        MenuBar menuBar = new MenuBar();
//        Menu File = new Menu("File");
//        Menu Edit = new Menu("Edit");
//        Menu Preferences = new Menu("Preferences");
//        Menu Metrics = new Menu("Metrics");
//        Menu Help = new Menu("Help");
//
//        menuBar.getMenus().addAll(File, Edit, Preferences, Metrics, Help);
//        VBox vBox = new VBox(menuBar);
//
//        MenuItem New = new MenuItem("New");
//        MenuItem Open = new MenuItem("Open");
//        MenuItem Save = new MenuItem("Save");
//        MenuItem Exit = new MenuItem("Exit");
//        File.getItems().addAll(New, Open, Save, Exit);
//
//        MenuItem Language = new MenuItem("Language");
//        Preferences.getItems().add(Language);
//
//        Menu FP_menu = new Menu("Function Points");
//        MenuItem EnterFPData_menu = new MenuItem("Enter FP Data");
//        FP_menu.getItems().add(EnterFPData_menu);
//        Metrics.getItems().add(FP_menu);


//        Scene scene = new Scene(vBox, 960, 600);
//        mainWindow.setScene(scene);
        mainWindow.show();

//        Language.setOnAction(event -> {openLangSelectWindow();}); //uncomment for testing
    }

    //Getters


    //Setters

    //Misc. Methods
    void openLangSelectWindow()
    {
        /* Language Selection Window initialization */
        langSelectWindow = new ProjectStage();
        langSelectWindow.setTitle("Language Selection");
        /* after creating set size function, use instead of code below */
        /*
        langSelectWindow.setHeight(775/2);
        langSelectWindow.setWidth(800/4);
        langSelectWindow.setX(800/2-100);
        langSelectWindow.setY(0); //set window sizing
         */

        langSelectWindow.setStageSizePos(775/2, 800/4, 800/2-100, 100);

        /* Language Radio Buttons initialization*/ //should it be checkboxes like the example in the vision doc?
        RadioButton languageRadios[] = new RadioButton[13];
        ToggleGroup languageRadiosGroup = new ToggleGroup();
        VBox vbox = new VBox(10, new Label("Select one language"));
        int i =0;
        for (Languages lang: Languages.values())
        {
            RadioButton radio = new RadioButton(lang.toString());
            languageRadios[i] = radio;
            i++;
            radio.setToggleGroup(languageRadiosGroup);
            vbox.getChildren().add(radio);
        }

        Scene scene1 = new Scene(vbox, 775/2, 800/4);
        langSelectWindow.setScene(scene1);
        langSelectWindow.show();
    }
}

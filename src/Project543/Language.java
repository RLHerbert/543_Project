package Project543;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public enum Language {
    NONE, ASSEMBLER, ADA, C, CPP, CSHARP, COBOL, FORTRAN, HTML, JAVA, JAVASCRIPT, VBSCRIPT, VISUALBASIC;

    //Member Methods
    //
    //Constructor(s)
    //

    //Misc. Member Methods
    //
    @Override
    public String toString() {
        //return super.toString();

        String programmingLanguage = "ERROR";

        switch(this){
            case NONE:
                programmingLanguage = "";
                break;
            case ASSEMBLER:
                programmingLanguage = "Assembler";
                break;
            case ADA:
                programmingLanguage = "Ada 95";
                break;
            case C:
                programmingLanguage = "C";
                break;
            case CPP:
                programmingLanguage = "C++";
                break;
            case HTML:
                programmingLanguage = "HTML";
                break;
            case JAVA:
                programmingLanguage = "Java";
                break;
            case COBOL:
                programmingLanguage = "COBOL";
                break;
            case CSHARP:
                programmingLanguage = "C#";
                break;
            case FORTRAN:
                programmingLanguage = "FORTRAN";
                break;
            case VBSCRIPT:
                programmingLanguage = "VBScript";
                break;
            case JAVASCRIPT:
                programmingLanguage = "JavaScript";
                break;
            case VISUALBASIC:
                programmingLanguage = "Visual Basic";
                break;
            default:
                System.err.println("ERROR: PROGRAMMING LANGUAGE NOT FOUND");
                break;
        }

//        System.out.println("Languages.toString returned: " + progLang);
        return programmingLanguage;
    }

    public int linesOfCodePerFunctionPoint(){
        //Returns average lines of code per FP for a given language

        int linesOfCode = -1;
        switch(this) {
            case NONE:
                linesOfCode = 0;
                break;
            case VISUALBASIC:
                linesOfCode = 50;
                break;
            case JAVASCRIPT:
                linesOfCode = 54;
                break;
            case VBSCRIPT:
                linesOfCode = 38;
                break;
            case FORTRAN:
                linesOfCode = 90;
                break;
            case CSHARP:
                linesOfCode = 58;
                break;
            case C:
                linesOfCode = 148;
                break;
            case COBOL:
                linesOfCode = 80;
                break;
            case JAVA:
                linesOfCode = 55;
                break;
            case HTML:
                linesOfCode = 43;
                break;
            case CPP:
                linesOfCode = 59;
                break;
            case ADA:
                linesOfCode = 154;
                break;
            case ASSEMBLER:
                linesOfCode = 209;
                break;
            default:
                System.err.println("ERROR: PROGRAMMING LANGUAGE NOT FOUND");
                break;

        }
        return linesOfCode;
    }

    public boolean isNone(){
        //Returns true if the selected languages is None
        if (this == NONE){
            return true;
        }

        return false;
    }

    public static Language openLanguageSelectWindow() {
        /* Language Selection Dialog Window initialization */
        Dialog<Language> langSelectWindow = new Dialog<Language>();
        langSelectWindow.setTitle("Language Selection");
        langSelectWindow.setHeaderText("Select one language.");
        langSelectWindow.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        /* Language Radio Buttons initialization*/
        RadioButton languageRadios[] = new RadioButton[13];
        ToggleGroup languageRadiosGroup = new ToggleGroup();
        VBox vbox = new VBox(10);

        for (int i = 0; i < Language.values().length - 1; i++)
        {
            RadioButton radio = new RadioButton(Language.values()[i+1].toString());
            languageRadios[i] = radio;
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
            if (lang.equals("C#"))
                lang = "CSHARP";
            else if (lang.equals("C++"))
                lang = "CPP";
            else if (lang.equals("ADA95"))
                lang = "ADA";
            Language selectedLang = Language.valueOf(lang);
            System.out.println(selectedLang);
            return selectedLang;
        }
    }
}

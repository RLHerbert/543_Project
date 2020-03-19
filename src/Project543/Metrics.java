package Project543;

import java.util.*;
import javafx.scene.control.*;

public class Metrics {
    //Member Fields
    //
    //Member Enums and Classes
    //

    //Static Member Fields
    //
    //Constant Static Fields
    //

    //Non-Constant Static Fields
    //

    //Non-Static Member Fields
    //
    //Constant Member Fields
    //

    //Non-Constant Member Fields
    //
    protected ArrayList<Integer> saveData;

    //Member Methods
    //
    //Constructor(s)
    //
    public Metrics(){
        saveData = new ArrayList<Integer>();
    }

    public Metrics(String saveDataString){
        this.saveData = new ArrayList<Integer>();
        readSaveData(saveDataString);
    }

    //Getters
    //

    //Setters
    //
    public void setSaveData() {} //Virtual

    //Misc. Member Methods
    //
    public String writeSaveDataString()
    {
        return saveData.toString();
    }

    public void readSaveData(String data) {
        //Scanner readInt = new Scanner(data);

        StringTokenizer lineTokenizer = new StringTokenizer(data, ",");

        while (lineTokenizer.hasMoreTokens())
        {
            String dataToRead = lineTokenizer.nextToken();

            if (dataToRead.charAt(0) == ' '){
                dataToRead = dataToRead.substring(1, dataToRead.length());
            }

            int dataToAdd = Integer.parseInt(dataToRead);
            saveData.add(dataToAdd);
        }
    }

    public Tab toTab()
    {
        return new Tab();
    } //Not implemented?
}

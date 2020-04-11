package Project543.Metrics;

import java.util.*;
import javafx.scene.control.*;

public abstract class Metrics {
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
    public String extraData;

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
    public abstract void setSaveData(); //Virtual

    //Misc. Member Methods
    //

    //Save and open
    //
    public abstract boolean hasChanged();

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
            System.out.println(dataToRead);

            if (dataToRead.charAt(0) == ' '){
                //What case was this for?
                dataToRead = dataToRead.substring(1, dataToRead.length());
            }
            if ((dataToRead.indexOf('#') >=0) || ((dataToRead.indexOf(']') >=0))) {
                dataToRead = dataToRead.substring(0,dataToRead.indexOf("]"));
            }

            int dataToAdd = Integer.parseInt(dataToRead);
            saveData.add(dataToAdd);
        }

        lineTokenizer = new StringTokenizer(data, "#");

        while (lineTokenizer.hasMoreTokens()){
            this.extraData = lineTokenizer.nextToken();
        }
    }
}

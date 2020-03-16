package Project543;

import java.util.*;
import javafx.scene.control.*;

public class Metrics {
    //Member Variables
    //
    //Member Classes and Enums
    //

    //Static Variables
    //

    //Non-Static Variables
    //
    public ArrayList<Integer> saveData;

    //Member Methods
    //
    //Constructor(s)
    //
    public Tab toTab()
    {
        return new Tab();
    }

    //Getters
    //

    //Setters
    //
    public void setSaveData() {} //Virtual

    //Misc. Member Methods
    //
    public String writeData()
    {
        return saveData.toString();
    }

    public void readData(String data)
    {
        Scanner readInt = new Scanner(data);
        while (readInt.hasNextInt())
        {
            saveData.add(readInt.nextInt());
        }
    }


}

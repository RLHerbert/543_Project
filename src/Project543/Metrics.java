package Project543;

import java.util.*;
import javafx.scene.control.*;

public class Metrics {
    public ArrayList<Integer> saveData;

    public Tab toTab()
    {
        return new Tab();
    }

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

    public void setSaveData() {};
}

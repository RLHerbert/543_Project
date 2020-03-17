package Project543;

import java.util.Scanner;

//A class to handle the Value Adjustment Factors (VAF)
public class ValueAdjustmentFactor {
    //Member Variables
    public static final int NUM_VAF = 14; //The 14 value adjustment factors
    public static final String descriptionText[] = {
            "Does the system require reliable backup and recovery processes?",
            "Are specialized data communications required to transfer information to and from the application?",
            "Are there distributed processing functions?",
            "Is performance critical?",
            "Will the system run in an existing, heavily utilized operational environment?",
            "Does the system require online data entry?",
            "Does the online data entry require the input transaction to be built over multiple screens or operations?",
            "Are the internal logical files updated online?",
            "Are the inputs, outputs, files, or inquiries complex?",
            "Is the internal processing complex?",
            "Is the code designed to be reusable?",
            "Are conversion and installation included in the design?",
            "Is the system designed for multiple installations in different organizations?",
            "Is the application designed to facilitate change and for ease of use by the user?"
    };

    private int[] currentVals;


    //Member Methods
    //Constructor(s)
    public ValueAdjustmentFactor(){
        currentVals = new int[NUM_VAF];
        for (int i=0; i < NUM_VAF; i++){
            currentVals[i] = 0;
        }
    }

    public ValueAdjustmentFactor(Scanner savedFile){
        currentVals = new int[NUM_VAF];

        //savedFile.nextLine();
        String fileLine = "";

        for (int i = 0; i < NUM_VAF; i++){
            //this.currentVals[i] = savedFile.nextInt();
            fileLine = savedFile.nextLine();
            fileLine = fileLine.substring(fileLine.lastIndexOf(":")+2);
            this.currentVals[i] = Integer.parseInt(fileLine);
        }
    }

    //TODO: Implement ValAdjFac(SAVEDFILE){}

    //Getters
    public int getVal(int valToGet){
        //Returns the specified VAF
        //PRECONDITIONS: 0 <= valToSet < NUM_VAF

        return currentVals[valToGet];
    }

    public int getSumOfVals(){
        //Returns the sum of the VAFs
        int sumVals = 0;
        for (int i = 0; i < NUM_VAF; i++){
            sumVals += getVal(i);
        }

        return sumVals;
    }

    public int[] getValArray()
    {
        return currentVals;
    }

    //Setters
    public void setVal(int valToSet, int newVal){
        //Sets the value of the specified VAF to a new value
        //PRECONDITIONS: 0 <= valToSet < NUM_VAF, 0 <= newVal <= 5

        if ((0 <= newVal) && (newVal <= 5)) {
            currentVals[valToSet] = newVal;
        }
        else {System.err.println("ERROR: INVALID_VAF_VALUE");}

        //POSTCONDITIONS: The value of the specified VAF is updated to the new value
    }

    public void setAllVals(int[] newVals){
        this.currentVals = newVals.clone();
    }

    //Misc. Member Methods
    @Override
    public String toString(){
        //To allow easy saving
        String outString = "";

        for (int i = 0; i < NUM_VAF; i++){
            outString += "VAF_" + i + ": " + currentVals[i];
            if (i < NUM_VAF-1) {outString += "\n";}
        }

        return outString;
    }
}

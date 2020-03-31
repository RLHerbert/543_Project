package Project543.Metrics;

import java.util.Scanner;

//A class to handle the Value Adjustment Factors (VAF)
public class ValueAdjustmentFactor {
    //Member Fields
    //
    //Member Enums and Classes
    //

    //Static Member Fields
    //
    //Constant Static Fields
    //
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

    //Non-Constant Static Fields
    //

    //Non-Static Member Fields
    //
    //Constant Member Fields
    //

    //Non-Constant Member Fields
    //
    private int[] currentValues;


    //Member Methods
    //
    //Constructor(s)
    //
    public ValueAdjustmentFactor(){
        //Default constructor

        //Initialize member fields
        currentValues = new int[NUM_VAF];
        for (int i=0; i < NUM_VAF; i++){
            currentValues[i] = 0;
        }
    }

    //Getters
    //
    public int getValue(int valueToGet){
        //Returns the specified VAF
        //PRECONDITIONS: 0 <= valToSet < NUM_VAF

        return currentValues[valueToGet];
    }

    public int getSumOfValues(){
        //Returns the sum of the VAFs
        int sumOfValues = 0;
        for (int i = 0; i < NUM_VAF; i++){
            sumOfValues += getValue(i);
        }

        return sumOfValues;
    }

    public int[] getCurrentValuesArray()
    {
        return currentValues;
    }

    //Setters
    public void setValue(int valueToSet, int newValue){
        //Sets the value of the specified VAF to a new value
        //PRECONDITIONS: 0 <= valToSet < NUM_VAF, 0 <= newVal <= 5

        if ((0 <= newValue) && (newValue <= 5)) {
            currentValues[valueToSet] = newValue;
        }
        else {System.err.println("ERROR: INVALID_VAF_VALUE");}

        //POSTCONDITIONS: The value of the specified VAF is updated to the new value
    }

    public void setAllValuesFromArray(int[] newValues){
        for (int i = 0; i < currentValues.length; i++)
            this.currentValues[i] = newValues[i];
    }

    //Misc. Member Methods
    //
}

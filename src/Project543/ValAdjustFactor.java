package Project543;

//A class to hold the value adjustment factors
public class ValAdjustFactor {
    //Quick and dirty, public until otherwise necessary
    public int factor;
    private String descriptionText;

    //Member methods
    //Constructor(s)
    public ValAdjustFactor() {
        this.factor = 0;
        this.descriptionText = "";
    }

    public ValAdjustFactor(String text) {
        factor = 0;
        this.setDescriptionText(text);
    }

    //Getters

    public String getDescriptionText()
    {
        return this.descriptionText;
    }
    //Setters
    public void setDescriptionText(String text)
    {
        this.descriptionText = text;
    }

    //Misc. Member Methods
}

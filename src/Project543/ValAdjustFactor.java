package Project543;

//A class to hold the value adjustment factors
public class ValAdjustFactor {
    //Quick and dirty, public until otherwise necessary
    public int factor;
    private String descriptionText;

    ValAdjustFactor(String text)
    {
        this.setDescriptionText(text);
    }

    void setDescriptionText(String text)
    {
        this.descriptionText = text;
    }

    String getDescriptionText()
    {
        return this.descriptionText;
    }
}

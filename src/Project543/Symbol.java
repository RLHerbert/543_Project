package Project543;

public class Symbol {
    String name;
    String type;
    String scope;

    Symbol(String idk, String idk2, String idk3)
    {
//        System.out.println("Inside Symbol constructor: idk, idk2, idk3 --> " + idk + " " + idk2 + " " + idk3);
        this.name = idk;
        this.type = idk2;
        this.scope = idk3;
    }

    @Override
    public String toString() {
        return name + " " + type + " " + scope;
    }
}
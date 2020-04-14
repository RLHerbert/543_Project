package Project543;

public class Symbol {
    String name;
    String type;
    String scope;

    Symbol(String name, String type, String scope)
    {
        this.name = name;
        this.type = type;
        this.scope = scope;
    }

    @Override
    public String toString() {
        return name + " " + type + " " + scope;
    }
}
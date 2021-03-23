import java.util.*;

public class StringVariables {
    private ArrayList<String> names;
    private ArrayList<String> values;

    public StringVariables() {
        names = new ArrayList<String>();
        values = new ArrayList<String>();
    }

    // getters
    public ArrayList<String> getStringVariableNames() {
        return this.names;
    }

    public ArrayList<String> getStringVariableValues() {
        return this.values;
    }

    public String getValue(String name) {
        int index = names.indexOf(name);
        return values.get(index);
    }

    // custom methods
    public void addVariable(String name, String value) {
        this.names.add(name);
        this.values.add(value);
    }
}

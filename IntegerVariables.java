import java.util.*;

public class IntegerVariables {
    private ArrayList<String> names;

    private ArrayList<Integer> values;

    public IntegerVariables() {
        names = new ArrayList<String>();
        values = new ArrayList<Integer>();
    }

    // getters
    public ArrayList<String> getStringVariableNames() {
        return this.names;
    }

    public ArrayList<Integer> getStringVariableValues() {
        return this.values;
    }

    public int getValue(String name) {
        int index = names.indexOf(name);
        return values.get(index);
    }

    // custom methods
    public void addVariable(String name, int value) {
        this.names.add(name);
        this.values.add(value);
    }
}

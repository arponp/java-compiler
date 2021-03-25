import java.util.*;

public class Variables {
    private ArrayList<String> variableNames;
    private ArrayList<String> variableTypes;

    public Variables() {
        variableNames = new ArrayList<String>();
        variableTypes = new ArrayList<String>();
    }

    // getters

    public String getType(String name) {
        int index = variableNames.indexOf(name);
        return variableTypes.get(index);
    }

    // methods

    public void addVariable(String name, String type) {
        variableNames.add(name);
        variableTypes.add(type);
    }
}

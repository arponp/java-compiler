import java.util.*;
import java.io.*;

public class Compiler {
    public static void main(String[] args) throws FileNotFoundException {
        IntegerVariables intVars = new IntegerVariables();
        StringVariables strVars = new StringVariables();
        try {
            File file = new File("/Users/arponp/Desktop/Mission 20-21/Java mission/java-compiler/sample.txt");
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                if (line.startsWith("#")) { // commenting
                    continue;
                } else if (line.startsWith("var")) { // creating variable
                    if (line.split(" ")[3].startsWith("\"")) { // string
                        String value = "";
                        for (int i = 3; i < line.split(" ").length; i++) {
                            if (i == line.split(" ").length - 1) {
                                value += line.split(" ")[i];
                            } else {
                                value += line.split(" ")[i] + " ";
                            }
                        }
                        value = value.substring(1, value.length() - 1);
                        strVars.addVariable(line.split(" ")[1], value);
                    } else if (isInteger(line.split(" ")[3])) { // integer
                        int value = Integer.parseInt(line.split(" ")[3]);
                        intVars.addVariable(line.split(" ")[1], value);
                    }
                } else if (line.startsWith("print(")) {
                    String query = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
                    if (query.startsWith("\"")) { // print strings
                        System.out.println(query.substring(query.indexOf("\"") + 1, query.length() - 1));
                    } else if (isInteger(query)) { // print integers
                        System.out.println(line.substring(line.indexOf("(") + 1, line.indexOf(")")));
                    } else { // print variables
                        ArrayList<String> strVarNames = strVars.getStringVariableNames();
                        ArrayList<String> intVarNames = intVars.getStringVariableNames();
                        if (strVarNames.indexOf(line.substring(line.indexOf("(") + 1, line.indexOf(")"))) != -1) {
                            System.out.println(
                                    strVars.getValue(line.substring(line.indexOf("(") + 1, line.indexOf(")"))));
                        } else if (intVarNames
                                .indexOf(line.substring(line.indexOf("(") + 1, line.indexOf(")"))) != -1) {
                            System.out.println(
                                    intVars.getValue(line.substring(line.indexOf("(") + 1, line.indexOf(")"))));
                        }
                    }
                } else if (line.startsWith("if ")) { // if statement
                    String condition = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
                    if (condition.contains("==")) { // equal comparator
                        String val1 = condition.split("==")[0];
                        String val2 = condition.split("==")[1];
                        if (isInteger(val1) && isInteger(val2)) {
                            System.out.println(Integer.parseInt(val1) == Integer.parseInt(val2));
                        }
                    }
                }
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

}

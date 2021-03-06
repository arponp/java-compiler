import java.util.*;
import java.io.*;

public class Compiler {
    public static void main(String[] args) throws FileNotFoundException {
        IntegerVariables intVars = new IntegerVariables();
        StringVariables strVars = new StringVariables();
        Variables variables = new Variables();
        try {
            File file = new File("/Users/arponp/Desktop/Mission 20-21/Java mission/java-compiler/sample.txt");
            Scanner input = new Scanner(file);
            Scanner userInput = new Scanner(System.in);
            String loopStatement = "";
            int loopTimesLeft = 0;
            while (input.hasNextLine() || loopTimesLeft > 0) {
                String line = "";
                if (!(loopTimesLeft > 0)) {
                    line = input.nextLine();
                } else {
                    line = loopStatement;
                    loopTimesLeft--;
                }
                if (line.startsWith("    ")) {
                    line = line.substring(4);
                }
                if (line.startsWith("#")) { // commenting
                    continue;
                } else if (line.startsWith("var ")) { // creating variable
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
                        variables.addVariable(line.split(" ")[1], "String");
                    } else if (isInteger(line.split(" ")[3])) { // integer
                        int value = Integer.parseInt(line.split(" ")[3]);
                        intVars.addVariable(line.split(" ")[1], value);
                        variables.addVariable(line.split(" ")[1], "int");
                    } else if (line.split(" ")[3].startsWith("input(")) {
                        String inputMessage = line.substring(line.indexOf("(") + 2, line.indexOf(")") - 1);
                        System.out.print(inputMessage + " ");
                        String value = userInput.nextLine();
                        if (isInteger(value)) {
                            intVars.addVariable(line.split(" ")[1], Integer.parseInt(value));
                            variables.addVariable(line.split(" ")[1], "int");
                        } else {
                            strVars.addVariable(line.split(" ")[1], value);
                            variables.addVariable(line.split(" ")[1], "String");
                        }
                    } else if (line.split(" ")[3].contains("+")) {
                        String[] values = line.split(" ")[3].split("\\+");
                        int sum = 0;
                        for (String value : values) {
                            sum += Integer.parseInt(value);
                        }
                        intVars.addVariable(line.split(" ")[1], sum);
                        variables.addVariable(line.split(" ")[1], "int");
                    } else if (line.split(" ")[3].contains("-")) {
                        String[] values = line.split(" ")[3].split("\\-");
                        int difference = Integer.parseInt(values[0]);
                        for (int i = 1; i < values.length; i++) {
                            difference -= Integer.parseInt(values[i]);
                        }
                        intVars.addVariable(line.split(" ")[1], difference);
                        variables.addVariable(line.split(" ")[1], "int");
                    } else if (line.split(" ")[3].contains("*")) {
                        String[] values = line.split(" ")[3].split("\\*");
                        int product = 1;
                        for (String value : values) {
                            product *= Integer.parseInt(value);
                        }
                        intVars.addVariable(line.split(" ")[1], product);
                        variables.addVariable(line.split(" ")[1], "int");
                    } else if (line.split(" ")[3].contains("/")) {
                        String[] values = line.split(" ")[3].split("\\/");
                        int quotient = Integer.parseInt(values[0]);
                        for (int i = 1; i < values.length; i++) {
                            quotient /= Integer.parseInt(values[i]);
                        }
                        intVars.addVariable(line.split(" ")[1], quotient);
                        variables.addVariable(line.split(" ")[1], "int");
                    } else if (line.split(" ")[3].contains("%")) {
                        String[] values = line.split(" ")[3].split("\\%");
                        int quotient = Integer.parseInt(values[0]);
                        for (int i = 1; i < values.length; i++) {
                            quotient %= Integer.parseInt(values[i]);
                        }
                        intVars.addVariable(line.split(" ")[1], quotient);
                        variables.addVariable(line.split(" ")[1], "int");
                    }
                } else if (line.startsWith("print(")) {
                    String query = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
                    if (query.startsWith("\"")) { // print strings
                        System.out.println(query.substring(query.indexOf("\"") + 1, query.length() - 1));
                    } else if (isInteger(query)) { // print integers
                        System.out.println(line.substring(line.indexOf("(") + 1, line.indexOf(")")));
                    } else if (query.contains("+")) {
                        String[] values = query.split("\\+");
                        int sum = 0;
                        for (String value : values) {
                            sum += Integer.parseInt(value);
                        }
                        System.out.println(sum);
                    } else if (query.contains("-")) {
                        String[] values = query.split("\\-");
                        int difference = Integer.parseInt(values[0]);
                        for (int i = 1; i < values.length; i++) {
                            difference -= Integer.parseInt(values[i]);
                        }
                        System.out.println(difference);
                    } else if (query.contains("*")) {
                        String[] values = query.split("\\*");
                        int product = 1;
                        for (String value : values) {
                            product *= Integer.parseInt(value);
                        }
                        System.out.println(product);
                    } else if (query.contains("/")) {
                        String[] values = query.split("\\/");
                        int quotient = Integer.parseInt(values[0]);
                        for (int i = 1; i < values.length; i++) {
                            quotient /= Integer.parseInt(values[i]);
                        }
                        System.out.println(quotient);
                    } else if (query.contains("%")) {
                        String[] values = query.split("\\%");
                        int quotient = Integer.parseInt(values[0]);
                        for (int i = 1; i < values.length; i++) {
                            quotient %= Integer.parseInt(values[i]);
                        }
                        System.out.println(quotient);
                    } else { // print variables
                        String type = variables.getType(line.substring(line.indexOf("(") + 1, line.indexOf(")")));
                        if (type.equals("String")) {
                            System.out.println(
                                    strVars.getValue(line.substring(line.indexOf("(") + 1, line.indexOf(")"))));
                        } else if (type.equals("int")) {
                            System.out.println(
                                    intVars.getValue(line.substring(line.indexOf("(") + 1, line.indexOf(")"))));
                        }
                    }
                } else if (line.startsWith("if ")) { // if statement
                    String condition = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
                    boolean conditionMet = false;
                    if (condition.contains("==")) { // equal comparator
                        String val1 = condition.split("==")[0];
                        String val2 = condition.split("==")[1];
                        if (isInteger(val1) && isInteger(val2)) { // two integers
                            conditionMet = Integer.parseInt(val1) == Integer.parseInt(val2);
                        } else if (val1.startsWith("\"") && val1.endsWith("\"") && val2.startsWith("\"")
                                && val2.endsWith("\"")) { // two strings
                            conditionMet = val1.equals(val2);
                        } else if (isInteger(val1) || isInteger(val2)) {
                            if (variables.getType(val1) != null) {
                                conditionMet = intVars.getValue(val1) == Integer.parseInt(val2);
                            } else {
                                conditionMet = intVars.getValue(val2) == Integer.parseInt(val1);
                            }
                        } else if (!val1.contains("\"") && !val2.contains("\"")) {// two variables
                            if (variables.getType(val1) != variables.getType(val2)) {
                                System.out.println("Type mismatch compile error");
                                break;
                            }
                            if (variables.getType(val1).equals("String")) { // comparing two strings
                                conditionMet = strVars.getValue(val1).equals(strVars.getValue(val2));
                            } else { // two integers
                                conditionMet = intVars.getValue(val1) == intVars.getValue(val2);
                            }
                        } else {
                            if (variables.getType(val1) != null) {
                                conditionMet = strVars.getValue(val1)
                                        .equals(val2.substring(val2.indexOf("\"") + 1, val2.length() - 1));
                            } else {
                                conditionMet = strVars.getValue(val2)
                                        .equals(val1.substring(val1.indexOf("\"") + 1, val1.length() - 1));
                            }
                        }
                    }
                    if (!conditionMet) {
                        while (true) {
                            if (input.nextLine().startsWith("    ")) {
                                input.nextLine();
                            } else {
                                break;
                            }
                        }
                    }
                } else if (line.startsWith("run(")) { // for loop
                    int times = Integer
                            .parseInt(line.substring(line.indexOf("(") + 1, line.length() - 1).split(",")[1]);
                    String printStatement = line.substring(line.indexOf("(") + 1, line.length() - 1).split(",")[0];
                    if (!printStatement.startsWith("print(")) {
                        System.out.println("Compile error");
                        break;
                    }
                    loopStatement = printStatement;
                    loopTimesLeft = times;
                }
            }
            input.close();
            userInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void executeCode() {

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

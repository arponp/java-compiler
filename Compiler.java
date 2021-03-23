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
                String code = input.nextLine();
                if (code.startsWith("#")) {
                    // commenting
                    continue;
                } else if (code.startsWith("var")) { // creating variable
                    if (code.split(" ")[3].startsWith("\"")) { // string
                        String value = code.split(" ")[3];
                        // value = value.substring(1, value.length() - 1);
                        strVars.addVariable(code.split(" ")[1], value);
                        System.out.println(strVars.getValue(code.split(" ")[1]));
                    } else { // integer
                        int value = Integer.parseInt(code.split(" ")[3]);
                        intVars.addVariable(code.split(" ")[1], value);
                        System.out.println(intVars.getValue(code.split(" ")[1]));
                    }
                }
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

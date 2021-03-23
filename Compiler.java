import java.util.*;
import java.io.*;

public class Compiler {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> stringVarNames = new ArrayList<String>();
        try {
            File file = new File("/Users/arponp/Desktop/Mission 20-21/Java mission/java-compiler/sample.txt");
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                String code = input.nextLine();
                if (code.startsWith("#"))
                    continue;
                else
                    System.out.println(code);
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

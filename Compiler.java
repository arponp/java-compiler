import java.util.*;
import java.io.*;

public class Compiler {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            File file = new File("/Users/arponp/Desktop/Mission 20-21/Java mission/java-compiler/sample.txt");
            Scanner input = new Scanner(file);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        T9 test = new T9("kelly.txt");

        for (String string : test.decode("15")) {
            //System.out.println(string);
        }

        ArrayList<String> tester = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader("kelly.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                char[] row = line.toLowerCase()
                        .toCharArray();

                StringBuilder str = new StringBuilder();
                for (char c : row) {
                    str.append(test.CharToKey(c));
                }
                tester.add(str.toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for (String string : tester) {
            System.out.println(string + " : ");
            for (String out : test.decode(string)) {
                System.out.println("        " + out);
            }
        }
    }
}

package helpers;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class StringInputScanner {
    private final ArrayList<String> result = new ArrayList<String>();

    public StringInputScanner(File input) {
        try (Scanner in = new Scanner((new FileReader(input)))) {
            while (in.hasNext()) {
                result.add(in.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public ArrayList<String> getResult() {
        return result;
    }

}

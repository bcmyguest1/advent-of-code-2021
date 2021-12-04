package helpers;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class IntegerInputScanner {
    private final ArrayList<Integer> result = new ArrayList<>();

    public IntegerInputScanner(File input) {
        this(input, 10);
    }

    public IntegerInputScanner(File input, int base) {
        try (Scanner in = new Scanner((new FileReader(input)))) {
            while (in.hasNext()) {
                result.add(Integer.parseInt(in.nextLine(), base));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public ArrayList<Integer> getResult() {
        return result;
    }
}

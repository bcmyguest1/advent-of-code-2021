import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class BingoBoard {
    ArrayList<ArrayList<Integer>> board = new ArrayList<>();
    boolean rowsBingo = false;
    boolean colsBingo = false;
    int bingoNum;


    public BingoBoard(ArrayList<String> boardInput) {
        for (String row: boardInput) {
            try {
                ArrayList<Integer> rowValues = Arrays.stream(row.trim().split("\\s+")).map(Integer::parseInt)
                        .collect(Collectors.toCollection(ArrayList::new));
                board.add(rowValues);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public boolean checkForWin(HashSet<Integer> inputs) {
        if (inputs.size() < board.size()) {
            return false;
        }
        for (int row = 0; row < board.size(); row++) {
            for (int col = 0; col <= row; col++) {
                if (inputs.contains(board.get(0).get(col)) && inputs.contains(board.get(1).get(col)) &&
                        inputs.contains(board.get(2).get(col)) && inputs.contains(board.get(3).get(col)) &&
                        inputs.contains(board.get(4).get(col))) {
                    colsBingo = true;
                    bingoNum = col;
                    return true;
                } else if (inputs.contains(board.get(row).get(0)) && inputs.contains(board.get(row).get(1)) &&
                        inputs.contains(board.get(row).get(2)) && inputs.contains(board.get(row).get(3)) &&
                        inputs.contains(board.get(row).get(4))) {
                    rowsBingo = true;
                    bingoNum = row;
                    return true;
                }
            }
        }

        return false;
    }

    public Integer getNonWinningSum(HashSet<Integer> inputs) {
        int sum = 0;
        for (int row = 0; row < board.size(); row++) {
            for (int col = 0; col < board.get(row).size(); col++) {
                int cellVal = board.get(row).get(col);
                if (!inputs.contains(cellVal)) {
                    sum += cellVal;
                }
            }
        }
        return sum;
    }
}

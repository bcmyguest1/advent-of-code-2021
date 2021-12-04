import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

/*
* For the purposes of this class we are going to assume that the input is good and that the boards will always be 5x5
* */
public class Bingo {

    private final ArrayList<Integer> resultList;

    private ArrayList<BingoBoard> boards = new ArrayList<>();

    public Bingo(ArrayList<String> input) {
        // parse the input into two different data structure types, one for the results and one for the boards
        resultList = Arrays.stream(input.get(0).split(",")).map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));

        input.remove(0);
        ArrayList<String> boardInput = new ArrayList<String>();
        for (String row: input) {
            if (row.trim().length() == 0 || boardInput.size() == 5) {
                boards.add(new BingoBoard(boardInput));
                boardInput = new ArrayList<String>();
            }
            if (row.length() != 0) {
                boardInput.add(row);
            }

        }
    }

    public int findLastWinningBoardScore() {
        int lastWinnerScore = 0;
        for (int i = 0 ; i < resultList.size(); i++) {
            HashSet<Integer> numsToCheck = new HashSet<>(resultList.subList(0, i+1));
            ArrayList<BingoBoard> boardsToCheck = new ArrayList<>();
            for (BingoBoard board : boards) {
                boolean boardWon = board.checkForWin(numsToCheck);
                if (boardWon) {
                    lastWinnerScore = board.getNonWinningSum(numsToCheck) * resultList.get(i);
                } else {
                    boardsToCheck.add(board);
                }
            }
            boards = boardsToCheck;
        }

        return lastWinnerScore;
    }

    public int findFirstWinningBoardScore() {
        for (int i = 0 ; i < resultList.size(); i++) {
            HashSet<Integer> numsToCheck = new HashSet<>(resultList.subList(0, i+1));
            for (BingoBoard board : boards) {
                boolean boardWon = board.checkForWin(numsToCheck);
                if (boardWon) {
                    return board.getNonWinningSum(numsToCheck) * resultList.get(i);
                }
            }
        }

        return 0;
    }
}
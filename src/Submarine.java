import helpers.StringInputScanner;

import java.io.File;

public class Submarine {
    public static void main(String[] args) {
        System.out.println("Nothing has been done");
        StringInputScanner inputScanner = new StringInputScanner(new File("resources/day 4/input.txt"));
        System.out.println("let's play bingo!");
        Bingo bingo = new Bingo(inputScanner.getResult());

        System.out.println(String.format("First winning board score: %d",bingo.findFirstWinningBoardScore()));
        System.out.println(String.format("Last winning board score: %d",bingo.findLastWinningBoardScore()));
    }
}

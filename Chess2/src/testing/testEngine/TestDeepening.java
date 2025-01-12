package testing.testEngine;

import board.FEN;
import board.Position;
import engine.minimax;
import moveGeneration.MoveGenerator;
import zobrist.ZobristHashing;

public class TestDeepening {
    public static void main(String[] args) {
        new MoveGenerator();
        ZobristHashing.initializeRandomNumbers();

        Position position = new Position();
        int depth = 6;

        System.out.println("ttNegaMax repeated");
        long start = System.currentTimeMillis();
        minimax.repeatedttNegaMax(position, depth);
        long end = System.currentTimeMillis();
        long elapsed = end - start;
        System.out.println("Searched to depth: " + depth + "\nIn ms: " + elapsed + "\nwith result: " + "\n");


        System.out.println("NegaMax repeated");
        start = System.currentTimeMillis();
        minimax.repeatedNegaMax(position, depth);
        end = System.currentTimeMillis();
        elapsed = end - start;
        System.out.println("Searched to depth: " + depth + "\nIn ms: " + elapsed + "\nwith result: " + "\n");

    }
}
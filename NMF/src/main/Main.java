package main;

import io.FileIO;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        testWords();
    }

    public static void test() {

        int M = 10; // rows in X
        int T = 30; // columns in X
        int R = 5; // number of basis columns in W

        Matrix X = new Matrix(M, T);
        MatrixUtilities.randomize(X);
        Matrix W = new Matrix(M, R);
        MatrixUtilities.randomize(W);
        Matrix H = new Matrix(R, T);
        MatrixUtilities.randomize(H);

        NMFCostKL nmfSolver = new NMFCostKL(X, W, H, "solver1");

        int iterations = 200;
        for (int i = 0; i < iterations; i++) {
            System.out.print(i + ": ");
            nmfSolver.doLeftUpdate();
            nmfSolver.doRightUpdate();
            nmfSolver.printReconstructionError();
        }

        System.out.println(X);
        System.out.println(W);
        System.out.println(H);
    }

    public static void testWords() {

        Map<String, String[]> fw = FileIO.readFeatureWords("input/feature_words.tsv");

        Map<String, Integer> ddic = new LinkedHashMap<>();
        Map<String, Integer> wdic = new LinkedHashMap<>();

        int docId = 0, wordId = 0;
        for (String key : fw.keySet()) {
            ddic.put(key, docId++);

            String[] words = fw.get(key);
            for (int i = 0; i < words.length; i++) {
                if (!wdic.containsKey(words[i])) {
                    wdic.put(words[i], wordId++);
                }
            }
        }

        System.out.println("Finished the 1st step!");

        final int rows = fw.keySet().size();
        final int cols = wordId;
        final int k = 100;
        Matrix X = new Matrix(rows, cols);
        Matrix W = new Matrix(rows, k);
        Matrix H = new Matrix(k, cols);
        MatrixUtilities.randomize(W);
        MatrixUtilities.randomize(H);

        for (String key : fw.keySet()) {
            int dId = ddic.get(key);
            String[] words = fw.get(key);
            for (int i = 0; i < words.length; i++) {
                int wId = wdic.get(words[i]);
                X.set(dId, wId, X.get(dId, wId) + 1);
            }
            System.out.println("Finished the doc :" + key);
        }


        NMFCostKL nmfSolver = new NMFCostKL(X, W, H, "solver1");

        int iterations = 200;
        for (int i = 0; i < iterations; i++) {
            System.out.print(i + ": ");
            nmfSolver.doLeftUpdate();
            nmfSolver.doRightUpdate();
            nmfSolver.printReconstructionError();
        }

//        System.out.println(X);
        System.out.println(W);
        System.out.println(H);
    }
}

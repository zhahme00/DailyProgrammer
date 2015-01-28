package com.zee.testing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.IntStream;

public class Daily192I {
    public static void main(String[] args) {
        SpellCheck sp = new SpellCheck("c:\\temp\\enable1.txt");
        sp.printLookup();
    }

    public static class SpellCheck {
        private static int SIZE = 26;
        private int[][] lookup = new int[SIZE][SIZE];

        public SpellCheck(String lookupData) {
            this.reload(lookupData);
        }

        public void reload(String newDataFile) {
            this.lookup = new int[SIZE][SIZE];
            this.update(newDataFile);
        }

        public void update(String dataFile) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(dataFile));
                String word = null;
                while ((word = reader.readLine()) != null) {
                    this.build(word);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public boolean check(String word) {

            return false;
        }

        public void printLookup() {
            // Column header
            System.out.format("%c", ' ');
            IntStream.range(97, 97 + 26).forEach(i -> System.out.format("%6c ", (char) i));
            System.out.println();

            int i = 97;
            for(int[] row: lookup) {
                System.out.format("%c", (char)(i++));
                for (int col: row) {
                    System.out.format("%6d ", col);
                }
                System.out.println();
            }
        }

        private void build(String word) {
            for(int i = 0; i < word.length() - 1; i++) {
                int row = word.charAt(i) - 97;
                int col = word.charAt(i + 1) - 97;

                this.lookup[row][col]++;
            }
        }
    }
}

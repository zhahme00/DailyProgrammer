package com.zee.testing;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Console;

// http://www.reddit.com/r/dailyprogrammer/comments/2tr6yn/2015126_challenge_199_bank_number_banners_pt_1/
public class Daily199Part1 {
    public static void main(String[] args) {
        Console c = System.console();
        if (c == null) {
            System.out.println("No console; running with mock data...");
            String[] simulation = new String[]{
                    "000000000", "111111111", "490067715"
            };

            Numbers n = new Numbers();
            for(String trial: simulation) {
                printEntries(n.getEntries(trial));
            }
        }
        else{
            // todo
            throw new NotImplementedException();
        }
    }

    private static void printEntries(String[] lines) {
        for(String s : lines){
            System.out.println(s);
        }
    }

    // Anticipating more work with printing in part 2 so this class was implemented.
    public static class Numbers {
        private static String[][] font =
                {
                        // 0
                        {" _ ", "| |", "|_|"},
                        {"   ", "  |", "  |"},
                        {" _ ", " _|", "|_ "},
                        {" _ ", " _|", " _|"},
                        {"   ", "|_|", "  |"},
                        // 5
                        {" _ ", "|_ ", " _|"},
                        {" _ ", "|_ ", "|_|"},
                        {" _ ", "  |", "  |"},
                        {" _ ", "|_|", "|_|"},
                        // 9
                        {" _ ", "|_|", " _|"}
                };

        private final StringBuilder[] entryBuilders;

        public  Numbers() {
            // Number representation in pipes and underscores is 3 lines high.
            this.entryBuilders = new StringBuilder[] {
                    new StringBuilder(), new StringBuilder(), new StringBuilder()
            };
        }

        public String[] getEntry(char number) {
            return this.getEntry(Character.getNumericValue(number));
        }

        public String[] getEntry(int number) {
            assert number >= 0 && number <= 9;
            if (number < 0 || number > 9) {
                throw new IllegalArgumentException("number");
            }

            return Numbers.font[number];
        }

        public String[] getEntries(String numberSequence) {
            for(StringBuilder b : this.entryBuilders) {
                b.setLength(0);
            }

            // Get the strings of each number in the parameter sequence and append each line into a line builder.
            for (int i = 0, n = numberSequence.length(); i < n; i++) {
                String[] numberStrings = this.getEntry(numberSequence.charAt(i));

                assert numberStrings.length == 3 : "Each entry must be 3 lines high!";
                for(int j = 0; j < numberStrings.length; j++ ) {
                    this.entryBuilders[j].append(numberStrings[j]);
                }
            }

            String[] result = new String[this.entryBuilders.length];
            for (int i = 0; i < this.entryBuilders.length; i++) {
                result[i] = this.entryBuilders[i].toString();
            }

            return result;
        }
    }
}

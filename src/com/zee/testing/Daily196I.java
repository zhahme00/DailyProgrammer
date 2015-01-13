package com.zee.testing;

import java.util.Iterator;

// http://www.reddit.com/r/dailyprogrammer/comments/2rnwzf/20150107_challenge_196_intermediate_rail_fence/
public class Daily196I {
    public static void main(String[] args) {
        switch (args[0]) {
            case "enc":
                System.out.println(encode(args[2], Integer.parseInt(args[1])));
                break;

            case "dec":
                System.out.println(decode(args[2], Integer.parseInt(args[1])));
                break;

            default:
                System.out.format("Command '%s' not supported", args[0]);
                break;
        }
    }

    private static String decode(String cipherText, int rails) {
        // Degenerate case
        if (rails <= 1) {
            return cipherText;
        }
        // Generates line number sequence
        DiscreteOscillator lineNumbers = new DiscreteOscillator(rails, cipherText.length());

        // Determine char count per line
        int[] lineCharCount = new int[rails];
        for(int line : lineNumbers) {
            lineCharCount[line]++;
        }

        // Split the cipher text into line chunks
        String[] cipherLines = new String[rails];
        int index  = 0;
        for (int i = 0; i < lineCharCount.length; i++) {
            cipherLines[i] = cipherText.substring(index, index + lineCharCount[i]);
            index += lineCharCount[i];
        }

        // Assemble plain text
        StringBuilder plainText = new StringBuilder(cipherText.length());
        int[] indices = new int[rails];
        for(int line : lineNumbers) {
            plainText.append(cipherLines[line].charAt( indices[line]));
            indices[line]++;
        }

        return plainText.toString();
    }

    private static String encode(String plainText, int rails) {
        // Degenerate case
        if (rails <= 1) {
            return plainText;
        }

        // Storage for each encoded line
        StringBuilder[] cipherLines = new StringBuilder[rails];
        for(int i = 0; i < cipherLines.length; i++) {
            cipherLines[i] = new StringBuilder(plainText.length() / 2);
        }

        // Encode block - decide which line each plain text character belongs in
        DiscreteOscillator lineNumbers = new DiscreteOscillator(rails, plainText.length());
        int index = 0;
        for(int line : lineNumbers) {
            cipherLines[line].append(plainText.charAt(index++));
        }

        // Assemble
        StringBuilder cipherText = new StringBuilder(plainText.length());
        for (StringBuilder cl: cipherLines) {
            cipherText.append(cl);
        }

        return cipherText.toString();
    }

    static class DiscreteOscillator implements Iterator<Integer>, Iterable<Integer> {
        private final int amplitude;
        private final int duration;
        private int y;
        private int x;
        private int incrementer;

        public DiscreteOscillator(int amplitude, int duration) {
            this.amplitude = amplitude;
            this.duration = duration;

            this.reset();
        }

        private void reset() {
            this.y = 0;
            this.x = 0;
            this.incrementer = -1;
        }

        @Override
        public boolean hasNext() {
            return this.x < this.duration;
        }

        @Override
        public Integer next() {
            if (this.y == (this.amplitude - 1) || this.y == 0) {
                incrementer = -incrementer;
            }

            int currentY = this.y;
            this.x++;
            this.y += this.incrementer;
            return currentY;
        }

        @Override
        public Iterator<Integer> iterator() {
            this.reset();
            return this;
        }
    }
}

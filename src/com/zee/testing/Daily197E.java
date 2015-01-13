package com.zee.testing;

import java.util.Random;

// http://www.reddit.com/r/dailyprogrammer/comments/2s7ezp/20150112_challenge_197_easy_isbn_validator/
public class Daily197E {
    public static void main(String[] args) {
        String isbn = getISBN();
        System.out.format("Generated ISBN %s is %s\n", isbn, isValidISBN(isbn) ? "Valid" : "Invalid");
    }

    public static boolean isValidISBN(String isbn) {
        int sum = 0;
        int multiplier = 10;
        for (int i = 0; i < isbn.length(); i++) {
            int number = 0;
            char c = isbn.charAt(i);
            if (c == 'X') {
                number = 10;
            } else if (c == '-') {
                continue;
            } else {
                number = Character.getNumericValue(c);
            }

            sum += multiplier-- * number;
        }

        return sum % 11 == 0;
    }

    public static String getISBN() {
        return getISBN(new Random());
    }

    public static String getISBN(Random r) {
        // generate 1 - 9 digits.
        int[] octets = new int[10];
        int sum = 0;
        for (int i = 10; i >= 2; i--) {
            sum += (octets[10 - i] = r.nextInt(11)) * i;
        }

        // generate 10th digit.
        octets[9] = (11 - (sum % 11)) % 11;

        // stringify
        StringBuilder sb = new StringBuilder(10);
        for (int i : octets) {
            sb.append(i == 10 ? "X" : Integer.toString(i));
        }

        return sb.toString();
    }
}
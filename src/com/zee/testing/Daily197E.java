package com.zee.testing;

// http://www.reddit.com/r/dailyprogrammer/comments/2s7ezp/20150112_challenge_197_easy_isbn_validator/
public class Daily197E {
    public static void main(String[] args) {
        System.out.println(isValidISBN(args[0]) ? "Valid" : "Invalid");
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
}

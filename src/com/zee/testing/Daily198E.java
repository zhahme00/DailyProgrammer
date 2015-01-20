package com.zee.testing;

// http://www.reddit.com/r/dailyprogrammer/comments/2syz7y/20150119_challenge_198_easy_words_with_enemies/
public class Daily198E {
    public static void main(String[] args) {
        // eliminate matching bytes
        byte[] left = args[0].getBytes();
        byte[] right = args[1].getBytes();
        for(int l = 0; l < left.length; l++) {
            for (int r = 0; r < right.length; r++) {
                if (left[l] == right[r]) {
                    left[l] = right[r] = 0;
                    break;
                }
            }
        }

        // reconstruct byte array to string
        String leftRemnant = getString(left);
        String rightRemnant = getString(right);

        // output
        int result = Integer.compare(leftRemnant.length(), rightRemnant.length());
        String status = result == 0 ? "Tie!" : result > 0 ? "Left Wins!" : "Right Wins";
        System.out.format("%s - Left: %s, Right: %s", status, prettify(leftRemnant), prettify(rightRemnant));
    }

    public static String prettify(String s) {
        return s.isEmpty() ? "(none)" : s;
    }

    public static String getString(byte[] arr) {
        int index = 0;
        char[] c = new char[arr.length];
        for(byte b : arr) {
            if (b != 0) {
                c[index++] = (char)b;
            }
        }

        return new String(c, 0, index);
    }
}
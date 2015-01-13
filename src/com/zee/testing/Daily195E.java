package com.zee.testing;

public class Daily195E {

    public static void main(String[] args) {
        String[] inputs = getInputs();
        String[][] symbolicLinks = buildSymbolicLinks(inputs);
        String result = getPath(inputs[inputs.length - 1], symbolicLinks);

        System.out.println(result);
    }

    private  static String getPath(String sourcePath, String[][] symbolicLinks) {
        String[] pathChunks = sourcePath.substring(1).split("/");
        String searchKey = "";
        for (String chunk : pathChunks) {
            searchKey += "/" +  chunk;
            String link = null;

            System.out.format("chunk: %s, searchKey: %s\n", chunk, searchKey);
            while((link = getLink(searchKey, symbolicLinks)) != null) {
                searchKey = link;
            }
        }

        return searchKey;
    }

    private static String[][] buildSymbolicLinks(String[] inputs) {
        int rows = Integer.parseInt(inputs[0]);
        String[][] lookup = new String[rows][2];
        for(int r = 1; r <= rows; r++) {
            String[] chunks  = inputs[r].split(":");
            lookup[r - 1][0] = chunks[0];
            // Want a consistent looking value (no trailing slashes).
            if (chunks[1].charAt(chunks[1].length() - 1)  == '/') {
                lookup[r - 1][1] = chunks[1].substring(0, chunks[1].length()-1);
            }
            else {
                lookup[r - 1][1] = chunks[1];
            }
        }

        return lookup;
        //return new String[0][0];
    }

    private static String getLink(String searchKey, String[][] lookup) {
        for (int r = 0; r < lookup.length; r++) {
            if (searchKey.equals(lookup[r][0])) {
                return lookup[r][1];
            }
        }

        return null;
    }

    private static String[] getInputs() {
//        return new String[]{
//                "4",
//                "/bin/thing:/bin/thing-3",
//                "/bin/thing-3:/bin/thing-3.2",
//                "/bin/thing-3.2/include:/usr/include",
//                "/usr/include/SDL:/usr/local/include/SDL",
//                "/bin/thing/include/SDL/stan"
//        };
        return new String[] {
                "3",
                "/bin:/usr/bin",
                "/usr/bin:/usr/local/bin/",
                "/usr/local/bin/log:/var/log-2014",
                "/bin/log/rc"
        };
    }
}

package rabin_karp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RabinKarpExtended {
    private String text;
    private TreeMap<Integer, Integer> number2position;
    private int d = 256; // a number of characters in the input alphabet
    private int q = 101; // a prime number

    public RabinKarpExtended(String text) {
        this.text = text;
    }

    public List<Integer> search(String query) {
        ArrayList<Integer> indices;
        int queryLength = query.length();
        int queryHash = 0; // hash value for query

        // Calculate the hash value of pattern and first window of text
        for (int i = 0; i < queryLength; i++) {
            queryHash = (d * queryHash + query.charAt(i)) % q;
        }

        // creates txtHash with positions
        createIndex(queryLength);

        // find equals queryHash & txtHash
        int finalQueryHash = queryHash;
        indices = (ArrayList<Integer>) number2position.entrySet().stream()
                .filter(entry -> {
                    if (finalQueryHash == entry.getValue()) {
                        // Check for characters one by one
                        int j;
                        for (j = 0; j < queryLength; j++) {
                            if (text.charAt(entry.getKey() + j) != query.charAt(j)) {
                                break;
                            }
                        }
                        return j == queryLength;
                    }
                    return false;
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return indices;
    }

    // creates indexes for each slide of query length
    private void createIndex(int queryLength) {
        int h = 1;
        int pos;
        int textHash = 0;

        // The value of h would be "pow(d, queryLength-1)%q"
        for (pos = 0; pos < queryLength - 1; pos++)
            h = (h * d) % q;

        for (pos = 0; pos < queryLength; pos++) {
            textHash = (d * textHash + text.charAt(pos)) % q;
        }

        // Slide the pattern over text one by one
        for (pos = 0; pos <= text.length() - queryLength; pos++) {
            number2position.put(pos, textHash);

            // Calculate hash value for next window of text: Remove
            // leading digit, add trailing digit
            if (pos < text.length() - queryLength) {
                textHash = (d * (textHash - text.charAt(pos) * h) + text.charAt(pos + queryLength)) % q;

                // We might get negative value of textHash, converting it
                // to positive
                if (textHash < 0) {
                    textHash = (textHash + q);
                }
            }
        }
    }
}
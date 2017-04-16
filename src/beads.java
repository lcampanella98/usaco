/*
ID: enzocam1
LANG: JAVA
PROG: beads
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class beads {
    private static int mostBeadsSplit;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("beads.in"));
             PrintWriter pw = new PrintWriter("beads.out")) {
            int n = Integer.parseInt(br.readLine());
            String beadStr = br.readLine();
            char[] beads = beadStr.concat(beadStr).toCharArray();
            for (int splitIndex = 1; splitIndex < beads.length - 1; splitIndex++) {
                int[] numBeads = numBeadsAtSplit(beads, splitIndex);
                mostBeadsSplit = Math.max(mostBeadsSplit, numBeads[0] + numBeads[1]);
            }
            pw.println(mostBeadsSplit);
            pw.flush();
        } catch (IOException e) {
        }

    }

    private static int[] numBeadsAtSplit(char[] beads, int splitIndex) {
        int maxLen = beads.length / 2;
        int rightIndex = splitIndex, leftIndex = splitIndex - 1;
        int rightCt = 0, leftCt = 0;
        while (rightIndex < beads.length && beads[rightIndex] == 'w' && rightCt < maxLen) {rightCt++;rightIndex++;}
        if (rightIndex < beads.length && rightCt < maxLen) {
            char rightChar = beads[rightIndex++];
            rightCt++;
            while (rightIndex < beads.length
                    && (beads[rightIndex] == rightChar || beads[rightIndex] == 'w') && rightCt < maxLen) {
                rightCt++;
                rightIndex++;
            }
        }
        while (leftIndex >= 0 && beads[leftIndex] == 'w' && rightCt + leftCt < maxLen) {leftCt++;leftIndex--;}
        if (leftIndex >= 0 && rightCt + leftCt < maxLen) {
            char leftChar = beads[leftIndex--];
            leftCt++;
            while (leftIndex >= 0 && (beads[leftIndex] == leftChar
                    || beads[leftIndex] == 'w') && rightCt + leftCt < maxLen) {
                leftCt++;
                leftIndex--;
            }
        }
        return new int[] {leftCt, rightCt};
    }
}

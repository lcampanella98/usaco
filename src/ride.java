/*
ID: enzocam1
LANG: JAVA
PROG: ride
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ride {
    public static void main(String[] args) {

        try (PrintWriter out = new PrintWriter("ride.out");
             BufferedReader br = new BufferedReader(new FileReader("ride.in"))) {
            String comet, group;
            int cometProd, groupProd;
            while (br.ready()) {
                comet = br.readLine();
                group = br.readLine();
                cometProd = productWord(comet);
                groupProd = productWord(group);
                out.println(cometProd % 47 == groupProd % 47 ? "GO" : "STAY");
            }
            out.flush();
        } catch (IOException e) {
        }
    }

    private static int productWord(String word) {
        int prod = 1;
        for (int i = 0; i < word.length(); i++) prod *= (int)word.charAt(i) - (int)'A' + 1;
        return prod;
    }

}

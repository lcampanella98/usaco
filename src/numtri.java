/*
ID: enzocam1
LANG: JAVA
PROG: numtri
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class numtri {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("numtri.in"));
             PrintWriter pw = new PrintWriter("numtri.out")) {
            int n = Integer.parseInt(br.readLine());
            int[][] t = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j <= i; j++) t[i][j] = Integer.parseInt(st.nextToken());
            }

            for (int i = n - 2; i >= 0; i--) {
                for (int j = 0; j <= i; j++) {
                    t[i][j] += Math.max(t[i + 1][j], t[i + 1][j + 1]);
                }
            }
            pw.println(t[0][0]);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

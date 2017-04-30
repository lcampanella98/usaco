/*
ID: enzocam1
LANG: JAVA
PROG: crypt1
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class crypt1 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("crypt1.in"));
             PrintWriter pw = new PrintWriter("crypt1.out")) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int[] digits = new int[n];
            for (int i = 0; i < n; i++) digits[i] = Integer.parseInt(st.nextToken());
            int a, b, c, d, e;
            int ai, bi, ci, di, ei;
            int n1, p1, p2;
            int ways = 0;
            //long start, end;
            //start = System.nanoTime();
            for (ai = 0; ai < digits.length; ai++) {

                a = digits[ai];
                for (di = 0; di < digits.length; di++) {
                    d = digits[di];
                    if (a * d > 10) continue;
                    for (ei = 0; ei < digits.length; ei++) {
                        e = digits[ei];
                        if (a * e > 10) continue;
                        for (bi = 0; bi < digits.length; bi++) {
                            b = digits[bi];
                            for (ci = 0; ci < digits.length; ci++) {
                                c = digits[ci];
                                n1 = 100 * a + 10 * b + c;
                                p1 = n1 * e;
                                if (p1 > 999 || !allDigitsIn(p1, digits)) continue;
                                p2 = n1 * d;
                                if (p2 > 999 || ! allDigitsIn(p2, digits)) continue;
                                p2 *= 10;
                                if (!allDigitsIn(p1 + p2, digits)) continue;
                                ways++;
                                //System.out.println(String.valueOf(a) + String.valueOf(b) + String.valueOf(c) + " * " + String.valueOf(d) + String.valueOf(e));
                            }
                        }
                    }
                }
            }
            pw.println(ways);
            pw.flush();
            //end = System.nanoTime();
            //System.out.println(((end - start) / 1000000.0) + " ms");
        } catch (IOException e) {
        }
    }

    private static boolean allDigitsIn(int n, int[] p) {
        int d, i;
        while (n > 0) {
            d = n % 10;
            for (i = 0; i < p.length; i++) if (p[i] == d) break;
            if (i >= p.length) return false;
            n /= 10;
        }
        return true;
    }

}

/*
ID: enzocam1
LANG: JAVA
PROG: pprime
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class pprime {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("pprime.in"));
             PrintWriter pr = new PrintWriter("pprime.out")) {
            long start, end;
            start = System.currentTimeMillis();
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            int aDigits = 1 + (int)Math.log10(a), bDigits = 1 + (int)Math.log10(b);
            SortedSet<Integer> palPrimes = new TreeSet<>();
            for (int nDigits = aDigits; nDigits <= bDigits; nDigits++) {
                List<Integer> pals = new ArrayList<>();
                nDigitPals(0, nDigits, 0, pals);
                for (int pal : pals) {
                    if (pal >= a && pal <= b && isPrimeNoCheck2(pal)) palPrimes.add(pal);
                }
            }
            for (int pal : palPrimes) pr.println(pal);

            pr.flush();
            end = System.currentTimeMillis();
            System.out.println("code executed in " + (end - start) + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isPrimeNoCheck2(int n) {
        if (n < 2) return false;
        if (n < 4) return true;
        int rt = (int)Math.sqrt(n);
        for (int div = 3; div <= rt; div++) {
            if (n % div == 0) return false;
        }
        return true;
    }

    public static void nDigitPals(int d, int n, int pal, List<Integer> pals) {
        if (d >= n / 2) {
            int tempPal = pal, reversedPal = 0, tenMask = 1;
            while (tempPal > 0) {
                reversedPal = 10 * reversedPal + tempPal % 10;
                tenMask *= 10;
                tempPal /= 10;
            }
            if (n % 2 == 1) {
                pal *= tenMask * 10;
                int start = d == 0 ? 1 : 0, inc = d == 0 ? 2 : 1;
                for (int dMid = start; dMid < 10; dMid += inc) {
                    pals.add(pal + tenMask * dMid + reversedPal);
                }
            } else {
                pal *= tenMask;
                pals.add(pal + reversedPal);
            }
            return;
        }
        int tenPal = pal * 10;
        int start = d == 0 ? 1 : 0, inc = d == 0 ? 2 : 1;
        for (int di = start; di < 10; di += inc) {
            nDigitPals(d + 1, n, tenPal + di, pals);
        }
    }

    public static boolean isPal(int a) {
        int numDigits = (int)Math.log10(a) + 1;
        boolean odd = numDigits % 2 == 1;
        int half = numDigits / 2;
        int[] dList = new int[half];
        int i;
        for (i = 0; i < half; i++) {
            dList[i] = a % 10;
            a /= 10;
        }
        if (odd) a /= 10;
        for (i = dList.length - 1; i >= 0; i--) {
            if (dList[i] != a % 10) return false;
            a /= 10;
        }
        return true;
    }

}

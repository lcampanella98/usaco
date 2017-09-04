/*
ID: enzocam1
LANG: JAVA
PROG: sprime
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class sprime {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("sprime.in"));
             PrintWriter pw = new PrintWriter("sprime.out")) {
            long start, end;
            start = System.currentTimeMillis();
            int n = Integer.parseInt(br.readLine());
            List<Integer> ribs = new ArrayList<>();
            getRibs(n, 0, 0, ribs);
            for (int rib : ribs) pw.println(rib);
            end = System.currentTimeMillis();
            System.out.println("code executed in " + (end - start) + " ms");
        }catch(IOException e) {
            e.printStackTrace();
        }

    }

    public static int[] primeDigits = {2, 3, 5, 7};

    public static void getRibs(int n, int d, int rib, List<Integer> primeRibs) {
        if (d == n) {
            primeRibs.add(rib);
            return;
        }
        if (d == 0) {
            for (int i : primeDigits) {
                getRibs(n, d + 1, 10 * rib + i, primeRibs);
            }
        } else {
            int tempRib = 10 * rib, tempRib2;
            for (int i = 1; i < 10; i += 2) {
                tempRib2 = tempRib + i;
                if (isPrime(tempRib2)) {
                    getRibs(n, d + 1, tempRib2, primeRibs);
                }
            }
        }
    }

    public static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n < 4) return true;
        int rt = (int)Math.sqrt(n);
        for (int div = 2; div <= rt; div++) {
            if (n % div == 0) return false;
        }
        return true;
    }

    public static int[] getPrimes(int floor, int ceil ) {
        boolean[] flags = new boolean[ceil + 1];
        flags[0] = true;
        flags[1] = true;
        for (int i = 2; i < flags.length; i++) {
            if (!flags[i]) {
                for (int j = i + i; j < flags.length; j+=i) flags[j] = true;
            }
        }
        List<Integer> pList = new ArrayList<>();
        for (int i = floor; i < flags.length; i++) if (!flags[i]) pList.add(i);
        int[] primes = new int[pList.size()];
        for (int i = 0; i < pList.size(); i++) primes[i] = pList.get(i);
        return primes;
    }


}

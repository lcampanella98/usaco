import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/*
ID: enzocam1
LANG: JAVA
PROG: ariprog
 */
public class ariprog {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new FileReader("ariprog.in"));
            PrintWriter pw = new PrintWriter("ariprog.out")) {
            long start, end;
            start = System.currentTimeMillis();
            int n = Integer.parseInt(br.readLine());
            int m = Integer.parseInt(br.readLine());

            int len = (m + 1) * (m + 2) / 2;
            int[] bs = new int[len];
            boolean[] flags = new boolean[2 * m * m + 1];

            int i = 0, p2, sum;
            for (int p = 0; p <= m; ++p) {
                p2 = p * p;
                for (int q = p; q <= m; ++q) {
                    sum = p2 + q * q;
                    bs[i++] = sum;
                    flags[sum] = true;
                }
            }

            // remove dups
            Arrays.sort(bs);
            bs = removeDuplicates(bs);

            SortedSet<Sequence> seqs = new TreeSet<>();
            int a1i, b, seqLen, a0, cur, curi, target;
            for (int a0i = 0; a0i < bs.length - 1; a0i++) {
                a0 = bs[a0i];
                for (a1i = a0i + 1; a1i < bs.length; a1i++) {
                    curi = a1i;
                    cur = bs[curi];
                    b = cur - a0;
                    seqLen = 2;
                    target = cur + b;
                    while (seqLen < n && target < flags.length && flags[target]) {
                        target += b;
                        seqLen++;
                    }
                    if (seqLen == n) {
                        seqs.add(new Sequence(a0, b));
                    }

                }
            }
            if (seqs.size() == 0) pw.println("NONE");
            for (Sequence s : seqs) {
                pw.println(s.a + " " + s.b);
            }
            end = System.currentTimeMillis();
            System.out.println("code executed in " + (end - start) + " ms");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] removeDuplicates(int[] A) {
        if (A.length < 2)
            return A;

        int j = 0;
        int i = 1;

        while (i < A.length) {
            if (A[i] == A[j]) {
                i++;
            } else {
                A[++j] = A[i++];
            }
        }

        return Arrays.copyOf(A, j + 1);
    }
}

class Sequence implements Comparable<Sequence>{
    int a, b;
    public Sequence(int a, int b) {this.a = a;this.b = b;}

    @Override
    public int compareTo(Sequence o) {
        if (b > o.b) return 1;
        if (b < o.b) return -1;
        if (a > o.a) return 1;
        if (a < o.a) return -1;
        return 0;
    }
}
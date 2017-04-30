/*
ID: enzocam1
LANG: JAVA
PROG: combo
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class combo {
    static class Comb implements Comparable<Comb>{
        private int x1, x2, x3, n;
        public Comb(int c1, int c2, int c3, int n) {x1=(c1+n)%n;x2=(c2+n)%n;x3=(c3+n)%n;this.n=n;}

        @Override
        public boolean equals(Object other) {
            return other instanceof Comb && hashCode() == other.hashCode();
        }

        @Override
        public int hashCode() {
            return 1000*n + 100*x1+10*x2+x3;
        }

        public Comb newCombInc(int d1, int d2, int d3) {return new Comb(x1+d1,x2+d2,x3+d3,n);}
        public int compareTo(Comb o) {
            if (x1>o.x1)return 1;
            if (x1<o.x1)return -1;
            if (x2>o.x2)return 1;
            if (x2<o.x2)return -1;
            if (x3>o.x3)return 1;
            if (x3<o.x3)return -1;
            return 0;
        }
        public String toString() {
            return (x1+1) + "," + (x2+1) + "," + (x3+1);
        }
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("combo.in"));
             PrintWriter pw = new PrintWriter("combo.out")) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int f1 = Integer.parseInt(st.nextToken()), f2 = Integer.parseInt(st.nextToken()), f3 = Integer.parseInt(st.nextToken());
            f1--;f2--;f3--;
            st = new StringTokenizer(br.readLine(), " ");
            int m1 = Integer.parseInt(st.nextToken()), m2 = Integer.parseInt(st.nextToken()), m3 = Integer.parseInt(st.nextToken());
            m1--;m2--;m3--;
            Set<Comb> cSet = new HashSet<>();
            int d1, d2, d3;
            Comb cf = new Comb(f1, f2, f3, n), mf = new Comb(m1, m2, m3, n);
            for (d1 = -2; d1 <= 2; d1++) {
                for (d2 = -2; d2 <= 2; d2++) {
                    for (d3 = -2; d3 <= 2; d3++) {
                        cSet.add(cf.newCombInc(d1, d2, d3));
                        cSet.add(mf.newCombInc(d1, d2, d3));
                    }
                }
            }
            pw.println(cSet.size());
            pw.flush();
        } catch (IOException e) {
        }
    }
}

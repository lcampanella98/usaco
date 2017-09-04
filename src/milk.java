/*
ID: enzocam1
LANG: JAVA
PROG: milk
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class milk {

    static class Farmer implements Comparable<Farmer> {
        private int price, units;

        public Farmer(int price, int units) {
            this.price = price;
            this.units = units;
        }

        public int compareTo(Farmer o) {
            if (price > o.price) return 1;
            if (price < o.price) return -1;
            return 0;
        }
    }

    public static void main(String[] args) {


        try (BufferedReader br = new BufferedReader(new FileReader("milk.in"));
             PrintWriter pw = new PrintWriter("milk.out")) {
            int n, m;
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            List<Farmer> farmersList = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                farmersList.add(new Farmer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }
            Collections.sort(farmersList);
            int totalPrice = 0;
            int unitsLeft = n;
            for (Farmer f : farmersList) {
                if (f.units > unitsLeft) {
                    totalPrice += unitsLeft * f.price;
                    unitsLeft = 0;
                } else {
                    totalPrice += f.units * f.price;
                    unitsLeft -= f.units;
                }
                if (unitsLeft == 0) break;
            }
            pw.println(totalPrice);
        } catch (IOException e) {
        }
    }
}

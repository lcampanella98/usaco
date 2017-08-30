/*
ID: enzocam1
LANG: JAVA
PROG: wormhole
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class wormhole {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("wormhole.in"));
             PrintWriter out = new PrintWriter("wormhole.out")) {
            String line;
            line = br.readLine();
            int n = Integer.parseInt(line);
            Set<Wormhole> wormholes = new HashSet<>(n);
            StringTokenizer st;
            for (int i = 0; i < n; i++) {
                line = br.readLine();
                st = new StringTokenizer(line, " ");

                wormholes.add(new Wormhole(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i));
            }
            Set<List<Wormhole>> rows = new HashSet<>();

            for (Wormhole temp : wormholes) {
                boolean added = false;
                for (List<Wormhole> row : rows) {
                    boolean sameRow = true;
                    for (int i = 0; i < row.size(); i++) {
                        Wormhole o = row.get(i);
                        if (temp.y != o.y) {sameRow = false;break;}
                        if (temp.x > o.x) continue;
                        row.add(i, temp);
                        added = true;
                        break;
                    }
                    if (!sameRow) continue;
                    if (added) break;
                    row.add(temp);
                    added = true;
                }
                if (!added) {
                    List<Wormhole> cRow = new ArrayList<>(Collections.singletonList(temp));
                    rows.add(cRow);
                }
            }
            HashMap<Integer, WormholeInfo> wormholeMap = new HashMap<>(n);
            for (List<Wormhole> row : rows) {
                for (int i = 0; i < row.size(); i++) {
                    wormholeMap.put(row.get(i).num, new WormholeInfo(row, i));
                }
            }
            int[] pairsInit = new int[n];
            for (int i = 0; i < n; i++) pairsInit[i] = -1;
            Set<Integer> avail = new HashSet<>();
            for (int i = 0; i < n; i++) avail.add(i);
            formPairs2(pairsInit, n);
            System.out.println(allPairs.size());
            Wormhole start = null, temp = null;
            WormholeInfo info = null;
            Set<Integer> starts = new HashSet<>();
            Set<Integer> ends = new HashSet<>();
            int foundCt = 0;
            for (int[] pairs : allPairs) {
                boolean found = false;
                for (int startHole = 0; startHole < n; startHole++) {
                    info = wormholeMap.get(startHole);
                    start = info.row.get(info.index);
                    temp = start;
                    starts.add(temp.num);
                    while (true) {
                        info = wormholeMap.get(pairs[temp.num]);
                        temp = info.row.get(info.index);
                        if (ends.contains(temp.num)) {
                            found = true;
                            break;
                        }
                        ends.add(temp.num);
                        if (info.index >= info.row.size() - 1) break;
                        temp = info.row.get(info.index + 1);
                        if (starts.contains(temp.num)) {
                            found = true;
                            break;
                        }
                        starts.add(temp.num);
                    }
                    if (found) {
                        foundCt++;
                        break;
                    }
                    starts.clear();
                    ends.clear();
                }
                starts.clear();
                ends.clear();
            }
            out.println(foundCt);
            out.flush();
        } catch (IOException e) {}
    }

    static Set<int[]> allPairs = new HashSet<>();


    static void formPairs2(int[] pairs, int len) {
        boolean hasFreeHole = false;
        for (int i = 0; i < pairs.length - 1; i++) {
            if (pairs[i] >= 0) continue;
            for (int j = i + 1; j < pairs.length; j++) {
                if (pairs[j] >= 0) continue;
                hasFreeHole = true;
                pairs[j] = i;
                pairs[i] = j;
                formPairs2(pairs, len);
                pairs[j] = -1;
            }
            pairs[i] = -1;
            break;
        }
        if (!hasFreeHole) {
            allPairs.add(Arrays.copyOf(pairs, len));
        }
    }

    static class WormholeInfo {
        public WormholeInfo(List<Wormhole> row, int index) {this.row = row;this.index = index;}
        List<Wormhole> row;
        int index;
    }

    static class Wormhole {
        int x, y, num;
        Wormhole(int x, int y, int num) {this.x = x;this.y = y;this.num = num;}

        public String toString() {
            return num + ": " + x + "," + y;
        }
    }

}

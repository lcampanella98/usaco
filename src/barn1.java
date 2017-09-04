/*
ID: enzocam1
LANG: JAVA
PROG: barn1
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class barn1 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("barn1.in"));
             PrintWriter pw = new PrintWriter("barn1.out")) {
            int m, s, c;
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            m = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            boolean[] stalls = new boolean[s];
            for (int i = 0; i < c; i++) {stalls[Integer.parseInt(br.readLine()) - 1] = true;}
            List<Integer> groups = new ArrayList<>(s);
            int leadingGroupLen = 0;
            int i = 0;
            while (!stalls[i] && i < s) {++i;++leadingGroupLen;}
            int trailingGroupLen = 0;
            i = s - 1;
            while (!stalls[i] && i >= 0) {--i;++trailingGroupLen;}
            int groupLen = 0;
            for (i = leadingGroupLen; i < s - trailingGroupLen; i++) {
                if (stalls[i]) {
                    if (groupLen > 0) {
                        groups.add(groupLen);
                        groupLen = 0;
                    }
                } else ++groupLen;
            }
            Collections.sort(groups);
            int totalEmptyStalls = leadingGroupLen + trailingGroupLen;
            for (i = 0; i < m - 1 && groups.size() - i - 1 >= 0; i++) {
                totalEmptyStalls += groups.get(groups.size() - i - 1);
            }
            pw.println(s - totalEmptyStalls);
            pw.flush();
        } catch (IOException e) {
        }
    }
}

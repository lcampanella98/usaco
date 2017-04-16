import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/*
ID: enzocam1
LANG: JAVA
PROG: milk2
 */
public class milk2 {
    static class MilkTime implements Comparable<MilkTime> {
        private int start, end;
        public MilkTime(int start, int end) {this.start = start;this.end= end;}
        public boolean isOverlapping(MilkTime o) {
            return (o.start <= start && o.end >= start)
                    || (o.start <= end && o.end >= end)
                    || (o.start < start && o.end > end)
                    || (start < o.start && end > o.end);
        }
        public void mergeWith(MilkTime o) {
            start = Math.min(start, o.start);
            end = Math.max(end, o.end);
        }
        public int getTimeMilked() {return end - start;}
        public int compareTo(MilkTime o) {
            if (start > o.start) return 1;
            if (start < o.start) return -1;
            return 0;
        }

    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("milk2.in"));
             PrintWriter pw = new PrintWriter("milk2.out")) {
            int n = Integer.parseInt(br.readLine());
            List<MilkTime> milkTimes = new ArrayList<>();
            int maxTimeMilked = 0, maxTimeNotMilked = 0;
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                MilkTime mt = new MilkTime(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                MilkTime cur;
                maxTimeMilked = Math.max(maxTimeMilked, mt.getTimeMilked());

                for (int j = 0; j < milkTimes.size(); j++) {
                    cur = milkTimes.get(j);
                    if (cur.isOverlapping(mt)) {
                        mt.mergeWith(cur);
                        maxTimeMilked = Math.max(maxTimeMilked, mt.getTimeMilked());
                        milkTimes.remove(j--);
                    }
                }
                milkTimes.add(mt);
            }
            Collections.sort(milkTimes);
            MilkTime cur, next;
            for (int i = 0; i < milkTimes.size() - 1; i++) {
                cur = milkTimes.get(i);
                next = milkTimes.get(i + 1);
                maxTimeNotMilked = Math.max(maxTimeNotMilked, next.start - cur.end);
            }
            pw.println(maxTimeMilked + " " + maxTimeNotMilked);
        } catch (IOException e) {
        }
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
ID: enzocam1
LANG: JAVA
PROG: skidesign
 */
public class skidesign {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("skidesign.in"));
             PrintWriter pw = new PrintWriter("skidesign.out")) {
            int n = Integer.parseInt(br.readLine());
            String line;
            int[] hills = new int[n];
            int i = 0;
            while ((line = br.readLine()) != null) {
                hills[i++] = Integer.parseInt(line);
            }
            Arrays.sort(hills);
            int maxDiff = 17, minHeight = hills[0], maxHeight, cost, minCost = Integer.MAX_VALUE;
            while ((maxHeight = minHeight + maxDiff) < hills[n - 1]) {
                i = 0;
                cost = 0;
                while (hills[i] < minHeight) {
                    cost += (minHeight - hills[i]) * (minHeight - hills[i]);
                    i++;
                }
                i = n - 1;
                while (hills[i] > maxHeight) {
                    cost += (hills[i] - maxHeight) * (hills[i] - maxHeight);
                    i--;
                }
                if (cost < minCost) {
                    minCost = cost;
                }
                minHeight++;
            }
            if (minHeight == hills[0]) minCost = 0;


            pw.println(String.valueOf(minCost));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
ID: enzocam1
LANG: JAVA
PROG: friday
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class friday {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("friday.in"));
             PrintWriter pw = new PrintWriter("friday.out")) {
            int n = Integer.parseInt(br.readLine());
            int[] thirteenthByDay = new int[7];
            int date = 1, month = 0, yr = 1900, yrLim = 1900 + n - 1, daysInMonth = 31, dayOfWeek = 2;
            while (yr <= yrLim) {
                if (++date > daysInMonth) {
                    date = 1;
                    if (++month > 11) {
                        month = 0;
                        ++yr;
                    } else if (month == 1) daysInMonth = isLeapYear(yr) ? 29 : 28;
                    else if (month == 8 || month == 3 || month == 5 || month == 10) daysInMonth = 30;
                    else daysInMonth = 31;
                }
                dayOfWeek = (dayOfWeek + 1) % 7;
                if (date == 13) thirteenthByDay[dayOfWeek]++;
            }
            for (int i = 0; i < thirteenthByDay.length; i++) {
                pw.print(thirteenthByDay[i] + (i < thirteenthByDay.length - 1 ? " " : "\n"));
            }
            pw.flush();
        } catch (IOException e) {
        }
    }

    private static boolean isLeapYear(int yr) {
        return yr % 4 == 0 && (yr % 100 != 0 || yr % 400 == 0);
    }
}

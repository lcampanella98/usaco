/*
ID: enzocam1
LANG: JAVA
PROG: namenum
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class namenum {

    private static Map<Character, Integer> charsToDigits;

    public static void main(String[] args) {
        charsToDigits = new HashMap<>(26);
        int num = 2;
        int i = 0;
        for (char c = 'A'; c < 'Z'; c++) {
            if (c=='Q')continue;
            charsToDigits.put(c, num);
            i++;
            if (i % 3 == 0) num++;
        }
        try (BufferedReader br = new BufferedReader(new FileReader("namenum.in"));
             BufferedReader brDict = new BufferedReader(new FileReader("dict.txt"));
             PrintWriter pw = new PrintWriter("namenum.out")) {
            long n = Long.parseLong(br.readLine());
            int nDigits = (int)Math.log10(n) + 1;
            List<String> dict = new ArrayList<>(5000);
            while (brDict.ready()) {
                String name = brDict.readLine();
                if (name.length() == nDigits) dict.add(name);
            }
            boolean gotOne = false;
            for (String name : dict) {
                if (canBeKeypadMapping(name, n)) {
                    pw.println(name);
                    gotOne = true;
                }
            }
            if (!gotOne) pw.println("NONE");
            pw.flush();
        } catch (IOException e) {
        }
    }

    private static boolean canBeKeypadMapping(String s, long n) {
        char[] cArr = s.toCharArray();
        for (int i = cArr.length - 1; i >= 0; i--) {
            if (!charsToDigits.containsKey(cArr[i])) return false;
            if (charsToDigits.get(cArr[i]) != (int)(n%10)) return false;
            n /= 10;
        }
        return true;
    }
}

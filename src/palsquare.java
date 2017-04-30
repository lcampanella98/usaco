import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/*
ID: enzocam1
LANG: JAVA
PROG: palsquare
 */
public class palsquare {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("palsquare.in"));
             PrintWriter pw = new PrintWriter("palsquare.out")) {
            int base = Integer.parseInt(br.readLine());
            int nsq;
            String sqStr;
            for (int n = 1; n <= 300; n++) {
                nsq = n * n;

                sqStr = int10ToBase(nsq, base);
                if (isPalindrome(sqStr)) pw.println(int10ToBase(n, base) + " " + sqStr);
            }
            pw.flush();
        } catch (IOException e) {}
    }

    private static String int10ToBase(int i, int base) {
        int place = 1;
        while (i / place > 0) place *= base;
        place /= base;
        StringBuilder sb = new StringBuilder();
        while (place > 0) {
            int val = i / place;
            char valChar = val < 10 ? Character.forDigit(val, 10) : (char)(((int)'A') + (val - 10));
            sb.append(valChar);
            i %= place;
            place /= base;
        }
        return sb.toString();
    }

    private static boolean isPalindrome(int n, int b) {
        int place = 1;
        while (n / place > 0) place *= b;
        place /= b;
        int[] digits = new int[place];
        int i = 0;
        while (place > 0) {
            digits[i++] = n / place;
            n %= place;
            place /= b;
        }
        for (i = 0; i < digits.length; i++) {
            if (digits[i] != digits[digits.length - i - 1]) return false;
        }
        return true;
    }

    private static boolean isPalindrome(String s) {
        int n = s.length();
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(n - i - 1)) return false;
        }
        return true;
    }
}

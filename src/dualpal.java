import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
ID: enzocam1
LANG: JAVA
PROG: dualpal
 */
public class dualpal {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("dualpal.in"));
             PrintWriter pw = new PrintWriter("dualpal.out")) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            for (int i = s + 1; n > 0; i++) {
                int numBases = 0;
                for (int b = 2; b <= 10; b++) {
                    if (isPalindrome(i, b)) {
                        numBases++;
                        if (numBases == 2) {
                            n--;
                            pw.println(i);
                            break;
                        }
                    }
                }
            }
            pw.flush();
        } catch (IOException e) {
        }

    }

    private static boolean isPalindrome(int n, int b) {
        int place = 1, nDigits = 0;
        while (n / place > 0) {
            place *= b;
            nDigits++;
        }
        place /= b;
        int[] digits = new int[nDigits];
        int i = 0;
        while (place > 0) {
            digits[i++] = n / place;
            n %= place;
            place /= b;
        }
        for (i = 0; i < nDigits; i++) {
            if (digits[i] != digits[nDigits - i - 1]) return false;
        }
        return true;
    }

}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/*
ID: enzocam1
LANG: JAVA
PROG: transform
 */
public class transform {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("transform.in"));
             PrintWriter pw = new PrintWriter("transform.out")) {
            int n = Integer.parseInt(br.readLine());
            //int[][] bef = new int[n][n];
            boolean[][] bef = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                char[] cArr = br.readLine().toCharArray();
                for (int j = 0; j < n; j++) bef[i][j] = cArr[j] == '@';
            }
            //int[][] after = new int[n][n];
            boolean[][] after = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                char[] cArr = br.readLine().toCharArray();
                for (int j = 0; j < n; j++) after[i][j] = cArr[j] == '@';
            }
            if (is90DegRotation(bef, after)) {
                pw.println(1);
            } else if (is180DegRotation(bef, after)) {
                pw.println(2);
            } else if (is270DegRotation(bef, after)) {
                pw.println(3);
            } else if (isReflectedHorizontally(bef, after)) {
                pw.println(4);
            } else {
                boolean[][] cloneBef = cloneArray(bef);
                reflectHorizontally(cloneBef);
                if (is90DegRotation(cloneBef, after) || is180DegRotation(cloneBef, after)
                        || is270DegRotation(cloneBef, after)) {
                    pw.println(5);
                } else if (areEqual(bef, after)) {
                    pw.println(6);
                } else {
                    pw.println(7);
                }
            }
            pw.flush();
        } catch (IOException e) {
        }
    }

    private static boolean[][] cloneArray(boolean[][] a) {
        boolean[][] r = new boolean[a.length][a.length];
        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < r.length; j++) r[i][j] = a[i][j];
        }
        return r;
    }

    private static boolean areEqual(boolean[][] bef, boolean[][] after) {
        int n = bef.length;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (bef[row][col] != after[row][col]) return false;
            }
        }
        return true;
    }

    private static void reflectHorizontally(boolean[][] a) {
        int n = a.length;
        boolean temp;
        for (int col = 0; col < n / 2; col++) {
            for (int row = 0; row < n; row++) {
                temp = a[row][col];
                a[row][col] = a[row][n - col - 1];
                a[row][n - col - 1] = temp;
            }
        }
    }

    private static boolean isReflectedHorizontally(boolean[][] bef, boolean[][] after) {
        int n = bef.length;
        int halfN = n / 2;
        for (int col = 0; col < halfN; col++) {
            for (int row = 0; row < n; row++) {
                if (bef[row][col] != after[row][n - col - 1]) return false;
            }
        }
        if (n % 2 == 1) {
            for (int row = 0; row < n; row++) {
                if (bef[row][halfN] != after[row][halfN]) return false;
            }
        }
        return true;
    }

    private static boolean is270DegRotation(boolean[][] bef, boolean[][] after) {
        return is90DegRotation(after, bef);
    }

    private static boolean is180DegRotation(boolean[][] bef, boolean[][] after) {
        int n = bef.length;
        int halfN = n / 2;
        for (int layer = 0; layer < halfN; layer++) {
            int lim = n - layer;
            int row = layer, col;
            for (col = layer; col < lim; col++) {
                if (bef[row][col] != after[lim - 1][n - col - 1]) return false;
            }
            row = lim - 1;
            for (col = layer; col < lim; col++) {
                if (bef[row][col] != after[layer][n - col - 1]) return false;
            }
            col = layer;
            for (row = layer; row < lim; row++) {
                if (bef[row][col] != after[n - row - 1][lim - 1]) return false;
            }
            col = lim - 1;
            for (row = layer; row < lim; row++) {
                if (bef[row][col] != after[n - row - 1][layer]) return false;
            }
        }
        if (n % 2 == 1) if (bef[halfN][halfN] != after[halfN][halfN]) return false;
        return true;
    }

    private static boolean is90DegRotation(boolean[][] bef, boolean[][] after) {
        int n = bef.length;
        int halfN = n / 2;
        for (int layer = 0; layer < halfN; layer++) {
            int lim = n - layer;
            int row = layer, col;
            for (col = layer; col < lim; col++) {
                if (bef[row][col] != after[col][lim - 1]) {
                    return false;
                }
            }
            row = lim - 1;
            for (col = layer; col < lim; col++) {
                if (bef[row][col] != after[col][layer]) {
                    return false;
                }
            }
            col = layer;
            for (row = layer; row < lim; row++) {
                if (bef[row][col] != after[layer][n - row - 1]) {
                    return false;
                }
            }
            col = lim - 1;
            for (row = layer; row < lim; row++) {
                if (bef[row][col] != after[lim - 1][n - row - 1]) {
                    return false;
                }
            }
        }
        if (n % 2 == 1) if (bef[halfN][halfN] != after[halfN][halfN]) return false;
        return true;
    }
}

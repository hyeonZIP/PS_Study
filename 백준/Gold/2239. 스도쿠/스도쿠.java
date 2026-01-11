import java.io.*;
import java.util.*;

public class Main {
    private static final int MAX = 9;
    private static final char EMPTY = '0';

    private static char[][] map = new char[9][9];

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        dfs(0, 0);
    }

    private static boolean isValidSudokuNumber(int y, int x, int target) {
        char num = Character.forDigit(target, 10);

        for (int i = 0; i < MAX; i++) {
            if (map[y][i] == num) {
                return false;
            }

            if (map[i][x] == num) {
                return false;
            }
        }

        int py = (y / 3) * 3;
        int px = (x / 3) * 3;

        for (int i = py; i <= py + 2; i++) {
            for (int j = px; j <= px + 2; j++) {
                if (map[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean dfs(int y, int x) {
        if (x == MAX) {
            return dfs(y + 1, 0);
        }

        if (y == MAX) {
            return true;
        }

        if (map[y][x] != EMPTY) {
            return dfs(y, x + 1);
        }

        for (int num = 1; num <= MAX; num++) {
            if (isValidSudokuNumber(y, x, num)) {
                map[y][x] = Character.forDigit(num, 10);
                if (dfs(y, x + 1)) {
                    return true;
                }
                map[y][x] = EMPTY;
            }
        }

        return false;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < MAX; i++) {
            map[i] = br.readLine().toCharArray();
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < MAX; i++) {
            bw.write(map[i]);
            bw.write("\n");
        }
        bw.close();
    }
}
import java.io.*;
import java.util.*;

public class Main {
    private static final int[] ddy = new int[] { 0, 1, 0, -1 };
    private static final int[] ddx = new int[] { -1, 0, 1, 0 };

    private static final int[][] dy = new int[][] { // 좌 하 우 상
            { 0, -2, -1, 1, 2, -1, 1, -1, 1, 0 }, // 마지막은 알파
            { 2, 0, 0, 0, 0, 1, 1, -1, -1, 1 },
            { 0, -2, -1, 1, 2, 1, -1, 1, -1, 0 },
            { -2, 0, 0, 0, 0, -1, -1, 1, 1, -1 }
    };
    private static final int[][] dx = new int[][] {
            { -2, 0, 0, 0, 0, -1, -1, 1, 1, -1 },
            { 0, -2, -1, 1, 2, -1, 1, -1, 1, 0 },
            { 2, 0, 0, 0, 0, 1, 1, -1, -1, 1 },
            { 0, 2, 1, -1, -2, -1, 1, -1, 1, 0 }
    };

    private static final int[] rate = new int[] { 5, 2, 7, 7, 2, 10, 10, 1, 1 };

    private static int N, answer, startY, startX;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        startY = N / 2 + 1;
        startX = N / 2 + 1;

        map = new int[N + 1][N + 1];

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }

    private static void sol() {
        int direction = 0;
        int repeatCount = 1;

        while (true) {
            if (spin(repeatCount, direction)) {
                direction = direction + 1 > 3 ? 0 : direction + 1;
                spin(repeatCount, direction);
                direction = direction + 1 > 3 ? 0 : direction + 1;
                repeatCount++;
                continue;
            }
            break;
        }

    }

    private static boolean isOutOfRange(int y, int x) {
        return y <= 0 || x <= 0 || y > N || x > N;
    }

    private static boolean spin(int repeatCount, int direction) {
        for (int i = 0; i < repeatCount; i++) {
            int sandY = startY + ddy[direction];
            int sandX = startX + ddx[direction];
            int sand = map[sandY][sandX];
            int usedSand = 0;
            map[sandY][sandX] = 0;

            for (int j = 0; j < 9; j++) {
                int py = sandY + dy[direction][j];
                int px = sandX + dx[direction][j];
                int calculatedSand = sand * rate[j] / 100;
                usedSand += calculatedSand;

                if (isOutOfRange(py, px)) {
                    answer += calculatedSand;
                } else {
                    map[py][px] += calculatedSand;
                }
            }

            int alphaY = sandY + dy[direction][9];
            int alphaX = sandX + dx[direction][9];
            int alphaSand = sand - usedSand;

            if (isOutOfRange(alphaY, alphaX)) {
                answer += alphaSand;
            } else {
                map[alphaY][alphaX] += alphaSand;
            }

            if (sandY == 1 && sandX == 1) {
                return false;
            } else {
                startY = sandY;
                startX = sandX;
            }
        }

        return true;
    }
}
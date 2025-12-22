import java.io.*;
import java.util.*;

public class Main {
    private static final int[] dy = new int[] { 0, 0, -1, 1 };
    private static final int[] dx = new int[] { -1, 1, 0, 0 };
    private static final int[] cy = new int[] { 1, 1, -1, -1 };
    private static final int[] cx = new int[] { 1, -1, 1, -1 };

    private static int N;
    private static int answer = Integer.MAX_VALUE;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        answer();
    }

    private static void sol() {
        for (int y = 1; y <= N; y++) {
            for (int x = 2; x <= N - 1; x++) {

                for (int d1 = 1; d1 <= N; d1++) {
                    for (int d2 = 1; d2 <= N; d2++) {
                        if (isValidateCoordinate(y, x, d1, d2)) {
                            int[][] boundaryMap = new int[N + 1][N + 1];

                            drawFiveDistrict(boundaryMap, y, x, d1, d2);

                            drawOtherDistrict(boundaryMap, y, x, d1, d2);

                            updateAnswer(boundaryMap);
                        }
                    }
                }
            }
        }
    }

    private static void updateAnswer(int[][] boundaryMap) {
        int[] population = new int[5];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int currentDistrict = boundaryMap[i][j];

                population[currentDistrict - 1] += map[i][j];
            }
        }

        Arrays.sort(population);

        answer = Math.min(answer, population[4] - population[0]);
    }

    private static void drawOtherDistrict(int[][] boundaryMap, int y, int x, int d1, int d2) {
        for (int r = 1; r < y + d1; r++) {
            for (int c = 1; c <= x; c++) {
                if (boundaryMap[r][c] == 5)
                    break;
                boundaryMap[r][c] = 1;
            }
        }

        for (int r = 1; r <= y + d2; r++) {
            for (int c = N; c > x; c--) {
                if (boundaryMap[r][c] == 5)
                    break;
                boundaryMap[r][c] = 2;
            }
        }

        for (int r = y + d1; r <= N; r++) {
            for (int c = 1; c < x - d1 + d2; c++) {
                if (boundaryMap[r][c] == 5)
                    break;
                boundaryMap[r][c] = 3;
            }
        }

        for (int r = y + d2 + 1; r <= N; r++) {
            for (int c = N; c >= x - d1 + d2; c--) {
                if (boundaryMap[r][c] == 5)
                    break;
                boundaryMap[r][c] = 4;
            }
        }
    }

    private static void drawFiveDistrict(int[][] boundaryMap, int y, int x, int d1, int d2) {
        for (int dy = y, dx = x; dy <= y + d1; dy++, dx--) {
            boundaryMap[dy][dx] = 5;
        }

        for (int dy = y, dx = x; dy <= y + d2; dy++, dx++) {
            boundaryMap[dy][dx] = 5;
        }

        for (int dy = y + d1, dx = x - d1; dy <= y + d1 + d2; dy++, dx++) {
            boundaryMap[dy][dx] = 5;
        }

        for (int dy = y + d2, dx = x + d2; dy <= y + d2 + d1; dy++, dx--) {
            boundaryMap[dy][dx] = 5;
        }

        fillFiveDistrict(boundaryMap, y, x);
    }

    private static void fillFiveDistrict(int[][] boundaryMap, int y, int x) {
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 1][N + 1];

        q.offer(new int[] { y + 1, x });
        boundaryMap[y + 1][x] = 5;

        if (isNeedCrossMoving(boundaryMap, y + 1, x)) {
            while (!q.isEmpty()) {
                int[] arr = q.poll();

                for (int i = 0; i < 4; i++) {
                    int py = arr[0] + cy[i];
                    int px = arr[1] + cx[i];

                    if (isOutOfRange(py, px) || visited[py][px] || boundaryMap[py][px] == 5
                            || isOutOfFiveDistrict(boundaryMap, py, px)) {
                        continue;
                    }

                    boundaryMap[py][px] = 5;
                    q.offer(new int[] { py, px });
                    visited[py][px] = true;
                }
            }
        } else {
            while (!q.isEmpty()) {
                int[] arr = q.poll();

                for (int i = 0; i < 4; i++) {
                    int py = arr[0] + dy[i];
                    int px = arr[1] + dx[i];

                    if (isOutOfRange(py, px) || visited[py][px] || boundaryMap[py][px] == 5) {
                        continue;
                    }

                    boundaryMap[py][px] = 5;
                    q.offer(new int[] { py, px });
                    visited[py][px] = true;
                }
            }
        }
    }

    private static boolean isNeedCrossMoving(int[][] boundaryMap, int y, int x) {
        for (int i = 0; i < 4; i++) {
            if (boundaryMap[y + dy[i]][x + dx[i]] != 5) {
                return false;
            }
        }
        return true;
    }

    private static boolean isOutOfFiveDistrict(int[][] boundaryMap, int y, int x) {
        for (int i = 0; i < 4; i++) {
            int py = y + dy[i];
            int px = x + dx[i];

            if (isOutOfRange(py, px) || boundaryMap[py][px] != 5) {
                return true;
            }
        }

        return false;
    }

    private static boolean isOutOfRange(int y, int x) {
        return y <= 0 || x <= 0 || y > N || x > N;
    }

    private static void test(int[][] boundaryMap) {
        for (int i = 0; i <= N; i++) {
            System.out.println(Arrays.toString(boundaryMap[i]));
        }

        System.out.println("-----------------------");
    }

    private static boolean isValidateCoordinate(int y, int x, int d1, int d2) {
        return y < y + d1 + d2 && y + d1 + d2 <= N && x - d1 >= 1 && x - d1 < x && x < x + d2 && x + d2 <= N;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];

        StringTokenizer st;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void answer() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}
import java.io.*;
import java.util.*;

public class Main {
    static final int[] dy = new int[] { -1, 0, 1, 0 };// 북 동 남 서
    static final int[] dx = new int[] { 0, 1, 0, -1 };// 0 1 2 3
    static final int EMPTY = 0, WALL = 1;
    static int answer = -1;
    static int[][] map, prefixSum;
    static int N, M, H, W, startY, startX, endY, endX;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        init();
        sol();
        print();
    }

    static void sol() {
        if (startY == endY && startX == endX) {
            answer = 0;
            return;
        }
        bfs();
    }

    static void bfs() {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { startY, startX, 0 });
        visited[startY][startX] = true;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int y = current[0];
            int x = current[1];
            int count = current[2];

            for (int i = 0; i < 4; i++) {
                int py = y + dy[i];
                int px = x + dx[i];

                int ppy = py + H - 1;
                int ppx = px + W - 1;

                if (isOutOfRange(py, px) || isOutOfRange(ppy, ppx) || visited[py][px] || isWall(py, px)) {
                    continue;
                }

                visited[py][px] = true;

                if (containsWall(py, px, ppy, ppx)) {
                    continue;
                }

                if (py == endY && px == endX) {
                    answer = count + 1;
                    return;
                }

                q.offer(new int[] { py, px, count + 1 });
            }
        }
    }

    static boolean containsWall(int fromY, int fromX, int toY, int toX) {
        return prefixSum[toY][toX]
                - prefixSum[fromY - 1][toX]
                - prefixSum[toY][fromX - 1]
                + prefixSum[fromY - 1][fromX - 1] > 0;
    }

    static boolean isWall(int y, int x) {
        return map[y][x] == WALL;
    }

    static boolean isOutOfRange(int y, int x) {
        return y <= 0 || x <= 0 || y > N || x > M;
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());
        startX = Integer.parseInt(st.nextToken());
        endY = Integer.parseInt(st.nextToken());
        endX = Integer.parseInt(st.nextToken());

        initPrefixSum();
    }

    static void initPrefixSum() {
        prefixSum = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                prefixSum[i][j] = map[i][j] + prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1];
            }
        }
    }

    static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }

}

import java.util.*;
import java.io.*;

public class Main {

    static final char WALL = '1', EMPTY = '0';
    static int N, M, K;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static char[][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        init();

        System.out.println(bfs());
    }//main

    private static int bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{0, 0, K, 0});
        visited[K][0][0] = true;

        while (!q.isEmpty()) {
            int[] arr = q.poll();
            int y = arr[0];
            int x = arr[1];
            int bombCount = arr[2];
            int count = arr[3];

            if (y == N - 1 && x == M - 1) {
                return count+1;
            }

            for (int i = 0; i < 4; i++) {
                int py = y + dy[i];
                int px = x + dx[i];

                if (isOutOfRange(py, px)) continue;
                if (visited[bombCount][py][px]) continue;
                if (map[py][px] == WALL && bombCount > 0) {
                    q.offer(new int[]{py, px, bombCount - 1, count + 1});
                    visited[bombCount][py][px] = true;
                } else if (map[py][px] == EMPTY) {
                    q.offer(new int[]{py, px, bombCount, count + 1});
                    visited[bombCount][py][px] = true;
                }
            }
        }
        return -1;
    }

    private static boolean isOutOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= M;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[K + 1][N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
    }
}

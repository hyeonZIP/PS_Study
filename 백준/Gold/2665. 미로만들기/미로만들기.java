import java.io.*;
import java.util.*;

public class Main {
    private static final int[] dy = { 0, 1, -1, 0 };
    private static final int[] dx = { 1, 0, 0, -1 };

    private static int n;
    private static int answer;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        init();

        sol();

        print();
    }

    private static void sol() {
        bfs_0_1();
    }

    private static void bfs_0_1() {
        Deque<int[]> dq = new ArrayDeque<>();
        int[][] dist = new int[n][n];
        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dq.offer(new int[] { 0, 0 });
        dist[0][0] = 0;
        visited[0][0] = true;

        while (!dq.isEmpty()) {
            int[] arr = dq.poll();
            int y = arr[0];
            int x = arr[1];

            for (int i = 0; i < 4; i++) {
                int py = y + dy[i];
                int px = x + dx[i];

                if (isOutOfRange(py, px) || visited[py][px]) {
                    continue;
                }

                if (dist[py][px] >= dist[y][x] + map[py][px]) {
                    dist[py][px] = dist[y][x] + map[py][px];
                    if (map[py][px] == 1) {
                        dq.addLast(new int[] { py, px });
                    } else {
                        dq.addFirst(new int[] { py, px });
                    }
                    visited[py][px] = true;
                }
            }
        }

        answer = dist[n - 1][n - 1];
    }

    private static boolean isOutOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= n || x >= n;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split("");

            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(s[j]) == 1 ? 0 : 1;
            }
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(String.valueOf(answer));
        bw.close();
    }
}

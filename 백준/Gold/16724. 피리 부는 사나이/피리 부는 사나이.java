import java.io.*;
import java.util.*;

public class Main {
    private static final int[] dy = new int[] { 0, 0, -1, 1 };
    private static final int[] dx = new int[] { -1, 1, 0, 0 };
    private static int answer = 0;
    private static int N, M;
    private static String[][] map;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) {
                    continue;
                }

                dfs(visited, i, j);
                answer++;
            }
        }
    }

    private static void dfs(boolean[][] visited, int y, int x) {
        visited[y][x] = true;

        Deque<int[]> s = new ArrayDeque<>();

        s.push(new int[] { y, x });

        while (!s.isEmpty()) {
            int[] arr = s.pop();

            // 거꾸로 온 곳 탐색
            for (int i = 0; i < 4; i++) {
                int py = arr[0] + dy[i];
                int px = arr[1] + dx[i];

                if (isOutOfRange(py, px) || visited[py][px]) {
                    continue;
                }

                if (isFromTile(py, px, arr[0], arr[1])) {
                    visited[py][px] = true;
                    s.push(new int[] { py, px });
                }
            }

            // 현재 타일에 적힌 방향대로 진행
            int[] direction = getDirection(arr[0], arr[1]);

            int py = arr[0] + direction[0];
            int px = arr[1] + direction[1];

            if (visited[py][px]) {
                continue;
            }

            s.push(new int[] { py, px });
            visited[py][px] = true;
        }
    }

    private static boolean isOutOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= M;
    }

    private static int[] getDirection(int y, int x) {
        String direction = map[y][x];

        switch (direction) {
            case "D":
                return new int[] { 1, 0 };
            case "U":
                return new int[] { -1, 0 };
            case "R":
                return new int[] { 0, 1 };
            case "L":
                return new int[] { 0, -1 };
            default:
                return new int[] { -1, -1 };
        }
    }

    private static boolean isFromTile(int py, int px, int y, int x) {
        int[] direction = getDirection(py, px);

        return y == py + direction[0] && x == px + direction[1];
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new String[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().split("");
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}
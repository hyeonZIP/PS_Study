import java.io.*;
import java.util.*;

public class Main {
    private static final int dy[] = new int[] { 0, 0, 1, -1, 0, 0 };
    private static final int dx[] = new int[] { 1, -1, 0, 0, 0, 0 };
    private static final int dz[] = new int[] { 0, 0, 0, 0, 1, -1 };
    private static int[][][] map = new int[5][5][5];
    private static int[][][] tempMap = new int[5][5][5];
    private static boolean[] used = new boolean[5];
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();

        sol();

        answer();
    }

    private static void sol() {
        combine(0);
    }

    private static void combine(int depth) {
        if (depth == 5) {
            answer = Math.min(answer, bfs());
            return;
        }

        for (int i = 0; i < 5; i++) {
            if (used[i]) {
                continue;
            }

            used[i] = true;

            int[][] original = Arrays.stream(map[i]).map(int[]::clone).toArray(int[][]::new);

            for (int j = 0; j < 4; j++) {
                tempMap[depth] = original;
                combine(depth + 1);
                original = rotate90Degree(original);
            }

            used[i] = false;
        }
    }

    private static int bfs() {
        if (tempMap[0][0][0] == 0 || tempMap[4][4][4] == 0) {
            return Integer.MAX_VALUE;
        }

        Deque<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[5][5][5];

        q.offer(new int[] { 0, 0, 0, 0 });
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            int[] arr = q.poll();

            int z = arr[0];
            int y = arr[1];
            int x = arr[2];
            int count = arr[3];

            for (int i = 0; i < 6; i++) {
                int pz = z + dz[i];
                int py = y + dy[i];
                int px = x + dx[i];

                if (isOutOfRange(pz, py, px) || visited[pz][py][px] || tempMap[pz][py][px] == 0) {
                    continue;
                }

                if (pz == 4 && py == 4 && px == 4) {
                    return count + 1;
                }

                visited[pz][py][px] = true;
                q.offer(new int[] { pz, py, px, count + 1 });
            }
        }

        return Integer.MAX_VALUE;
    }

    private static boolean isOutOfRange(int z, int y, int x) {
        return z < 0 || y < 0 || x < 0 || z >= 5 || y >= 5 || x >= 5;
    }

    private static int[][] rotate90Degree(int[][] singleMap) {
        int[][] newSingleMap = new int[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                newSingleMap[i][j] = singleMap[4 - j][i];
            }
        }

        return newSingleMap;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
        }
    }

    private static void answer() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        bw.write(String.valueOf(answer));
        bw.close();
    }
}
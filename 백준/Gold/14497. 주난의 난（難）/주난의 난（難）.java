import java.io.*;
import java.util.*;

public class Main {
    private static final String EMPTY = "0";
    private static final String STUDENT = "1";
    private static final String TARGET = "#";
    private static final String START = "*";
    private static final int[] dy = new int[] { 0, 0, -1, 1 };
    private static final int[] dx = new int[] { -1, 1, 0, 0 };

    private static int N, M;
    private static int[] startPos = new int[2];
    private static int[] targetPos = new int[2];
    private static String[][] map;
    private static int answer;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        answer();
    }

    private static void test() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-------------------");
    }

    private static void sol() {
        // int count = 1;
        // while (true) {
        // // test();
        // String[][] copiedMap = copyMap();

        // if (bfs(copiedMap) == 1) {
        // answer = count;
        // return;
        // }

        // count++;
        // }
        answer = zeroOneBFS();
    }

    private static String[][] copyMap() {
        String[][] copiedMap = new String[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copiedMap[i][j] = map[i][j];
            }
        }

        return copiedMap;
    }

    private static int zeroOneBFS() {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { startPos[0], startPos[1], 1 });

        boolean[][] visited = new boolean[N][M];
        visited[startPos[0]][startPos[1]] = true;

        while (!q.isEmpty()) {
            int[] arr = q.poll();

            int y = arr[0];
            int x = arr[1];
            int count = arr[2];

            for (int i = 0; i < 4; i++) {
                int py = y + dy[i];
                int px = x + dx[i];

                if (isOutOfRange(py, px) || visited[py][px]) {
                    continue;
                }
                visited[py][px] = true;
                if (map[py][px].equals(EMPTY)) {
                    q.addFirst(new int[] { py, px, count });
                    continue;
                }

                if (map[py][px].equals(STUDENT)) {
                    q.addLast(new int[] { py, px, count + 1 });
                    continue;
                }

                if (map[py][px].equals(TARGET)) {
                    return count;
                }
            }
        }

        return 0;
    }

    private static int bfs(String[][] copiedMap) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(startPos);

        boolean[][] visited = new boolean[N][M];
        visited[startPos[0]][startPos[1]] = true;

        while (!q.isEmpty()) {
            int[] arr = q.poll();

            int y = arr[0];
            int x = arr[1];

            for (int i = 0; i < 4; i++) {
                int py = y + dy[i];
                int px = x + dx[i];

                if (isOutOfRange(py, px) || visited[py][px]) {
                    continue;
                }
                visited[py][px] = true;
                if (map[py][px].equals(EMPTY)) {
                    q.offer(new int[] { py, px });
                    continue;
                }

                if (map[py][px].equals(STUDENT)) {
                    copiedMap[py][px] = EMPTY;
                    continue;
                }

                if (map[py][px].equals(TARGET)) {
                    return 1;
                }
            }
        }

        map = copiedMap;

        return 0;
    }

    private static boolean isOutOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= M;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new String[N][M];

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        startPos[0] = input[0] - 1;
        startPos[1] = input[1] - 1;
        targetPos[0] = input[2] - 1;
        targetPos[1] = input[3] - 1;

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().split("");
        }
    }

    private static void answer() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}
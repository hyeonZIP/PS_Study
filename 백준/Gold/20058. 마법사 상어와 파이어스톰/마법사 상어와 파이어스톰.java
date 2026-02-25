import java.io.*;
import java.util.*;

public class Main {
    static final int[] dy = new int[] { 0, 0, -1, 1 };
    static final int[] dx = new int[] { -1, 1, 0, 0 };
    static StringBuilder answer = new StringBuilder();
    static int N, Q;
    static int[] L;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    static void sol() {
        for (int magicRange : L) {
            rotate90Degree(magicRange);
            removeIce();
        }

        calculateIceAmount();
        calculateMaxIceSize();
    }

    static void calculateMaxIceSize() {
        int length = map.length;
        boolean[][] visited = new boolean[length][length];

        int maxIceSize = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (!visited[i][j] && map[i][j] > 0) {
                    maxIceSize = Math.max(maxIceSize, dfs(i, j, visited));
                }
            }
        }

        answer.append(maxIceSize);
    }

    static int dfs(int startY, int startX, boolean[][] visited) {
        Deque<int[]> stack = new ArrayDeque<>();
        visited[startY][startX] = true;
        stack.push(new int[] { startY, startX });
        int currentIceSize = 1;

        while (!stack.isEmpty()) {
            int[] arr = stack.pop();

            int y = arr[0];
            int x = arr[1];

            for (int i = 0; i < 4; i++) {
                int py = y + dy[i];
                int px = x + dx[i];

                if (isOutOfRange(py, px) || visited[py][px]) {
                    continue;
                }

                if (map[py][px] > 0) {
                    currentIceSize++;
                    visited[py][px] = true;
                    stack.push(new int[] { py, px });
                }
            }
        }

        return currentIceSize;
    }

    static void calculateIceAmount() {
        int length = map.length;
        int iceAmount = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (map[i][j] > 0) {
                    iceAmount += map[i][j];
                }
            }
        }

        answer.append(iceAmount).append("\n");
    }

    static void rotate90Degree(int magicRange) {
        int length = map.length;
        int[][] newMap = new int[length][length];

        int magicSize = 1;
        for (int i = 0; i < magicRange; i++) {
            magicSize *= 2;
        }

        for (int i = 0; i < length; i += magicSize) {
            for (int j = 0; j < length; j += magicSize) {
                for (int y = 0; y < magicSize; y++) {
                    for (int x = 0; x < magicSize; x++) {
                        newMap[i + y][j + x] = map[magicSize - 1 + i - x][j + y];
                    }
                }
            }
        }

        map = newMap;
    }

    static void removeIce() {
        int length = map.length;
        int[][] minusMap = new int[length][length];

        for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
                int surrounedIceCount = 0;

                for (int i = 0; i < 4; i++) {
                    int py = y + dy[i];
                    int px = x + dx[i];

                    if (isOutOfRange(py, px)) {
                        continue;
                    }

                    if (map[py][px] > 0) {
                        surrounedIceCount++;
                    }
                }

                if (surrounedIceCount < 3) {
                    minusMap[y][x]++;
                }
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                map[i][j] -= minusMap[i][j];
            }
        }
    }

    static boolean isOutOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= map.length || x >= map.length;
    }

    static void test(int[][] map) {
        for (int[] a : map) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println("---------");
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());// 2의 N승 격자 크기
        Q = Integer.parseInt(st.nextToken());// 마술 시전 횟수

        int length = 1;
        for (int i = 0; i < N; i++) {
            length *= 2;
        }

        map = new int[length][length];

        for (int i = 0; i < length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < length; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        L = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();// 마술 크기 2의 L승
    }

    static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}
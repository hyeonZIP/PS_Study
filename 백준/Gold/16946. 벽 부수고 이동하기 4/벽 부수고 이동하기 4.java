import java.io.*;
import java.util.*;

public class Main {
    private static final int WALL = 1;
    private static final int EMPTY = 0;
    private static final int[] dy = new int[] { 0, 0, -1, 1 };
    private static final int[] dx = new int[] { -1, 1, 0, 0 };
    private static int N, M;
    private static int[][] map, answer, idMap;
    private static Map<Integer, Integer> idCount = new HashMap<>();

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        int id = 1;
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (isEmpty(i, j) && !visited[i][j]) {
                    answer[i][j] = EMPTY;

                    int count = dfs(i, j, id, visited);
                    idCount.put(id, count);
                    id++;
                    continue;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (isWall(i, j)) {
                    Set<Integer> ids = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int py = i + dy[k];
                        int px = j + dx[k];

                        if (isOutOfRange(py, px)) {
                            continue;
                        }
                        ids.add(idMap[py][px]);
                    }

                    answer[i][j]++;
                    for (int k : ids) {
                        answer[i][j] = (answer[i][j] + idCount.getOrDefault(k, 0)) % 10;
                    }
                }
            }
        }
    }

    private static int dfs(int startY, int startX, int id, boolean[][] visited) {
        Deque<int[]> s = new ArrayDeque<>();

        s.push(new int[] { startY, startX });

        visited[startY][startX] = true;
        idMap[startY][startX] = id;

        int count = 1;
        while (!s.isEmpty()) {
            int[] arr = s.pop();

            for (int i = 0; i < 4; i++) {
                int py = arr[0] + dy[i];
                int px = arr[1] + dx[i];

                if (isOutOfRange(py, px) || visited[py][px] || map[py][px] == WALL) {
                    continue;
                }

                s.push(new int[] { py, px });
                visited[py][px] = true;
                idMap[py][px] = id;
                count++;
            }
        }

        return count;
    }

    private static boolean isWall(int y, int x) {
        return map[y][x] == WALL;
    }

    private static boolean isOutOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= M;
    }

    private static boolean isEmpty(int y, int x) {
        return map[y][x] == EMPTY;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        answer = new int[N][M];
        idMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                bw.write(String.valueOf(answer[i][j]));
            }
            bw.write("\n");
        }
        bw.close();
    }

    private static void test() {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(idMap[i]));
        }
        System.out.println("----");

        for (Map.Entry<Integer, Integer> entry : idCount.entrySet()) {
            System.out.println("key : " + entry.getKey());
            System.out.println("value : " + entry.getValue());
        }
    }
}
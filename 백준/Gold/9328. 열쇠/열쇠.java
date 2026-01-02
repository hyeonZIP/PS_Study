import java.io.*;
import java.util.*;

public class Main {
    private static final int[] dy = new int[] { 0, 0, -1, 1 };
    private static final int[] dx = new int[] { -1, 1, 0, 0 };
    private static final char WALL = '*';
    private static final char EMPTY = '.';
    private static final char TARGET = '$';

    private static StringBuilder answer = new StringBuilder();
    private static int testcase;
    private static char[][][] maps;
    private static Set<Character>[] keys;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        for (int t = 0; t < testcase; t++) {
            bfs(maps[t], keys[t]);
        }
    }

    private static void bfs(char[][] map, Set<Character> key) {
        int h = map.length;
        int w = map[0].length;
        boolean[][] visited = new boolean[h][w];
        Deque<int[]> q = findStartPosition(map, visited);

        List<int[]>[] lockedDoors = initLockedDoors();

        int result = 0;

        while (!q.isEmpty()) {
            int[] arr = q.poll();

            int y = arr[0];
            int x = arr[1];

            char current = map[y][x];

            if (Character.isUpperCase(current) && !key.contains(Character.toLowerCase(current))) {
                // 키를 가지고 있지 않은 문인 경우
                lockedDoors[current - 'A'].add(new int[] { y, x });
                continue;
            }

            if (Character.isLowerCase(current) && !key.contains(current)) {
                // 새로운 키를 얻은 경우
                key.add(current);
                for (int[] ary : lockedDoors[current - 'a']) {
                    q.offer(ary);
                }
            } else if (current == TARGET) {
                result++;
            }

            for (int i = 0; i < 4; i++) {
                int py = y + dy[i];
                int px = x + dx[i];

                if (isOutOfRange(py, px, h, w) || visited[py][px] || map[py][px] == WALL) {
                    continue;
                }

                q.offer(new int[] { py, px });
                visited[py][px] = true;
            }
        }

        answer.append(result).append("\n");
    }

    private static List<int[]>[] initLockedDoors() {
        List<int[]>[] lockedDoors = new ArrayList[26];

        for (int i = 0; i < 26; i++) {
            lockedDoors[i] = new ArrayList<>();
        }

        return lockedDoors;
    }

    private static boolean isOutOfRange(int y, int x, int h, int w) {
        return y < 0 || x < 0 || y >= h || x >= w;
    }

    // 탐색 시작 좌표 반환
    private static Deque<int[]> findStartPosition(char[][] map, boolean[][] visited) {
        Deque<int[]> q = new ArrayDeque<>();

        int h = map.length;
        int w = map[0].length;

        // 모서리에 열쇠 존재 가능성 염두
        for (int i = 0; i < w; i++) {
            if (map[0][i] != WALL) {
                q.offer(new int[] { 0, i });
                visited[0][i] = true;
            }
            if (map[h - 1][i] != WALL) {
                q.offer(new int[] { h - 1, i });
                visited[h - 1][i] = true;
            }
        }

        for (int i = 1; i < h - 1; i++) {
            if (map[i][0] != WALL) {
                q.offer(new int[] { i, 0 });
                visited[i][0] = true;
            }
            if (map[i][w - 1] != WALL) {
                q.offer(new int[] { i, w - 1 });
                visited[i][w - 1] = true;
            }
        }

        return q;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        testcase = Integer.parseInt(br.readLine());

        StringTokenizer st;

        maps = new char[testcase][][];
        keys = new HashSet[testcase];

        for (int i = 0; i < testcase; i++) {
            st = new StringTokenizer(br.readLine());

            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            maps[i] = new char[h][w];
            keys[i] = new HashSet<>();

            for (int j = 0; j < h; j++) {
                maps[i][j] = br.readLine().toCharArray();
            }

            for (char c : br.readLine().toCharArray()) {
                keys[i].add(c);
            }
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(answer.toString());
        bw.close();
    }
}
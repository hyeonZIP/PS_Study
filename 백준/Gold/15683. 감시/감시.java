import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static public class CCTV {
        int y;
        int x;
        int type;

        public CCTV(int y, int x, int type) {
            this.y = y;
            this.x = x;
            this.type = type;
        }
    }

    static final int WALL = 6;
    static final int EMPTY = 0;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int N, M, blindSpot, count, max = Integer.MIN_VALUE;
    static int[][] map;
    static ArrayList<CCTV> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();

        dfs(0, new boolean[N][M]);

        System.out.println(blindSpot - max);
    }

    private static void dfs(int depth, boolean[][] visited) {

        if (depth == list.size()) {
            max = Math.max(count, max);
            return;
        }

        CCTV cctv = list.get(depth);
        int y = cctv.y;
        int x = cctv.x;
        int type = cctv.type;

        switch (type) {
            case 1:
                for (int i = 0; i < 4; i++) {
                    ArrayList<int[]> detectedArea = detect(y, x, i, visited);
                    markingVisited(detectedArea, visited, true);
                    count += detectedArea.size();

                    dfs(depth + 1, visited);

                    markingVisited(detectedArea, visited, false);
                    count -= detectedArea.size();
                }
                break;
            case 2:
                for (int i = 0; i < 2; i++) {
                    ArrayList<int[]> detectedArea = detect(y, x, i, visited);
                    detectedArea.addAll(detect(y, x, i + 2, visited));

                    markingVisited(detectedArea, visited, true);
                    count += detectedArea.size();

                    dfs(depth + 1, visited);

                    markingVisited(detectedArea, visited, false);
                    count -= detectedArea.size();
                }
                break;
            case 3:
                for (int i = 0; i < 4; i++) {
                    ArrayList<int[]> detectedArea = detect(y, x, i, visited);
                    if (i + 1 == 4) {
                        detectedArea.addAll(detect(y, x, 0, visited));
                    } else {
                        detectedArea.addAll(detect(y, x, i + 1, visited));
                    }

                    markingVisited(detectedArea, visited, true);
                    count += detectedArea.size();

                    dfs(depth + 1, visited);

                    markingVisited(detectedArea, visited, false);
                    count -= detectedArea.size();
                }
                break;
            case 4:
                for (int i = 0; i < 4; i++) {
                    ArrayList<int[]> detectedArea = detect(y, x, i, visited);
                    if (i - 1 == -1) {
                        detectedArea.addAll(detect(y, x, 3, visited));
                        detectedArea.addAll(detect(y, x, i + 1, visited));
                    } else if (i + 1 == 4) {
                        detectedArea.addAll(detect(y, x, 0, visited));
                        detectedArea.addAll(detect(y, x, i - 1, visited));
                    } else {
                        detectedArea.addAll(detect(y, x, i + 1, visited));
                        detectedArea.addAll(detect(y, x, i - 1, visited));
                    }

                    markingVisited(detectedArea, visited, true);
                    count += detectedArea.size();

                    dfs(depth + 1, visited);

                    markingVisited(detectedArea, visited, false);
                    count -= detectedArea.size();
                }
                break;
            case 5:
                ArrayList<int[]> detectedArea = detect(y, x, 0, visited);
                detectedArea.addAll(detect(y, x, 1, visited));
                detectedArea.addAll(detect(y, x, 2, visited));
                detectedArea.addAll(detect(y, x, 3, visited));

                markingVisited(detectedArea, visited, true);
                count += detectedArea.size();

                dfs(depth + 1, visited);

                markingVisited(detectedArea, visited, false);
                count -= detectedArea.size();
                break;
        }//switch
    }

    private static void markingVisited(ArrayList<int[]> detectedArea, boolean[][] visited, boolean flag) {
        for (int[] arr : detectedArea) {
            visited[arr[0]][arr[1]] = flag;
        }
    }

    private static ArrayList<int[]> detect(int y, int x, int direction, boolean[][] visited) {
        ArrayList<int[]> l = new ArrayList<>();
        while (true) {
            y += dy[direction];
            x += dx[direction];
            if (isOutOfRange(y, x)) return l;
            if (map[y][x] == WALL) return l;
            if (visited[y][x]) continue;

            if (map[y][x] == EMPTY) {
                l.add(new int[]{y, x});
            }
        }
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

        blindSpot = N * M;
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int input = Integer.parseInt(st.nextToken());
                map[i][j] = input;
                if (input == 6) {
                    blindSpot--;
                    continue;
                }
                if (input > 0 && input < 6) {
                    blindSpot--;
                    list.add(new CCTV(i, j, input));
                }
            }
        }
    }
}

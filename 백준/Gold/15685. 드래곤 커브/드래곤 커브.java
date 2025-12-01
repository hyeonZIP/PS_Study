import java.io.*;
import java.util.*;

public class Main {
    private static final int[] dy = new int[] { 0, -1, 0, 1 };
    private static final int[] dx = new int[] { 1, 0, -1, 0 };

    private static int N;
    private static int[][] testCases;
    private static int answer;
    private static boolean[][] map = new boolean[101][101];

    public static void main(String[] args) throws IOException {
        init();

        sol();

        answer();
    }

    private static void sol() {
        for (int[] testCase : testCases) {
            int x = testCase[0];
            int y = testCase[1];
            int direction = testCase[2];
            int generation = testCase[3];

            List<Integer> drgaonCurveDirections = getDragonCurveDirections(direction, generation);
            drawDragonCurve(drgaonCurveDirections, y, x);
        }

        checkSquare();
    }

    private static void checkSquare() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
                    answer++;
                }
            }
        }
    }

    private static void drawDragonCurve(List<Integer> drgaonCurveDirections, int y, int x) {
        map[y][x] = true;

        for (int direction : drgaonCurveDirections) {
            y += dy[direction];
            x += dx[direction];

            map[y][x] = true;
        }
    }

    private static List<Integer> getDragonCurveDirections(int d, int g) {
        List<Integer> directions = new ArrayList<>();

        // 0세대
        directions.add(d);

        // 1세대 ~ g세대
        for (int i = 0; i < g; i++) {
            for (int j = directions.size() - 1; j >= 0; j--) {
                directions.add((directions.get(j) + 1) % 4);
            }
        }

        return directions;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        testCases = new int[N][4];

        for (int i = 0; i < N; i++) {
            testCases[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }

    private static void answer() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}
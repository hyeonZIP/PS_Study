import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, L, answer;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        init();

        sol();

        answer();
    }

    private static void sol() {
        rowCheck();
        // System.out.println("answer : " + answer);
        colCheck();
        // System.out.println("answer : " + answer);
    }

    private static void rowCheck() {
        for (int i = 0; i < N; i++) {
            int previous = 0;
            int flatCount = 1;
            boolean[] used = new boolean[N];
            boolean flag = true;

            for (int j = 0; j < N; j++) {
                if (previous == 0) {
                    previous = map[i][j];
                    continue;
                }

                int current = map[i][j];

                if (previous == current) {
                    flatCount++;
                    continue;
                }

                if (Math.abs(previous - current) >= 2) {
                    flag = false;
                    break;
                }

                // 올라가는 경사로
                // 올라가기 위해서는 이전에 칸에서 경사로가 없어야 한다 !used[j - 1]
                if (previous < current && flatCount >= L && !used[j - 1]) {
                    flatCount = 1;
                    previous = current;
                    continue;
                }

                // 내려가는 경사로
                // 내려가기 위해서는 이전칸의 경사로는 상관없다
                if (previous > current && checkRowFlatCount(i, j)) {
                    flatCount = 0;
                    previous = current;
                    j += L - 1;
                    used[j] = true;
                    continue;
                }

                flag = false;
                break;
            }

            if (flag) {
                answer++;
            }
        }
    }

    private static boolean checkRowFlatCount(int y, int x) {
        if (x + L - 1 < N) {
            int previous = map[y][x];
            int flatCount = 1;
            for (int i = x + 1; i < x + L; i++) {
                int current = map[y][i];

                if (previous == current) {
                    flatCount++;
                    continue;
                }

                if (previous != current) {
                    return false;
                }
            }

            if (flatCount == L) {
                return true;
            }
        }
        return false;
    }

    private static void colCheck() {
        for (int i = 0; i < N; i++) {
            int previous = 0;
            int flatCount = 1;
            boolean[] used = new boolean[N];
            boolean flag = true;

            for (int j = 0; j < N; j++) {
                if (previous == 0) {
                    previous = map[j][i];
                    continue;
                }

                int current = map[j][i];

                if (Math.abs(previous - current) >= 2) {
                    flag = false;
                    break;
                }

                if (previous == current) {
                    flatCount++;
                    continue;
                }

                if (previous < current && flatCount >= L && !used[j - 1]) {
                    flatCount = 1;
                    previous = current;
                    continue;
                }

                if (previous > current && checkColFlatCount(j, i)) {
                    flatCount = 0;
                    previous = current;
                    used[j] = true;
                    j += L - 1;
                    continue;
                }

                flag = false;
                break;
            }

            if (flag) {
                answer++;
            }
        }
    }

    private static boolean checkColFlatCount(int y, int x) {
        if (y + L - 1 < N) {
            int previous = map[y][x];
            int flatCount = 1;
            for (int i = y + 1; i < y + L; i++) {
                int current = map[i][x];

                if (previous == current) {
                    flatCount++;
                    continue;
                }

                if (previous != current) {
                    return false;
                }
            }

            if (flatCount == L) {
                return true;
            }
        }
        return false;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }

    private static void answer() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}
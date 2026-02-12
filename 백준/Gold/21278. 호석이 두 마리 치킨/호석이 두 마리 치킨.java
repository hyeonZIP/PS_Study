import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder answer = new StringBuilder();
    private static int N, M;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    map[j][k] = Math.min(map[j][k], map[j][i] + map[i][k]);
                }
            }
        }

        int chicken1 = 0, chicken2 = 0;
        int minDist = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                int currentDist = 0;

                for (int k = 1; k <= N; k++) {
                    int dist = Math.min(map[k][i], map[k][j]);
                    currentDist += dist * 2;
                }

                if (minDist > currentDist) {
                    minDist = currentDist;
                    chicken1 = i;
                    chicken2 = j;
                }
            }
        }

        answer.append(chicken1).append(" ").append(chicken2).append(" ").append(minDist);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if (i == j) {
                    map[i][j] = 0;
                    continue;
                }

                map[i][j] = Integer.MAX_VALUE / 2;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            map[A][B] = 1;
            map[B][A] = 1;
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}
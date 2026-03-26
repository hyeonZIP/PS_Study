import java.io.*;
import java.util.*;

public class Main {

    static int answer;
    static int T, W;
    static int[] input;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    static void sol() {
        int[][] dp = new int[T + 1][W + 1];
        for (int time = 1; time <= T; time++) {
            int pointTree = input[time];

            for (int moveingCount = 0; moveingCount <= time && moveingCount <= W; moveingCount++) {
                if (moveingCount == 0) {
                    dp[time][0] = dp[time - 1][0] + (pointTree == 1 ? 1 : 0);
                    continue;
                }

                int currentTree = moveingCount % 2 == 0 ? 1 : 2;
                int score = pointTree == currentTree ? 1 : 0;

                dp[time][moveingCount] = Math.max(dp[time - 1][moveingCount], dp[time - 1][moveingCount - 1]) + score;
            }
        }

        for (int movingCount = 0; movingCount <= W; movingCount++) {
            answer = Math.max(answer, dp[T][movingCount]);
        }
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        input = new int[T + 1];

        for (int i = 1; i <= T; i++) {
            int num = Integer.parseInt(br.readLine());

            input[i] = num;
        }
    }

    static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}
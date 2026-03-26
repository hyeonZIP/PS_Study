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
        int[][][] dp = new int[T + 1][W + 1][3];
        // 1초에서 0번 움직였을 때, 1번 나무에서의 시작값
        for (int time = 1; time <= T; time++) {
            int pointTree = input[time];

            for (int moveingCount = 0; moveingCount <= time; moveingCount++) {
                if (moveingCount == 0) {
                    dp[time][moveingCount][1] += dp[time - 1][moveingCount][1] + (pointTree == 1 ? 1 : 0);
                    continue;
                }

                if (moveingCount > W) {
                    break;
                }

                if (moveingCount % 2 == 0) {
                    // 제자리 : 1번 나무
                    dp[time][moveingCount][1] = Math.max(dp[time][moveingCount][1],
                            Math.max(dp[time - 1][moveingCount - 1][2],
                                    dp[time - 1][moveingCount][1])
                                    + (pointTree == 1 ? 1 : 0));
                } else {
                    // 2번 나무
                    dp[time][moveingCount][2] = Math.max(dp[time][moveingCount][2],
                            Math.max(dp[time - 1][moveingCount - 1][1],
                                    dp[time - 1][moveingCount][2])
                                    + (pointTree == 2 ? 1 : 0));
                }

            }
        }
        int num1 = Math.max(dp[T][W][1], dp[T][W - 1][1]);
        int num2 = Math.max(dp[T][W][2], dp[T][W - 1][2]);

        answer = Math.max(num1, num2);
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
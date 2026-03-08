import java.io.*;
import java.util.*;

public class Main {
    static final int[] matchstick = new int[] { 6, 2, 5, 5, 4, 5, 6, 3, 7, 6 };// 성냥 개비 수
    // -----------------------------------------0, 1, 2, 3, 4, 5, 6, 7, 8, 9

    static StringBuilder answer = new StringBuilder();
    static int[] input;
    static long[] dp;
    // 2개로 1을 만들고 자릿수를 늘리는게 웬만해선 큰 경우
    // 3개로 7을 만들 수 있음

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    static void sol() {
        for (int matchstickCount : input) {
            answer.append(dp[matchstickCount])
                    .append(" ")
                    .append(getMaximum(matchstickCount))
                    .append("\n");
        }
    }

    static String getMaximum(int count) {
        if (count == 2) {
            return "1";
        } else if (count == 3) {
            return "7";
        }

        if (count % 2 == 0) {
            // 짝수
            return "1".repeat(count / 2);
        } else {
            // 홀수
            return "7" + ("1".repeat(count / 2 - 1));
        }
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        input = new int[testcase];
        dp = new long[101];
        Arrays.fill(dp, Long.MAX_VALUE);

        for (int i = 0; i < testcase; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 6;
        dp[7] = 8;

        int[] arr = { 0, 0, 1, 7, 4, 2, 0, 8 };

        for (int i = 8; i <= 100; i++) {
            for (int j = 2; j <= 7; j++) {
                if (dp[i - j] == Long.MAX_VALUE) {
                    continue;
                }
                dp[i] = Math.min(dp[i], Long.parseLong(dp[i - j] + "" + arr[j]));
            }
        }
    }

    static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}
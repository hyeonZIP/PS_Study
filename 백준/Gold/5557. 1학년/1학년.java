import java.io.*;
import java.util.*;

public class Main {
    static long answer;
    static int N;
    static int[] arr;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    static void sol() {
        long[][] dp = new long[N][21];

        dp[0][arr[0]] = 1;

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i][j] == 0) {
                    continue;
                }

                int plus = j + arr[i + 1];
                int minus = j - arr[i + 1];

                if (plus >= 0 && plus <= 20) {
                    dp[i + 1][plus] += dp[i][j];
                }
                if (minus >= 0 && minus <= 20) {
                    dp[i + 1][minus] += dp[i][j];
                }
            }
        }

        answer = dp[N - 2][arr[N - 1]];
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}
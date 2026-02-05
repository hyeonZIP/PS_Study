import java.io.*;
import java.util.*;

public class Main {
    private static int answer = 1;
    private static int N, M;
    private static boolean[] isVipSeat;
    private static int[] dp = new int[42];

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        dp[0] = 1;
        dp[1] = 1;
        dp[41] = 1;
        // 1 2 3 5 8 ...
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        int count = 0;
        for (int i = 1; i <= N + 1; i++) {
            if (isVipSeat[i]) {
                if (count != 0) {
                    answer *= dp[count];
                    count = 0;
                    continue;
                }
            } else {
                count++;
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());// 좌석의 개수
        M = Integer.parseInt(br.readLine());// 고정석의 개수

        isVipSeat = new boolean[N + 2];
        isVipSeat[0] = true;
        isVipSeat[N + 1] = true;

        for (int i = 0; i < M; i++) {
            isVipSeat[Integer.parseInt(br.readLine())] = true;
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}
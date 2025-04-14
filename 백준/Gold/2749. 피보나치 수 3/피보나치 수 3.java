import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 피사노 주기
 * N번째 피보나치 수를 M으로 나눈 나머지를 구해야 할 때
 * M = Math.pow(10,k) 이고
 * k가 3이상 일 경우
 * 
 * 그 주기가 15 * Math.pow(10,k-1)
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(br.readLine());

        N %= 15 * 100_000;

        long[] dp = new long[(int) N + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2])%1_000_000;
        }
        System.out.println(dp[(int)N]);
    }
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];
        dp[1] = 0;
        if (N == 1) {
            bw.write(dp[1] + "");
        } else {
            for (int i = 2; i <= N; i++) {

                if (i % 6 == 0) {
                    dp[i] = Math.min(Math.min(dp[i / 2], dp[i / 3]), dp[i - 1]) + 1;
                } else if (i % 2 == 0) {
                    dp[i] = Math.min(dp[i / 2], dp[i - 1]) + 1;
                } else if (i % 3 == 0) {
                    dp[i] = Math.min(dp[i / 3], dp[i - 1]) + 1;
                } else {
                    dp[i] = dp[i - 1] + 1;
                }
            }
            bw.write(dp[N] + "");

        }

        bw.flush();
        bw.close();
        br.close();
    }
}

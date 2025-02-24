import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {

            int N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());
            int[] dp = new int[M + 1];
            dp[0] = 1;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (j >= arr[i]) {
                        dp[j] = dp[j] + dp[j - arr[i]];
                    }
                }
            }
            sb.append(dp[M]).append("\n");
        }

        bw.write(sb + "");
        bw.flush();
        bw.close();
        br.close();
    }
}

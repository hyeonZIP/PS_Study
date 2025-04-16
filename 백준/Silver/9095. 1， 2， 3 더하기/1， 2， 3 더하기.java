import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int[] dp = new int[11];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;

        for (int i=0; i<T; i++)
        {
            int n = Integer.parseInt(br.readLine());
            
            for (int j=3; j<n; j++)
            {
                dp[j] = dp[j-1] + dp[j-2] + dp[j-3];
            }
            sb.append(dp[n-1]);
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
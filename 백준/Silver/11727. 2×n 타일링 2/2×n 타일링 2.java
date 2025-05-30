import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[1001];

        dp[0] = 1;
        dp[1] = 3;

        if (n<3)
        {
            System.out.println(dp[n-1]);
        }
        else
        {
            for (int i=2; i<n; i++)
            {
                dp[i] = (dp[i-2]*2 + dp[i-1])%10007;
            }
            System.out.println(dp[n-1]);
        }


    }
}

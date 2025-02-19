
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] ary = new int[N];
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }

        //ary = {1, 100, 2, 50, 60, 3, 5, 6, 7, 8}

        dp[0] = ary[0];

        for (int i = 1; i < N; i++) {
            dp[i] = ary[i];
            for (int j = 0; j < i; j++) {
                if (ary[i]>ary[j])
                {
                    dp[i] = Math.max(dp[j]+ary[i],dp[i]);
                }
            }
        }

//        System.out.println(Arrays.toString(dp));

        int max = Integer.MIN_VALUE;
        for (int i : dp){
            max = Math.max(i, max);
        }

        System.out.println(max);
    }
}

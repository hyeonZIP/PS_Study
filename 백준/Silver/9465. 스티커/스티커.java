import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb= new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int test=0; test<T; test++){

            int n = Integer.parseInt(br.readLine());

            int[][] dp = new int[2][n];
            int[][] arr = new int[2][n];

            for(int i =0; i<2; i++){
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][0] = arr[0][0];
            dp[1][0] = arr[1][0];

            if(n == 1){
                sb.append(Math.max(dp[0][0],dp[1][0])).append("\n");
                continue;
            }

            dp[0][1] = arr[0][1] + dp[1][0];
            dp[1][1] = arr[1][1] + dp[0][0];
            

            for(int i=2; i<n; i++){
                dp[0][i] = Math.max(dp[1][i-2],dp[1][i-1]) + arr[0][i];
                dp[1][i] = Math.max(dp[0][i-2],dp[0][i-1]) + arr[1][i];
            }

            sb.append(Math.max(dp[0][n-1],dp[1][n-1])).append("\n");
        }

        System.out.print(sb);
    }
}

import java.io.*;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        int[] arr = new int[N+1];

        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        if(N >= 1){
            dp[1] = arr[1];
        }

        if(N >= 2){
            dp[2] = arr[1] + arr[2];
        }

        for(int i=3; i<=N; i++){
            dp[i] = Math.max(dp[i-2] + arr[i],dp[i-3] + arr[i-1] + arr[i]);
        }

        bw.write(dp[N] + "");
        bw.flush();
        bw.close();
        br.close();
    }
}

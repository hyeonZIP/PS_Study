
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        int[] dp = new int[n];
        Arrays.fill(dp,1);

        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<i; j++){
                if(arr[i]<arr[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }

        Arrays.sort(dp);
        bw.write(dp[n-1]+"");
        bw.flush();
        bw.close();
        br.close();
    }
}

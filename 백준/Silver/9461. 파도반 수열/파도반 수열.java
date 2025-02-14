

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		long[] dp = new long[101];
		
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;
		dp[4] = 2;
		dp[5] = 2;
		
		for(int i=0; i<T; i++) {
			
			int P = Integer.parseInt(br.readLine());
			
			for(int j=6; j<=P; j++) {
				dp[j] = dp[j-5] + dp[j-1]; 
			}
			
			sb.append(dp[P]).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
}

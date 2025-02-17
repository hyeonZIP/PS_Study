
import java.util.*;
import java.io.*;

public class Main {
	
	static int[] A;
	static int N,K;
	static int min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		A = new int[N];
		
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = N - 1; i >= 0; i--)
		{
			if (A[i] <= K)
			{
				min += K / A[i];
				K %= A[i];
			}
		}
		
		bw.write(min+"");
		bw.flush();
		bw.close();
		br.close();
		
	}
}

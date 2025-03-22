import java.io.*;
import java.util.*;

public class Main {
	
	static HashMap<Long,Long> map = new HashMap<>();
	static int P,Q;
	static long N;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Long.parseLong(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		map.put(0L, 1L);
		
		
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		bw.write(dp(N) + "");
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static long dp(long N) {
		
		if(map.containsKey(N)) {
			return map.get(N);
		}
		
		long a = N/P;
		long b = N/Q;
		
		map.put(N, dp(a)+dp(b));
		
		return map.get(N);
		
	}
}

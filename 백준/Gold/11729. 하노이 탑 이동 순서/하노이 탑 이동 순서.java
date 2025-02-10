import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//한국자산
//imdata

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int[] memo;
	static int[] hanoi;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		int N = Integer.parseInt(br.readLine());
		
		memo = new int[N+1];
		memo[1] = 1;
		
		sb.append(hanoi(N)).append("\n");
		
		hanoi = new int[N+1];
		hanoi[1] = N;
		hanoiDetail(N,1,3);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	private static int hanoi(int N) {
		
		if(memo[N] !=0 ) return memo[N];
		else {
			memo[N] = hanoi(N-1)*2 + 1;
		}
		
		return memo[N];
	}
	
	/**
	 * 1이 이동함
	 * 2가 이동함
	 * 2가 이동했으니 1이 이동하도록 로직을 구현해야함
	 * 그리고 3이 이동
	 */
	private static void hanoiDetail(int N, int x, int y) {
		
		if(N > 1) {
			hanoiDetail(N-1,x,6-x-y);
		}
//		sb.append(N).append(" ");
		sb.append(x).append(" ").append(y).append("\n");
		
		if(N>1) {
			hanoiDetail(N-1,6-x-y,y);
		}
	}
	
}

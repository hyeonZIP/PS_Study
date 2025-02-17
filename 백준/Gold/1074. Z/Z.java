import java.util.*;
import java.io.*;

/*
 * 2x2 탐색
 * 
 */

public class Main {
	
	static int answer;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int size = (int)Math.pow(2, N);
				
		zzz(size, r, c);
		
		bw.write(answer+"");
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void zzz(int size, int x, int y) {
		
		if(size == 1) {
			return;
		}
		
		if(x<size/2 && y<size/2) {
			zzz(size/2,x,y);
			//1사분면
		}
		else if(x<size/2 && y>=size/2) {
			answer += size*size/4;
			zzz(size/2,x,y-size/2);
			//2사분면
		}
		else if(x>=size/2 && y<size/2) {
			answer += size*size/2;
			zzz(size/2,x-size/2,y);
			//3사분면
		}
		else {
			answer += size*size/4*3;
			zzz(size/2,x-size/2,y-size/2);
			//4사분면
		}
	}
}

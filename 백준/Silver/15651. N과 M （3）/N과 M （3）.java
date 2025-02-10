
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static StringBuilder sb = new StringBuilder();
	static int[] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[M];
		
		dfs(0,1);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
//		System.out.println(sb);
	}
	
	private static void dfs(int index, int number) {
		
		if(index == M) {
			for(int a : arr) {
				sb.append(a).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=number; i<=N; i++) {
			arr[index] = i;
			dfs(index+1, number);
		}
		
	}
}

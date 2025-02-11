
import java.util.*;
import java.io.*;

public class Main {
	
	static int N,M;
	static int[] numbers;
	static int[] arr;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		numbers = new int[M];
		visited = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		dfs(0, 0);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs(int index, int numIndex) {
		if(index == M) {
			for(int i : numbers) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				numbers[index] = arr[i];
				
				visited[i] = true;
				dfs(index+1, i+1);
				visited[i] = false;
			}
			
		}
	}
}

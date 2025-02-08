import java.io.*;
import java.util.*;

/*
방향 없는 그래프가 주어졌을 때, 연결 요소 (Connected Component)의 개수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다. (1 ≤ N ≤ 1,000, 0 ≤ M ≤ N×(N-1)/2) 
둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다. (1 ≤ u, v ≤ N, u ≠ v) 같은 간선은 한 번만 주어진다.

출력
첫째 줄에 연결 요소의 개수를 출력한다.
*/
public class Main {
	
	static int N,M;
	static int[][] map;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException{
		
		//N,M, 그리고 각 노드간의 연결 관계 입력
		init();
		
		int answer = 0;
		for(int i = 1; i<=N; i++) {
			if(!visited[i]) {
				dfs(i);
				answer++;
			}
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		sb.append(answer);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];//1부터 N까지의 노드가 존재하기때문에 N+1로 크기 지정
		visited = new boolean[N+1];
		
		//2차원 배열에 각 노드간 연결 관계 입력
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			map[from][to] = 1;
			map[to][from] = 1;
		}
		
		br.close();
	}
	private static void dfs(int node) {
		visited[node] = true;
		
		for(int i=1; i<=N; i++) {
			if(map[node][i] == 1 && !visited[i]) {
				dfs(i);
			}
		}
	}
}

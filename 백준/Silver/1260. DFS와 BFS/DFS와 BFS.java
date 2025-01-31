/*
 * 1. DFS / BFS 사용
 * 2. 정점 번호가 작은 것을 먼저 방문
 */

import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int V;
	
	static int[][] map;
	
	static boolean[] visited;
	
	static String result;
	
	static StringBuilder sb = new StringBuilder();
	
	static Queue<Integer> q = new LinkedList<>();
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());//정점 개수
		M = Integer.parseInt(st.nextToken());//간선 개수
		V = Integer.parseInt(st.nextToken());//시작 정점 번호
		
		map = new int[N+1][N+1];//정점 간 연결 정보
		visited = new boolean[N+1];//정점 방문 정보
		
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			map[x][y] = 1;
			map[y][x] = 1;
		}
		dfs(V);
		sb.append("\n");
		visited = new boolean[N+1];
		q.offer(V);
		visited[V] = true;
		bfs();
		System.out.println(sb);
	}
	
	public static void dfs(int startNode) {
		sb.append(startNode).append(" ");
		visited[startNode] = true;
		
		for(int i=1; i<=N; i++) {
			
			if(map[startNode][i] == 1 && !visited[i]) {
				dfs(i);
			}
		}
	}
	
	public static void bfs() {
		
		while(!q.isEmpty()) {
			
			int startNode = q.poll();
			
			sb.append(startNode).append(" ");
			
			for(int i=1; i<=N; i++) {
				if(map[startNode][i] == 1 && !visited[i]) {
					q.offer(i);
					visited[i] = true;
				}
			}
		}
		
		
		
	}
}

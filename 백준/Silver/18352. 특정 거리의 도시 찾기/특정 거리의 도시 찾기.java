
import java.util.*;
import java.io.*;

public class Main {
	
	static int N,M,K,X;
	static ArrayList<Integer>[] adj;
	static int[] distance;
	static ArrayList<Integer> answer;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());//노드 개수
		M = Integer.parseInt(st.nextToken());//간선 개수
		K = Integer.parseInt(st.nextToken());//거리 정보
		X = Integer.parseInt(st.nextToken());//시작 노드
		
		adj = new ArrayList[N+1];
		answer = new ArrayList<>();
		distance = new int[N+1];
		Arrays.fill(distance, -1);
		distance[X] = 0;
		
		for(int i=1; i<=N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			adj[A].add(B);
		}
		
		bfs();
		
		if(answer.isEmpty()) {
			System.out.println(-1);
		}
		else {
			answer.stream().sorted().forEach(System.out::println);			
		}
		
		
	}
	
	private static void bfs() {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		
		q.add(X);
		
		while(!q.isEmpty()) {
			int node = q.poll();
			
			if(distance[node] > K) {
				break;
			}
			if(distance[node] == K) {
				answer.add(node);
			}
			
			for(int next : adj[node]) {
				if(distance[next] != -1) continue;//
				distance[next] = distance[node] + 1;
				q.add(next);
			}
		}
		
	}
}

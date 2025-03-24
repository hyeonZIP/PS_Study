import java.io.*;
import java.util.*;

public class Main {
	
	static int N,M;
	static int[] degree;
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
	
	public static void main(String[] args)throws IOException{
		init();
		
		topologySort();
	}
	
	private static void topologySort() {
		
		ArrayDeque<int[]> q = new ArrayDeque<>();
		
		for(int i=1; i<=N; i++) {
			if(degree[i] == 0) {
				q.offer(new int[] {i,1});
			}
		}
		
		ArrayList<int[]> answer = new ArrayList<>();
		while(!q.isEmpty()) {
			int[] node = q.poll();
			answer.add(new int[] {node[0],node[1]});
			
			for(int i : adj.get(node[0])) {
				degree[i]--;
				if(degree[i] == 0) {
					q.offer(new int[] {i,node[1]+1});
				}
			}
		}
		
		answer.sort(Comparator.comparing(o->o[0]));
		
		for(int[] arr : answer) {
			System.out.print(arr[1] + " ");
		}
		
	}
	
	private static void init()throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<=N; i++) {
			adj.add(new ArrayList<>());
		}
		degree = new int[N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adj.get(a).add(b);
			degree[b]++;
		}
	}
}

import java.util.*;
import java.io.*;

public class Main {
	
	
	static public class Node{
		int end;
		long weight;
		
		public Node(int end, long weight) {
			this.end = end;
			this.weight = weight;
		}
	}
	static int n,m;
	static StringBuilder sb = new StringBuilder();
	static ArrayList<ArrayList<Node>> adj = new ArrayList<>();
	static String[][] map;
	
	public static void main(String[] args) throws IOException {
		init();
		
		
		
		for(int i=1; i<=n; i++) {
			String[] arr = dijkstra(i);
			makeTable(arr, i);
		}
		for(int i=0; i<n; i++) {
			map[i][i] = "-";
		}
		StringBuilder sb = new StringBuilder();
		for(String[] i : map) {
			for(String ii : i) {
				sb.append(ii).append(" ");
			}
			sb.append("\n");
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb+"");
		bw.flush();
		bw.close();
		
	}
	
	private static void makeTable(String[] arr, int start) {
		for(int i=0; i<n; i++) {
			map[i][start-1] = arr[i];
		}
	}
	
	private static String[] dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(o->o.weight));
		pq.offer(new Node(start,0));
		
		long[] dist = new long[n+1];
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[start] = 0;
		
		String[] arr = new String[n];
		arr[start-1] = "0";
		
		boolean[] visited = new boolean[n+1];
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(visited[cur.end]) continue;
			visited[cur.end] = true;
			
			for(Node next : adj.get(cur.end)) {
				if(dist[next.end] > dist[cur.end]+next.weight) {
					dist[next.end] = dist[cur.end]+next.weight;
					arr[next.end-1] = cur.end+"";
					
					pq.offer(new Node(next.end,dist[next.end]));
				}
			}
		}
		
		return arr;
		
	}
	
	private static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new String[n][n];
		
		for(int i=0; i<=n; i++) {
			adj.add(new ArrayList<>());
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			adj.get(a).add(new Node(b,c));
			adj.get(b).add(new Node(a,c));
		}
	}
}

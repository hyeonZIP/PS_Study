/*
문제
그래프의 정점의 집합을 둘로 분할하여, 각 집합에 속한 정점끼리는 서로 인접하지 않도록 분할할 수 있을 때, 그러한 그래프를 특별히 이분 그래프 (Bipartite Graph) 라 부른다.

그래프가 입력으로 주어졌을 때, 이 그래프가 이분 그래프인지 아닌지 판별하는 프로그램을 작성하시오.

입력
입력은 여러 개의 테스트 케이스로 구성되어 있는데, 첫째 줄에 테스트 케이스의 개수 K가 주어진다. 각 테스트 케이스의 첫째 줄에는 그래프의 정점의 개수 V와 간선의 개수 E가 빈 칸을 사이에 두고 순서대로 주어진다. 각 정점에는 1부터 V까지 차례로 번호가 붙어 있다. 이어서 둘째 줄부터 E개의 줄에 걸쳐 간선에 대한 정보가 주어지는데, 각 줄에 인접한 두 정점의 번호 u, v (u ≠ v)가 빈 칸을 사이에 두고 주어진다. 

출력
K개의 줄에 걸쳐 입력으로 주어진 그래프가 이분 그래프이면 YES, 아니면 NO를 순서대로 출력한다.

제한
2 ≤ K ≤ 5
1 ≤ V ≤ 20,000
1 ≤ E ≤ 200,000


1-2-3-4-5

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int T,V,E;
	static ArrayList<Integer>[] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());//테스트 케이스
		
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());//정점의 개수
			E = Integer.parseInt(st.nextToken());//간선의 개수
			
			list = new ArrayList[V+1];
			
			for(int i=1; i<=V; i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i=1; i<=E; i++) {
				st = new StringTokenizer(br.readLine());
				
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				
				list[u].add(v);
				list[v].add(u);
			}
//			dfsSolution();
			if(dfsSolution()) {
				sb.append("YES").append("\n");
			}
			else {
				sb.append("NO").append("\n");
			}
//			bfsSolution();
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
		
	}//main
	
	private static boolean dfsSolution() {
		int[] bipartite = new int[V+1];//0이면 방문 안함 노드1의 시작 값은 항상 1 다음 노드는 -1이 와야함
		
		for(int i=1; i<=V; i++) {
			if(bipartite[i] == 0 &&!dfs(i,bipartite,1)) {
				return false;
			}
		}
		
		return true;
	}//dfsSolution
	
	private static boolean dfs(int node,int[] bipartite, int state) {
		bipartite[node] = state;
		for(int i : list[node]) {
			if(bipartite[i] == 0) {//방문하지 않은 곳
				if(!dfs(i, bipartite, -state)) {
					return false;
				}
			}
			else if(bipartite[i] == state){//현재 상태와 동일한 값을 가진 노드가 인접한다 뜻으로 이분 그래프가 아니게 됨
				return false;
			}
		}
		return true;
	}//dfs
	
	private static void bfsSolution() {
		boolean[] visited = new boolean[V+1];
	}//bfsSolution
}

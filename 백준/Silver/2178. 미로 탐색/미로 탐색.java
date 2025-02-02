import java.util.*;
import java.io.*;

public class Main {
	
	public static class Pair{
		int x;
		int y;
		int result;
		public Pair(int x, int y, int result) {
			this.x = x;
			this.y = y;
			this.result = result;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] dx = {0,0,1,-1};
		int[] dy = {1,-1,0,0};
		
		int[][] map = new int[N+1][M+1];
		boolean[][] visited = new boolean[N+1][M+1];
		
		for(int i=1; i<=N; i++) {
			String s = br.readLine();
			
			for(int j=0; j<M; j++) {
				
				map[i][j+1] = Character.getNumericValue(s.charAt(j));
			}
		}
		
		Queue<Pair> q = new LinkedList<>();
		
		q.offer(new Pair(1,1,1));
		visited[1][1] = true;
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			
			for(int i=0; i<4; i++) {
				int px = p.x + dx[i];
				int py = p.y + dy[i];
				
				if(0<px && px < N+1 && 0<py && py<M+1 && map[px][py] != 0 && visited[px][py] != true) {
					int result = p.result +1;
					q.offer(new Pair(px, py,result));
					visited[px][py] = true;
					if(px == N && py == M) {
						System.out.println(result);
						q.clear();
						break;
					}
				}
			}
		}
	}
}

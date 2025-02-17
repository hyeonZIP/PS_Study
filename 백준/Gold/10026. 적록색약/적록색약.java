
import java.util.*;
import java.io.*;

public class Main {
	
	static int rgbCnt, rbCnt;
	static boolean[][] visited;
	static int n;
	static char[][] map;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		visited = new boolean[n][n];
		
		map = new char[n][n];
		
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<n; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(!visited[i][j]) {
					char color = map[i][j];
					dfs(i,j,color);
					rgbCnt++;
				}
			}
		}
		visited = new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(!visited[i][j]) {
					char color = map[i][j];
					dfs2(i,j,color);
					rbCnt++;
				}
			}
		}
		
		System.out.println(rgbCnt + " " + rbCnt);
	}
	
	private static void dfs(int x, int y, char color) {
		
		visited[x][y] = true;
		
		for(int i=0; i<4; i++) {
			int px = x + dx[i];
			int py = y + dy[i];
			
			if(px < 0 || py < 0 || px>=n || py>=n || visited[px][py]) continue;
			
			if(map[px][py] == color) {
				dfs(px,py,color);
			}
		}
		
	}
	private static void dfs2(int x, int y, char color) {
		
		visited[x][y] = true;
		
		for(int i=0; i<4; i++) {
			int px = x + dx[i];
			int py = y + dy[i];
			
			if(px < 0 || py < 0 || px>=n || py>=n || visited[px][py]) continue;
			
			if(color == 'R' || color == 'G') {
				if(map[px][py] == 'R' || map[px][py] == 'G') {
					dfs2(px,py,color);
				}
			}
			else if(map[px][py] == color) {
				dfs2(px,py,color);
			}
					
		}
		
	}
}

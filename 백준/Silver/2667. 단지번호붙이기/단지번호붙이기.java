import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * 1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다.
 * 상하좌우는 연결된 것이고 대각선상에 집이 있는 경우는 연결된 것이 아니다.
 * 지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고
 * 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.
 * 
 * 출력
 * 첫 번째 줄에는 총 단지수를 출력하시오. 그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.
 * 
 * 최대 입력: 25*25
 * 총 단지수와 단지내 집의 수는 DFS를 통해 계산
 */
public class Main {
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int answer;
	static List<Integer> answerList = new ArrayList<>();
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int count;
	
	public static void main(String[] args) {

		init();
		
		searchAPT();
		
		System.out.println(answer);
		Collections.sort(answerList);
		for(int i : answerList) {
			System.out.println(i+1);
		}
	}
	
	private static void init() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				String srr = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(String.valueOf(srr.charAt(j)));
				}
			}			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void searchAPT() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] > 0 && !isVisited(i,j)) {
					dfs(i,j);
					answer++;
					answerList.add(count);
					count = 0;
				}
			}
		}
	}
	
	private static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for(int i=0; i<4; i++) {
			int px = x + dx[i];
			int py = y + dy[i];
			
			if(isRange(px,py)) continue;
			if(isVisited(px,py)) continue;
			if(isAPT(px,py)) {
				count++;
				dfs(px,py);
			}
		}
	}
	
	private static boolean isVisited(int x, int y) {
		return visited[x][y];
	}
	
	private static boolean isRange(int x, int y) {
		return x<0 || y<0 || x>=N || y>=N;
	}
	private static boolean isAPT(int x, int y) {
		return map[x][y] == 1;
	}
}


import java.util.*;
import java.io.*;

/*
 * N은 항상 짝수
 * 각 구역을 탐색하는 메서드
 * 구역을 쪼개는 메서드
 * 카운팅하는 메서드
 */

public class Main {
	
	static int N;
	static int blueCount;
	static int whiteCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		divSearch(map);
		
		bw.write(whiteCount + "\n" + blueCount);
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void divSearch(int[][] map) {
		
		int length = map.length;
//		System.out.println("맵의 길이 : " + length);
		
		if(checkDiff(map)) {
			for(int i=0; i<length; i+=length/2) {
				for(int j=0; j<length; j+=length/2) {
//					System.out.println("x : " + i + " 와 " + " y : " + j + " 자르기");
					divSearch(divPaper(map, i , j, length/2));
				}
			}
		}
		else {
			if(map[0][0] == 1) {
				blueCount++;
			}
			else {
				whiteCount++;
			}
			//하나의 색깔일 경우
		}
	}
	
	private static int[][] divPaper(int[][] map, int x, int y,int length) {
		
		int[][] makeNew = new int[length][length];
		
		
		for(int i=0, k=x; i<length; i++, k++) {
			for(int j=0, l=y; j<length; j++, l++) {
//				System.out.println("x : " + k + " y : " + l);
				makeNew[i][j] = map[k][l];
			}
		}
		
		return makeNew;
	}
	
	private static boolean checkDiff(int[][] map) {//해당 색종이에 다른 색이 있는지 판별 동일한 색이면 true 리턴
		int length = map.length;
		int init = map[0][0];
		
		for(int i=0; i<length; i++) {
			for(int j=0; j<length; j++) {
				if(map[i][j] != init) {
					return true;
				}
			}
		}
		return false;
	}
}

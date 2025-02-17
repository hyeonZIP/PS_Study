
/*
 * 쿼드 트리
 * 전체 탐색 메서드
 * 분해 메서드
 */

import java.util.*;
import java.io.*;

public class Main {
	
	static int n;
	static StringBuilder sb = new StringBuilder();
	static int[] quadTree;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][n];
		
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<n; j++) {
				
				map[i][j] = Character.getNumericValue(s.charAt(j));
			}
		}
		sol(map);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	private static void sol(int[][] map) {
		int len = map.length;
		
		if(checkDiff(map, 0, 0)) {
			for(int i=0; i<len; i+=len/2) {
				for(int j=0; j<len; j+=len/2) {
					sol(divMap(map, i, j));
				}
			}
			sb.append(")");
			
		}
		else {
		}

		
	}
	
	private static boolean checkDiff(int[][] map, int x, int y) {
		int len = map.length;
		int init = map[0][0];
		
		for(int i=0; i<len; i++) {
			for(int j=0; j<len; j++) {
				if(map[i][j] != init) {
					sb.append("(");
					return true;
				}
			}
		}
		
		sb.append(init);
		return false;
	}
	
	private static int[][] divMap(int[][] map, int x, int y) {
		int len = map.length;
		
		int[][] divMap = new int[len/2][len/2];
		
		for(int i=0, k=x; i<len/2; i++, k++) {
			for(int j=0, l=y; j<len/2; j++, l++) {
				divMap[i][j] = map[k][l];
			}
		}
		
		return divMap;
	}
}


import java.util.*;
import java.io.*;

/**
 * 집(1)의 위치는 항상 고정
 * 치킨집(2) 을 M개 만큼 조합을 뽑아 각각의 경우 BFS를 이용해 계산 후 최소값 찾기
 * BFS를 할 필요가 있나? 그냥 빼기 빼기 해서 값 더하면?
 */
public class Main {
	
	static ArrayList<int[]> chickenPos;
	static ArrayList<int[]> homePos;
	static int[] numbers;
	
	static int N,M;
	
	static int chickDistance = Integer.MAX_VALUE;
	static boolean[][] activateMap;
	
	static ArrayDeque<int[]> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		chickenPos = new ArrayList<int[]>();
		homePos = new ArrayList<int[]>();
		numbers = new int[M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int position = Integer.parseInt(st.nextToken());
				if(position == 2) {
					chickenPos.add(new int[] {i,j});
				}
				else if(position == 1) {
					homePos.add(new int[] {i,j});
				}
			}
		}
		combine(0,0);
		sb.append(chickDistance);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static void combine(int index, int numberIndex) {
//		System.out.println("index : " + index + " numberIndex : " + numberIndex);
		if(index == M) {
			calcDistance();
			return;
		}
		
		for(int i=numberIndex; i<chickenPos.size();i++) {
			numbers[index] = i;
			combine(index+1,i+1);
		}
	}
	
	private static void calcDistance() {
		int minAllDistance = 0;
		for(int[] i : homePos) {
			int minHomeToChickenDistance = Integer.MAX_VALUE;
			for(int j : numbers) {
				int diffDistance = Math.abs(chickenPos.get(j)[0]-i[0]) + Math.abs(chickenPos.get(j)[1]-i[1]);
				minHomeToChickenDistance = Math.min(diffDistance, minHomeToChickenDistance);
			}
			minAllDistance += minHomeToChickenDistance;
		}
		chickDistance = Math.min(minAllDistance, chickDistance);
	}
}

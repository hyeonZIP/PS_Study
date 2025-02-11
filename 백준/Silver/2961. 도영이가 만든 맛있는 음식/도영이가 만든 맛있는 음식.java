import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//문제
//재료 N개. 신맛 S와 쓴맛 B 신맛은 사용한 재료의 신맛의 곱이고, 쓴맛은 합이다.
//신맛과 쓴맛의 차이를 작게 만들려고 한다. 
//재료는 적어도 하나 사용해야 한다.
//재료의 신맛과 쓴맛이 주어졌을 때, 신맛과 쓴맛의 차이가 가장 작은 요리를 만드는 프로그램을 작성하시오.
//
//입력
//첫째 줄에 재료의 개수 N(1 ≤ N ≤ 10)이 주어진다. 
//모든 재료를 사용해서 요리를 만들었을 때, 
//그 요리의 신맛과 쓴맛은 모두 1,000,000,000보다 작은 양의 정수이다.
//
//출력
//첫째 줄에 신맛과 쓴맛의 차이가 가장 작은 요리의 차이를 출력한다. 
/**
 * 1개 부터 n개 까지 ~ 
 */
public class Main {
	
	static int N;
	static int[][] sourBitter;
	static int answer = Integer.MAX_VALUE;
	
	static ArrayList<int[]> flavor;
	static ArrayList<Integer>[] flavorCase;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		sourBitter = new int[N][2];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sourBitter[i][0] = Integer.parseInt(st.nextToken());
			sourBitter[i][1] = Integer.parseInt(st.nextToken());
		}
		
		bp(0,1,0,0);
		System.out.println(answer);
	}
	
	private static void bp(int depth, int sour, int bitter, int count) {
		if(depth == N) {
			if(count != 0) {
				answer = Math.min(Math.abs(sour-bitter), answer);
			}
			return;
		}
		
		bp(depth+1,sour*sourBitter[depth][0],bitter+sourBitter[depth][1],count+1);
		bp(depth+1,sour,bitter,count);
	}
}

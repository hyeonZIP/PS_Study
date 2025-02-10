import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
 * 1 초	256 MB
 * 문제
N이 주어졌을 때, 1부터 N까지의 수로 이루어진 순열을 사전순으로 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 N(1 ≤ N ≤ 8)이 주어진다. 

출력
첫째 줄부터 N!개의 줄에 걸쳐서 모든 순열을 사전순으로 출력한다.
 */
public class Main {
	
	static boolean[] selected;
	static int[] number;
	static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		selected = new boolean[N];
		number = new int[N];
		p(0);
		
	}
	
	private static void p(int n) {
		if(n == N) {
			for(int i:number) {
				System.out.print(i + " ");
			}
			System.out.println();
			return;
		}
		for(int i=0; i<N; i++) {
			if(!selected[i]) {
				number[n] = i+1;
				selected[i] = true;
				p(n+1);
				selected[i] = false;
			}
		}
		
	}
}


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
시간 제한		메모리 제한
0.15 초		128 MB

문제
정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.

X가 3으로 나누어 떨어지면, 3으로 나눈다.
X가 2로 나누어 떨어지면, 2로 나눈다.
1을 뺀다.
정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.

입력
첫째 줄에 1보다 크거나 같고, 106보다 작거나 같은 정수 N이 주어진다.

출력
첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.


 */
public class Main {
	
	static int[] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb= new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		dp = new int[N+1];
		dp[1] = 0;
		
		System.out.println(dpFunc(N));
	}
	
	private static int dpFunc(int N) {
		
		if(dp[N] != 0) return dp[N];	
		if(N<=1) return 0;
		
		if(N%6 == 0)
		{
			dp[N] = Math.min((Math.min(dpFunc(N/3),dpFunc(N/2))),dpFunc(N-1))+1;
		}
		else if(N%3 == 0 )
		{
			dp[N] = Math.min(dpFunc(N/3), dpFunc(N-1))+1;
		}
		else if(N%2 == 0) {
			dp[N] = Math.min(dpFunc(N/2), dpFunc(N-1))+1;
		}
		else {
			dp[N] = dpFunc(N-1) + 1;
		}
		
		return dp[N];
	}
}

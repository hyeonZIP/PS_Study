import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
1 초	256 MB

카드는 카드팩의 형태로만 구매할 수 있고, 카드팩의 종류는 카드 N개가 포함된 카드팩, 총 N가지가 존재한다.
민규는 카드의 개수가 적은 팩이더라도 가격이 비싸면 높은 등급의 카드가 많이 들어있을 것이라는 미신을 믿고 있다. 
따라서, 민규는 돈을 최대한 많이 지불해서 카드 N개 구매하려고 한다. 카드가 i개 포함된 카드팩의 가격은 Pi원이다.

예를 들어, 카드팩이 총 4가지 종류가 있고,
P1 = 1, P2 = 5, P3 = 6, P4 = 7인 경우에 민규가 카드 4개를 갖기 위해 지불해야 하는 금액의 최댓값은 10원이다. 2개 들어있는 카드팩을 2번 사면 된다.

P1 = 5, P2 = 2, P3 = 8, P4 = 10인 경우에는 카드가 1개 들어있는 카드팩을 4번 사면 20원이고, 
이 경우가 민규가 지불해야 하는 금액의 최댓값이다.

P1 = 3, P2 = 5, P3 = 15, P4 = 16인 경우에는 
3개 들어있는 카드팩과 1개 들어있는 카드팩을 구매해 18원을 지불하는 것이 최댓값이다.

카드 팩의 가격이 주어졌을 때, N개의 카드를 구매하기 위해 민규가 지불해야 하는 금액의 최댓값

입력
첫째 줄에 민규가 구매하려고 하는 카드의 개수 N이 주어진다. (1 ≤ N ≤ 1,000)

둘째 줄에는 Pi가 P1부터 PN까지 순서대로 주어진다. (1 ≤ Pi ≤ 10,000)

출력
첫째 줄에 민규가 카드 N개를 갖기 위해 지불해야 하는 금액의 최댓값을 출력한다.
 */

public class Main {
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] pi = new int[N+1];
		int[] dp = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=N; i++) {
			pi[i] = Integer.parseInt(st.nextToken());
			for(int j=1; j<=i; j++) {
				dp[i] = Math.max(dp[i], dp[i-j] + pi[j]);
			}
		}
		
		System.out.println(dp[N]);
		
		
		
		//1장당 가격이 높은 것을 사야 최대값이다
	}
}

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());// 최소 고객 유치 수
        int N = Integer.parseInt(st.nextToken());// 도시 수

        int[] dp = new int[C + 100];
        int[] cost = new int[N + 1];
        int[] customer = new int[N + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            cost[i] = Integer.parseInt(st.nextToken());// 홍보 비용
            customer[i] = Integer.parseInt(st.nextToken());// 얻는 고객 수
        }

        dp[0] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = customer[i]; j < dp.length; j++) {
                if (dp[j - customer[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - customer[i]] + cost[i]);
                }
            }
        }

        int answer = Integer.MAX_VALUE;

        for (int i = C; i < dp.length; i++) {
            if (dp[i] < answer) {
                answer = dp[i];
            }
        }

        bw.write(String.valueOf(answer));
        bw.close();
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;
    static int N;
    static int max = 0;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        init();

        //System.out.print("백트래킹 : ");
        dfsV1(1, 0);
        System.out.println(max);

        //System.out.print("Return 백트래킹 : ");
        //System.out.println(dfsV2(1));

        //System.out.print("DP (TopDown) : ");
        //System.out.println(dfsV3(1));
        //System.out.println("Return 백트래킹은 팩토리얼 알고리즘과 유사하게 생각");
    }

    private static void dfsV1(int index, int count) {
        for (int i = index; i <= N; i++) {

            int day = arr[i][0];
            int money = arr[i][1];

            if (i + day <= N + 1) {
                count += money;
                max = Math.max(max, count);
                dfsV1(i + day, count);
                count -= money;
            }
        }
    }

    private static int dfsV2(int index) {

        if (index > N + 1) {
            return Integer.MIN_VALUE;
        }
        if (index == N + 1) {
            return 0;
        }

        int day = arr[index][0];
        int money = arr[index][1];

        return Math.max(dfsV2(index + day) + money, dfsV2(index + 1));//Math.max(넣고, 안넣고)
    }

    private static int dfsV3(int index) {

        if (index > N + 1) {
            return Integer.MIN_VALUE;
        }
        if (index == N + 1) {
            return 0;
        }
        if (dp[index] != -1) {
            return dp[index];
        }

        int day = arr[index][0];
        int money = arr[index][1];

        return dp[index] = Math.max(dfsV3(index + 1), dfsV3(index + day) + money);//Math.max(넣고, 안넣고)
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1][];
        dp = new int[N + 1];
        Arrays.fill(dp, -1);

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            arr[i] = new int[]{T, P};
        }
    }
}

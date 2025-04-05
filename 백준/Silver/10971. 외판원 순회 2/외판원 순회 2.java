import java.util.*;
import java.io.*;

public class Main {

    static int[][] map, dp;
    static int N, min;


    public static void main(String[] args) throws IOException {

        init();

        dfs(0, 1);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(min + "");
        bw.close();

    }

    private static void dfs(int now, int visited) {
        if (visited == ((1 << N) - 1)) {//N이 4일 때 모두 방문한 경우면 1111
            if (map[now][0] == 0) return;//처음 시작안 1 노드로 돌아갈 수 없는 경우 최소거리를 갱신하지 않음

            int temp = dp[now][visited] + map[now][0];//이전까지의 최선 경로와 현재 경로 계산
            min = Math.min(min, temp);//최소값 갱신
            return;
        }

        for (int i = 0; i < N; i++) {
            int next = (1 << i);

            if ((visited | next) == visited) continue;//이미 방문한 곳은 건너뜀

            if (map[now][i] == 0) continue;//자기 자신도 건너뜀

            if (dp[now][visited] + map[now][i] < dp[i][visited | next]) {//유사 데이크스트라 처럼 해당 경로가 더 짧을 경우 갱신
                dp[i][visited | next] = dp[now][visited] + map[now][i];
                dfs(i, visited | next);
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dp = new int[N][1 << N];
        map = new int[N][N];
        min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][1] = 0;

        br.close();
    }
}

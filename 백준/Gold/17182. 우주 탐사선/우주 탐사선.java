import java.io.*;
import java.util.*;

public class Main {
    static int N, K, answer = Integer.MAX_VALUE;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    static void sol() {
        floyd();

        boolean[] visited = new boolean[N];
        visited[K] = true;

        dfs(K, visited, 0);
    }

    static void dfs(int start, boolean[] visited, int time) {
        if (isAllVisited(visited)) {
            answer = Math.min(answer, time);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;

            dfs(i, visited, time + map[start][i]);

            visited[i] = false;
        }
    }

    static boolean isAllVisited(boolean[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    static void floyd() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());// 행성의 개수
        K = Integer.parseInt(st.nextToken());// ana호 발사되는 행성의 위치

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int a = Integer.parseInt(st.nextToken());

                map[i][j] = a;
            }
        }
    }

    static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }

}
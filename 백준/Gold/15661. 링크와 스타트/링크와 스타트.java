import java.util.*;
import java.io.*;

public class Main {

    static int[][] map;
    static boolean[] visited;
    static int min, N;

    public static void main(String[] args) throws IOException {

        init();

        sol(0);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));



        bw.write(min+"");
        bw.close();

    }
    private static void sol(int depth){
        int team1 = 0;
        int team2 = 0;
        if (depth == N){
            for (int i = 0; i < N; i++) {
                for (int j = i+1; j < N; j++) {
                    if (visited[i] != visited[j]) continue;

                    if(visited[i]){
                        team1 += map[i][j] + map[j][i];
                    }else{
                        team2 += map[i][j] + map[j][i];
                    }
                }
            }
            int temp = Math.abs(team1 - team2);
            if (temp < min){
                min = temp;
            }
            return;
        }

        visited[depth] = true;
        sol(depth+1);
        visited[depth] = false;
        sol(depth+1);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        min = Integer.MAX_VALUE;
        visited = new boolean[N];

        br.close();
    }
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 보안 영역을 늘린 비용 - 서비스 수익이 최대가 되는 경우를 구한다
 * 집의 개수는 정해져 있기때문에 최대 수익이 정해저 있다
 * 해당 수익을 넘어가면 리턴한다
 * 서비스가 시작하는 위치가 주어지지 않는다
 *
 * 시간제한 3초이기 때문에 모든 K에 대해 전체 탐색을 진행한다
 * N*N에 대해 K=1 K=2 K=3 K...
 * N은 최대 20
 *
 * K=1일 때 1x1 점으로 시작해서
 * k=2일 때 상하좌우로 한칸씩 늘려나간다 > bfs 방식으로 증가시킨다.
 *
 * k=1일 때는 M이 최대값이기 떄문에 k=2부터 시작한다
 */
public class Solution {
    static int N,M,maxCost;
    static int[][] map;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t<=testCase; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][N];

            int houseCnt=0;
            for (int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<N; j++){
                    int obj = Integer.parseInt(st.nextToken());
                    map[i][j] = obj;
                    if (obj==1){
                        houseCnt++;
                    }
                }
            }

            maxCost = houseCnt*M;//모든 집을 서비스 하더라도 얻을 수 있는 최대 수익

            int k = 2;
            int maxHouse = 1;
            while (k * k + (k - 1) * (k - 1) <= maxCost) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        int totalHouse = bfs(i,j,k);
                        if (k * k + (k - 1) * (k - 1) <= totalHouse*M){//
                            maxHouse = Math.max(maxHouse, totalHouse);
                        }
                    }
                }
                k++;
            }
            sb.append("#").append(t).append(" ").append(maxHouse).append("\n");
        }//testCase
        bw.write(sb+"");
        bw.flush();
        bw.close();
        br.close();
    }//main

    private static int bfs(int i, int j, int k){
        boolean[][] visited = new boolean[N][N];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i,j});
        visited[i][j] = true;
        int currentK = 1;

        int houseCount = 0;
        if (map[i][j] == 1){
            houseCount++;
        }
        while (currentK < k) {
            int size = q.size();

            for (int a = 0; a < size; a++) {
                int[] pos = q.poll();

                for (int b = 0; b < 4; b++) {
                    int px = pos[0] + dx[b];
                    int py = pos[1] + dy[b];

                    if (isOutOfRange(px,py)) continue;
                    if (visited[px][py]) continue;

                    if (map[px][py] == 1){
                        houseCount++;
                    }
                    visited[px][py] = true;
                    q.offer(new int[]{px, py});
                }
            }
            currentK++;
        }//while
        return houseCount;
    }//bfs

    private static boolean isOutOfRange(int x, int y){
        return x<0 || y<0 || x>=N || y>=N;
    }
}

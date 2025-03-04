import java.util.*;
import java.io.*;

/**
 * BFS를 이용하여 경과 시간에 따른 도달 가능 구역을 map에 표기하며 나아간다
 * 매번 BFS가 동작할 때마다 count를 증가시킨다.
 */

public class Solution {

    static int[][] map;
    static boolean[][] visited;
    static int height, width,hour;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int testcase = 1; testcase <= T; testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            height = Integer.parseInt(st.nextToken());
            width = Integer.parseInt(st.nextToken());
            int holeY = Integer.parseInt(st.nextToken());
            int holeX = Integer.parseInt(st.nextToken());
            hour = Integer.parseInt(st.nextToken());

            map = new int[height][width];
            visited = new boolean[height][width];

            for (int y = 0; y < height; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < width; x++) {
                    map[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = bfs(holeY, holeX);

            sb.append("#").append(testcase).append(" ").append(answer).append("\n");
        }//testcase
        bw.write(sb+"");
        bw.flush();
        bw.close();
        br.close();
    }//main

    private static int bfs(int y, int x) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{y, x});
        visited[y][x] = true;
        int answer = 0;

        int t = 0;
        while(t<hour){
            int time = q.size();
//            System.out.println("q.size() : " + q.size());
            for (int i=0; i<time; i++){
                answer++;
                int[] pos = q.poll();
                int py = pos[0];
                int px = pos[1];
                int pipeType = map[py][px];


                switch (pipeType) {
                    case 1:
                        if (isLeftConnected(py, px - 1)) {
                        visited[py][px - 1] = true;
                            q.offer(new int[]{py, px - 1});
                        }
                        if (isRightConnected(py, px + 1)) {
                        visited[py][px + 1] = true;
                            q.offer(new int[]{py, px + 1});
                        }
                        if (isTopConnected(py - 1, px)) {
                        visited[py - 1][px] = true;
                            q.offer(new int[]{py - 1, px});
                        }
                        if (isBottomConnected(py + 1, px)) {
                        visited[py + 1][px] = true;
                            q.offer(new int[]{py + 1, px});
                        }
                        break;
                    case 2:
                        if (isTopConnected(py - 1, px)) {
                        visited[py - 1][px] = true;
                            q.offer(new int[]{py - 1, px});
                        }
                        if (isBottomConnected(py + 1, px)) {
                        visited[py + 1][px] = true;
                            q.offer(new int[]{py + 1, px});
                        }
                        break;
                    case 3:
                        if (isLeftConnected(py, px - 1)) {
                        visited[py][px - 1] = true;
                            q.offer(new int[]{py, px - 1});
                        }
                        if (isRightConnected(py, px + 1)) {
                        visited[py][px + 1] = true;
                            q.offer(new int[]{py, px + 1});
                        }
                        break;
                    case 4:
                        if (isTopConnected(py - 1, px)) {
                        visited[py - 1][px] = true;
                            q.offer(new int[]{py - 1, px});
                        }
                        if (isRightConnected(py, px + 1)) {
                        visited[py][px + 1] = true;
                            q.offer(new int[]{py, px + 1});
                        }
                        break;
                    case 5:
                        if (isBottomConnected(py + 1, px)) {
                        visited[py + 1][px] = true;
                            q.offer(new int[]{py + 1, px});
                        }
                        if (isRightConnected(py, px + 1)) {
                        visited[py][px + 1] = true;
                            q.offer(new int[]{py, px + 1});
                        }
                        break;
                    case 6:
                        if (isBottomConnected(py + 1, px)) {
                        visited[py + 1][px] = true;
                            q.offer(new int[]{py + 1, px});
                        }
                        if (isLeftConnected(py, px - 1)) {
                        visited[py][px - 1] = true;
                            q.offer(new int[]{py, px - 1});
                        }
                        break;
                    case 7:
                        if (isTopConnected(py - 1, px)) {
                        visited[py - 1][px] = true;
                            q.offer(new int[]{py - 1, px});
                        }
                        if (isLeftConnected(py, px - 1)) {
                        visited[py][px - 1] = true;
                            q.offer(new int[]{py, px - 1});
                        }
                }
            }
            t++;
        }
        return answer;
    }//bfs

    private static boolean isLeftConnected(int y, int x) {
        return isInRange(y, x) && !visited[y][x] && (map[y][x] == 3 || map[y][x] == 1 || map[y][x] == 4 || map[y][x] == 5);
    }

    private static boolean isRightConnected(int y, int x) {
        return isInRange(y, x) && !visited[y][x] && (map[y][x] == 1 || map[y][x] == 3 || map[y][x] == 6 || map[y][x] == 7);
    }

    private static boolean isBottomConnected(int y, int x) {
        return isInRange(y, x) && !visited[y][x] && (map[y][x] == 1 || map[y][x] == 2 || map[y][x] == 4 || map[y][x] == 7);
    }

    private static boolean isTopConnected(int y, int x) {
        return isInRange(y, x) && !visited[y][x] && (map[y][x] == 1 || map[y][x] == 2 || map[y][x] == 5 || map[y][x] == 6);
    }

    private static boolean isInRange(int y, int x) {
        return y >= 0 && x >= 0 && y < height && x < width;
    }
}

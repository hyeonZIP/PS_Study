import java.util.*;
import java.io.*;

/**
 * 벽을 한개만 부술 수 있다
 * 벽을 부수고 전진했지만 목표에 도달하지 못했을 경우 해당 결과는 쓰레기값이 된다
 *
 */
public class Main {

    static int width, height;
    static int[][] map;
    static boolean[][][] visited;
    static ArrayDeque<int[]> q = new ArrayDeque<>();

    static int[] dx ={0,1,-1,0};
    static int[] dy ={1,0,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        map = new int[height+1][width+1];
        visited = new boolean[height+1][width+1][2];

        for (int y = 1; y <= height; y++) {
            String s = br.readLine();
            for (int x = 1; x <= width; x++) {
                map[y][x] = s.charAt(x-1) - '0';
            }
        }
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(bfs()+"");
        bw.flush();
        bw.close();
        br.close();
    }//main

    private static int bfs() {
        q.offer(new int[]{1, 1, 1,1});//y값, x값, 벽 부수기 1회, 카운트 1회
        visited[1][1][1] = true;

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int y = pos[0];
            int x = pos[1];
            int count = pos[3];
            int breaking = pos[2];
            if (y == height && x == width) return count;

            for (int i=0; i<4; i++){
                int py = y + dy[i];
                int px = x + dx[i];

                if (py < 1 || px < 1 || py > height || px > width) continue;

                if (map[py][px] == 0 && !visited[py][px][breaking]){
                    visited[py][px][breaking] = true;
                    q.offer(new int[]{py,px,breaking,count+1});
                }
                else if(breaking == 1 && !visited[py][px][breaking-1]){
                    visited[py][px][breaking-1] = true;
                    q.offer(new int[]{py,px, 0,count+1});
                }
            }
        }
        return -1;
    }
}

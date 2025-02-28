import java.io.*;
import java.util.*;

/**
 * 사람이 움직이고 불이 움직인다
 * 사람이 이동 가능한 경우
 * - 벽이 아닐 때
 * - 이동한 좌표 상하좌우에 불이 없을 때
 */

public class Main {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static char[][] map;
    static ArrayDeque<int[]> fire;
    static ArrayDeque<int[]> human;

    static int width, height;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 0; t < testCase; t++) {

            st = new StringTokenizer(br.readLine());
            width = Integer.parseInt(st.nextToken());
            height = Integer.parseInt(st.nextToken());

            map = new char[height][width];
            fire = new ArrayDeque<>();
            human = new ArrayDeque<>();

            for (int y = 0; y < height; y++) {
                String s = br.readLine();
                for (int x = 0; x < width; x++) {
                    char obj = s.charAt(x);
                    map[y][x] = obj;
                    if (obj == '*') {
                        fire.offer(new int[]{y, x});
                    } else if (obj == '@') {
                        human.offer(new int[]{y, x, 0});
                    }
                }
            }
            bfs(sb);
        }
        bw.write(sb+"");
        bw.flush();
        bw.close();
        br.close();
    }//main

    private static void bfs(StringBuilder sb) {
        while (!human.isEmpty()) {
            int humanCount = human.size();
            for (int p = 0; p< humanCount; p++){
                int[] player = human.poll();

                int y = player[0];
                int x = player[1];
                int count = player[2];

                if (map[y][x] != '@') continue;//해당 좌표에 @가 없다는 건 죽었다는 뜻...
                for (int i = 0; i < 4; i++) {
                    int py = dy[i] + y;
                    int px = dx[i] + x;

                    if (px < 0 || py < 0 || px > width - 1 || py > height - 1) {
                        sb.append(count+1).append("\n");
                        return;
                    }
                    if (map[py][px] != '.') continue;

                    map[py][px] = '@';
                    human.offer(new int[]{py, px, count + 1});
                }
            }

            int fireCount = fire.size();
            for (int f=0; f<fireCount; f++){
                int[] firePunch = fire.poll();

                int fireY = firePunch[0];
                int fireX = firePunch[1];

                for (int i = 0; i < 4; i++) {
                    int py = dy[i] + fireY;
                    int px = dx[i] + fireX;

                    if (px < 0 || py < 0 || px > width - 1 || py > height - 1) continue;
                    if (map[py][px] == '#' || map[py][px] == '*') continue;
                    map[py][px] = '*';//이동할 좌표에 사람이 있거나 빈공간이면 불로 변경한다

                    fire.offer(new int[]{py, px});
                }
            }
        }//while

        sb.append("IMPOSSIBLE").append("\n");
    }
}

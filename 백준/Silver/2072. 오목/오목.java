import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {


    /**
     * 매번 바둑이 놓일 때마다 완탐으로 탐색?
     */
    static int N, answer = -1;
    static int[][] map = new int[19][19];

    static int[] dy = {0, -1, -1, -1};
    static int[] dx = {-1, -1, 0, 1};

    static int[] dy2 = {0, 1, 1, 1};
    static int[] dx2 = {1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            int color = 1 + i % 2;
            map[y - 1][x - 1] = color;//1 또는 2로 흑백 구분

            if (dfs(y - 1, x - 1, color)) {
                answer = i + 1;
                break;
            }
        }

        System.out.println(answer);
    }

    private static boolean dfs(int y, int x, int color) {
        boolean[][] visited = new boolean[19][19];

        ArrayDeque<int[]> stack = new ArrayDeque<>();

        visited[y][x] = true;
        //방금 놓인 돌 기준으로 탐색 8방향 탐색해서 없으면 리턴
        for (int i = 0; i < 4; i++) {
            int count = 0;
            stack.push(new int[]{y, x, 1});
            while (!stack.isEmpty()) {

                int[] pos = stack.pop();
                int py = pos[0] + dy[i];
                int px = pos[1] + dx[i];
                count = pos[2];

                if (count >= 6) break;
                if (isOutOfRange(py, px) || visited[py][px] || map[py][px] != color || map[py][px] == 0) continue;
                stack.push(new int[]{py, px, count + 1});
                visited[py][px] = true;
            }//while
            if (count <= 5){
                int count2 = 0;
                stack.push(new int[]{y,x,0});
                while(!stack.isEmpty()){
                    int[] pos = stack.pop();
                    int py = pos[0] + dy2[i];
                    int px = pos[1] + dx2[i];
                    count2 = pos[2];

                    if (count+count2 >= 6) break;
                    if (isOutOfRange(py, px) || visited[py][px] || map[py][px] != color || map[py][px] == 0) continue;
                    stack.push(new int[]{py, px, count2 + 1});
                    visited[py][px] = true;
                }

                if (count+count2 == 5) return true;
            }
        }//for
        return false;
    }

    private static boolean isOutOfRange(int y, int x) {
        return x < 0 || y < 0 || x >= 19 || y >= 19;
    }
}

